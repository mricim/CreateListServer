package main.java;

import java.util.Objects;

public class Rutas {
    private final String system;
    private final String fileORlist;
    private final String version;
    private final String href;
    private final String name;
    private final String path;
    private final String md5;
    private final String fileSearch;

    public Rutas(String system, String fileORlist, String version, String href, String name, String path, String md5, String fileSearch) {
        this.system = system;
        this.fileORlist = fileORlist;
        this.version = version;
        this.href = href;
        this.name = name;
        this.path = path;
        this.md5 = md5.toLowerCase();
        this.fileSearch = fileSearch;
    }

    public String getSystem() {
        return system;
    }

    public String getFileORlist() {
        return fileORlist;
    }

    public String getVersion() {
        return version;
    }

    public String getHref() {
        return href;
    }

    public String getName() {
        if (name != null) {
            return name;
        }
        return "0";
    }

    public String getPath() {
        return path;
    }

    public String getPathNope() {
        if (path != null) {
            return path.replaceAll("\\\\", "/");
        }
        return "null";
    }

    public String getMd5() {
        if (md5 != null) {
            return md5;
        }
        return "0";
    }

    public String getFileSearch() {
        return fileSearch;
    }

    @Override
    public String toString() {
        return "ListaDeRutas{" +
                "system='" + system + '\'' +
                ", type='" + fileORlist + '\'' +
                ", version='" + version + '\'' +
                ", href='" + href + '\'' +
                ", name='" + name + '\'' +
                ", path='" + getPathNope() + '\'' +
                ", md5='" + md5 + '\'' +
                ", file='" + fileSearch + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rutas)) return false;
        Rutas rutas = (Rutas) o;
        return getName().equals(rutas.getName()) &&
                getPathNope().equals(rutas.getPathNope()) &&
                getMd5().equals(rutas.getMd5());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPathNope(), getMd5());
    }
}
