package gitlet;

import java.io.File;

import static gitlet.Utils.readContentsAsString;

class Blob implements GitObject {

    private final String content;

    Blob(File file) {
        this.content = readContentsAsString(file);
    }

    boolean equals(String id) {
        return this.hash().equals(id);
    }
}
