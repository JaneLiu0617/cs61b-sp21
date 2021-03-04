package gitlet;

import java.io.Serializable;

public class Blob implements Serializable {
    private final String name;
    private final String content;

    public Blob() {
        this.name = null;
        this.content = null;
    }
}
