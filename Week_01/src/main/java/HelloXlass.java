package src.main.java;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloXlass extends ClassLoader{

    public static void main(String[] args) {
        try {
            Object obj  =   new HelloXlass().findClass("Hello").newInstance();
            Method method = obj.getClass().getMethod("hello");
            method.invoke(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassFile(name);
        return defineClass(name, bytes, 0, bytes.length);
    }
    private byte[] loadClassFile(String name) {

        byte[] bytes = null;

        InputStream in = null;
        OutputStream out = null;
//"D:\\算法\\Hello\\" +
        try {
            in = new FileInputStream(new File("resource\\"+ name + ".xlass"));
            out = new ByteArrayOutputStream();
            int length;
            while (-1 != (length = in.read())) {
                length = 255 - length;
                out.write(length);
            }
            bytes = ((ByteArrayOutputStream) out).toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }
}
