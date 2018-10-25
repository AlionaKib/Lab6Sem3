package View;

import Functions.Function;

import java.io.*;

public class FunctionLoader extends ClassLoader {

    public Class getClassFromFile(File f) {
        byte[] raw = new byte[(int) f.length()];
        System.out.println(f.length());
        InputStream in = null;
        try {
            in = new FileInputStream(f);
            in.read(raw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (in != null)
                in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(null, raw, 0, raw.length);
    }
}
