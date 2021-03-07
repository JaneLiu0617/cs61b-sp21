package gitlet;

import java.io.File;

import static gitlet.Utils.join;
import static gitlet.Utils.readContentsAsString;

class Blob implements GitObject {

    private final String content;

    Blob(String fileName) {
        File file = join(Repository.CWD, fileName);
        this.content = readContentsAsString(file);
    }

    boolean equals(String id) {
        return this.hash().equals(id);
    }
}
