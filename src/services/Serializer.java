package services;

import java.io.*;

/**
 * Created by szkutek on 12.06.17.
 */
public class Serializer {
    public static void serialize(Serializable item, String path) throws FileNotFoundException {
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(item);
            out.close();
            file.close();
            System.out.println("Successfully saved to file " + path);
        } catch (java.io.IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Object deserialize(String path) throws ClassNotFoundException {
        try {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(file);
            Serializable item = (Serializable) in.readObject();

            in.close();
            file.close();
            System.out.println("Successfully read from file " + path);
            return item;
        } catch (java.io.IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}

