package Functions;

import java.io.*;

/**
 * Created by Алена on 02.10.2018.
 */
public class LinkedListTabulatedFunction implements TabulatedFunction, /*Serializable,*/ Externalizable {//реализация функционала списка

    class FunctionNode {       //узел списка, который хранит в себе ссылку на предыдущий и следующий элемент

        private FunctionNode next;          //ссылка на следующий элемент
        private FunctionNode previous;      //ссылка на предыдущий элемент
        private FunctionPoint element;          //элемент списка

        public FunctionNode(){
            this.next = null;
            this.previous = null;
        }

        public FunctionNode(FunctionPoint element){
            this();
            this.element = element;
        }

        public FunctionNode(FunctionNode previous, FunctionNode next, FunctionPoint element){
            this.next = next;
            this.previous = previous;
            this.element = element;
        }

        public FunctionNode getNext(){
            return this.next;
        }

        public FunctionNode getPrevious(){
            return this.previous;
        }

        public void setNext(FunctionNode next){
            this.next = next;
        }

        public void setPrevious(FunctionNode previous){
            this.previous = previous;
        }

        public FunctionPoint getElement(){
            return this.element;
        }

        public void setElement(FunctionPoint element){
            this.element = element;
        }
    }

    private FunctionNode head;
    private int size = 0;


    public LinkedListTabulatedFunction(){}

    public LinkedListTabulatedFunction(FunctionPoint[] functionPoints){
        if(functionPoints.length<2) throw new IllegalArgumentException();
        head = new FunctionNode();
        head.setNext(head);
        head.setPrevious(head);
        for(int i = 0; i<functionPoints.length-1; ++i){
            if(functionPoints[i].getX()>functionPoints[i+1].getX()) throw new IllegalArgumentException();
            this.addNodeToTail().setElement(functionPoints[i]);
        }
        this.addNodeToTail().setElement(functionPoints[functionPoints.length-1]);
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount){
        if(leftX >= rightX || pointsCount < 2)
            throw new IllegalArgumentException();
        head = new FunctionNode();
        head.setNext(head);
        head.setPrevious(head);
        for(int i=0; i<pointsCount; ++i){
            addNodeToTail().setElement(new FunctionPoint((leftX + (rightX-leftX)*i/(pointsCount-1)), 0));
        }
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, double[] value){
        this(leftX, rightX, value.length);
        for(int i=0; i<value.length; ++i){
            this.getNodeByIndex(i).getElement().setY(value[i]);
        }
    }

    private int size() {         //возвращаем размер списка
        return this.size;
    }

    private boolean isEmpty() {   //проверяем список на наличие элементов в нем
        return size() == 0;
    }

    private FunctionNode addNodeToTail() {      //добавление следующего элемента в конец списка
        FunctionNode node = new FunctionNode();
        //System.out.println("new element: x = " + node.getElement().getX() + " y = " +node.getElement().getY());
        if(isEmpty()) {
            //System.out.println("list was empty");
            node.setPrevious(head);
            this.head.setNext(node);
        } else {
            node.setPrevious(getNodeByIndex(size()-1));
            this.getNodeByIndex(size() - 1).setNext(node);
            //System.out.println("Element " + size() + " added");
        }
        this.head.setPrevious(node);
        node.setNext(head);
        size++;
        return node;
    }

    private FunctionNode addNodeByIndex(int index){  //добавление элемента в середину списка  по его будущему номеру
        if(index==size()) return addNodeToTail();
        FunctionNode node = new FunctionNode();
        node.setPrevious(getNodeByIndex(index-1));
        getNodeByIndex(index-1).setNext(node);
        getNodeByIndex(index).setPrevious(node);
        node.setNext(getNodeByIndex(index));
        size++;
        return node;
    }

    private FunctionNode deleteNodeByIndex(int index){    //удаление элемента
        FunctionNode node = getNodeByIndex(index);
        getNodeByIndex(index-1).setNext(getNodeByIndex(index+1));
        getNodeByIndex(index).setPrevious(getNodeByIndex(index-1));
        size--;
        return node;
    }

    private FunctionNode getNodeByIndex(int index){
        FunctionNode node;
        if(index < size()/2) {
            node = head.getNext();
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node;
        }
        node = head.getPrevious();
        for (int i = size()-1; i > index; i--) {
            node = node.getPrevious();
        }
        return node;
    }

    public double getLeftDomainBorder(){
        return this.getNodeByIndex(0).getElement().getX();
    }

    public double getRightDomainBorder(){
        return this.getNodeByIndex(size()-1).getElement().getX();
    }

    public double getFunctionValue(double x){
        int k=0;
        if(x>getLeftDomainBorder() && x < getRightDomainBorder()){
            for (int i=0; i<size(); ++i, ++k){
                if(getNodeByIndex(i).getElement().getX()>x) break;
            }
            return (x - getNodeByIndex(k-1).getElement().getX())*(getNodeByIndex(k).getElement().getY()-getNodeByIndex(k-1).getElement().getY())/
                    (getNodeByIndex(k).getElement().getX()-getNodeByIndex(k-1).getElement().getX())+getNodeByIndex(k-1).getElement().getY();
        }
        if(Math.abs(x-getLeftDomainBorder())<Double.MIN_VALUE) return getPointY(0);
        if(Math.abs(x-getRightDomainBorder())<Double.MIN_VALUE) return getPointY(getPointCount()-1);
        return Double.NaN;
    }

    public int getPointCount(){
        return this.size();
    }

    public FunctionPoint getPoint(int index){
        if(index < 0 || index>=getPointCount())
            throw new FunctionPointIndexOutOfBoundsException();
        return this.getNodeByIndex(index).getElement();
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException{
        setPointX(index, point.getX());
        this.getNodeByIndex(index).setElement(point);
    }

    public double getPointX(int index) {
        if(index < 0 || index>=getPointCount())
            throw new FunctionPointIndexOutOfBoundsException();
        return this.getNodeByIndex(index).getElement().getX();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException{
        if(index < 0 || index>=size())
            throw new FunctionPointIndexOutOfBoundsException();
        if(index!=0 && index!=getPointCount()-1 && (x < getNodeByIndex(index-1).getElement().getX()||
                x > getNodeByIndex(index+1).getElement().getX()))
            throw new InappropriateFunctionPointException();
        this.getNodeByIndex(index).getElement().setX(x);
    }

    public double getPointY(int index){
        if(index < 0 || index>=getPointCount())
            throw new FunctionPointIndexOutOfBoundsException();
        return this.getNodeByIndex(index).getElement().getY();
    }

    public void setPointY(int index, double y){
        if(index < 0 || index>=getPointCount())
            throw new FunctionPointIndexOutOfBoundsException();
        this.getNodeByIndex(index).getElement().setY(y);
    }

    public void deletePoint(int index){
        if(index < 0 || index >= getPointCount())
            throw new FunctionPointIndexOutOfBoundsException();
        if (getPointCount() < 3)
            throw new IllegalStateException();
        deleteNodeByIndex(index);
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException{
        if(point.getX()<getRightDomainBorder() && point.getX()>getLeftDomainBorder())
            for(int i = 0; i<getPointCount(); ++i)
                if(Math.abs(getNodeByIndex(i).getElement().getX()-point.getX()) < Double.MIN_VALUE)
                    throw new InappropriateFunctionPointException();
        int k=0;
        for (; k<size(); ++k){
            if(getNodeByIndex(k).getElement().getX()>point.getX()) break;
        }
        this.addNodeByIndex(k).setElement(point);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getPointCount());
        for(int i=0; i < getPointCount(); ++i){
            out.writeObject(this.getNodeByIndex(i).getElement());
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int count = in.readInt();
        for(int i=0; i<count; ++i){
            this.addNodeToTail().setElement((FunctionPoint) in.readObject());
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append('{');
        for(int i=0; i<getPointCount(); ++i) {
            sb.append(getNodeByIndex(i).getElement().toString());
            if (i == getPointCount()-1) break;
            sb.append(", ");
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TabulatedFunction))
            return false;
        TabulatedFunction function = (TabulatedFunction) obj;
        if(getPointCount()!=function.getPointCount())
            return false;
        for (int i=0; i < getPointCount(); i++)
            if (!getPoint(i).equals(function.getPoint(i)))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 37*result + getPointCount();
        for(int i = 0; i< getPointCount(); ++i){
            result = 37*result + getNodeByIndex(i).getElement().hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        TabulatedFunction tf;
        FunctionPoint fp[] = new FunctionPoint[getPointCount()];
        for(int i = 0; i<getPointCount(); ++i){
            fp[i] = new FunctionPoint(getPointX(i), getPointY(i));
        }
        tf = new LinkedListTabulatedFunction(fp);
        return tf;
    }
}

