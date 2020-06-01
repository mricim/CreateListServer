package main.java.SelectPath;

import javax.xml.bind.DatatypeConverter;
import main.java.Hash;
import java.io.File;

public class CustomDirOrFile {


    String path;
    String name;
    String hash = null;
    boolean dirTRUEfileFALSE;

    public CustomDirOrFile(String path, String name, boolean dirTRUEfileFALSE) {
        this.path = path;
        this.name = name;
        if (!dirTRUEfileFALSE) {
            this.hash = toHex(Hash.MD5.checksum((new File(path))));
        }
        this.dirTRUEfileFALSE = dirTRUEfileFALSE;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getHash() {
        return hash;
    }

    public boolean isDirTRUEfileFALSE() {
        return dirTRUEfileFALSE;
    }

    private static String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }

    @Override
    public String toString() {
        return "java.Test.CustomDirOrFile{" +
                "path='" + path + '\'' +
                ", hash='" + hash + '\'' +
                ", dir?=" + dirTRUEfileFALSE +
                '}';
    }
}
