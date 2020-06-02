package main.java.SelectPath;

import main.java.CheckSumMD5;
import main.java.Hash;

import javax.xml.bind.DatatypeConverter;
import java.io.File;


public class CustomDirOrFile {


    String path;
    String name;
    String hash = null;
    boolean dirTRUEfileFALSE;

    public CustomDirOrFile(String path, String name, boolean dirTRUEfileFALSE) throws Exception {
        this.path = path;
        this.name = name;
        if (!dirTRUEfileFALSE) {
            //this.hash = CheckSumMD5.getMD5Checksum(new File(path));
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
