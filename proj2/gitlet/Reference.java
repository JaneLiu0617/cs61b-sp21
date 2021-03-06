package gitlet;

import java.util.HashMap;
import java.util.Map;

class Reference implements GitObject {

    protected final Map<String, String> map;

    Reference() {
        this.map = new HashMap<>();
    }

    boolean contains(String fileName, Blob blob) {
        return blob.getID().equals(map.get(fileName));
    }

    void remove(String fileName) {
    }

    void add(String fileName, Blob blob) {
        if (contains(fileName, blob)) {
            return;
        }
        map.put(fileName, blob.createFile());
    }

    String merge(StageReference stageRef) {
        throw new RuntimeException("Not implemented!");
    }
}
