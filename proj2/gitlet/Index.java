package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static gitlet.Utils.*;

public class Index implements Serializable {

    private final Map<String, String> stageMap;
    private final Set<String> unstageSet;

    Index() {
        this.stageMap = new HashMap<>();
        this.unstageSet = new HashSet<>();
    }

    static Index read() {
        return readObject(Repository.INDEX_FILE, Index.class);
    }

    static void clear() {
        Index index = new Index();
        index.write();
    }

    void write() {
        writeObject(Repository.INDEX_FILE, this);
    }

    void stage(File file) {
        Blob blob = new Blob(file);
        String fileName = file.getName();
        stageMap.put(fileName, blob.write());
        unstageSet.remove(fileName);
    }

    void remove(File file) {
        String fileName = file.getName();
        stageMap.remove(fileName);
        unstageSet.remove(fileName);
    }

    Map<String, String> produceMap() {
        Commit head = Commit.getHead();
        Map<String, String> map = new HashMap<>(head.getMap());
        map.putAll(stageMap);
        map.keySet().removeAll(unstageSet);
        return map;
    }

    boolean isEmpty() {
        return stageMap.isEmpty() && unstageSet.isEmpty();
    }

    boolean isTracking(File file) {
        String fileName = file.getName();
        return stageMap.containsKey(fileName);
    }

    void unstage(File file) {
        String fileName = file.getName();
        stageMap.remove(fileName);
        Commit head = Commit.getHead();
        if (head.istracking(file)) {
            unstageSet.add(fileName);
            restrictedDelete(file);
        }
    }
}
