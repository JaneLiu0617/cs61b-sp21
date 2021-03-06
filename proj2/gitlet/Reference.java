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

    String merge(StageReference stageRef) {
        stageRef.merge(map);
        return createFile();
    }

    boolean contains(String fileName) {
        return map.containsKey(fileName);
    }
}
