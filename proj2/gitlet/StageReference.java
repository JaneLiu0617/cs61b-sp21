package gitlet;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
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

    void merge(Map<String, String> refMap) {
        refMap.putAll(map);
        refMap.keySet().removeAll(removeSet);
    }

    void unstage(String fileName) {
        remove(fileName);
        Commit headCommit = Commit.getHead();
        if (headCommit.contains(fileName)) {
            removeSet.add(fileName);
            File file = join(Repository.CWD, fileName);
            restrictedDelete(file);
        }
    }

    boolean isEmpty() {
        return removeSet.isEmpty() && map.isEmpty();
    }

    private void add(String fileName, Blob blob) {
        if (contains(fileName, blob)) {
            return;
        }
        map.put(fileName, blob.createFile());
    }

    private void remove(String fileName) {
        String id = map.remove(fileName);
        if (id == null) {
            return;
        }
        GitObject.delete(id);
    }

    private void writeToIndex() {
        writeObject(Repository.INDEX_FILE, this);
    }
}
