package gitlet;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static gitlet.Utils.*;

public class StageReference extends Reference {

    private final Set<String> removeSet;

    StageReference() {
        super();
        this.removeSet = new HashSet<>();
    }

    static StageReference read() {
        return readObject(Repository.INDEX_FILE, StageReference.class);
    }


    static void clearIndexFile() {
        new StageReference().writeToIndex();
    }

    void writeToIndex() {
        writeObject(Repository.INDEX_FILE, this);
    }

    @Override
    void remove(String fileName) {
        super.remove(fileName);
    }

    boolean isEmpty() {
        return removeSet.isEmpty() && map.isEmpty();
    }

    void stage(File file) {
        Commit headCommit = Commit.getHead();
        String content = readContentsAsString(file);
        String fileName = file.getName();
        Blob blob = new Blob(content);
        if (headCommit.contains(fileName, blob)) {
            remove(fileName);
        } else {
            add(fileName, blob);
        }
        writeToIndex();
    }
}
