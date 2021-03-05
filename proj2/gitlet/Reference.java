package gitlet;

import java.util.HashMap;
import java.util.Map;

import static gitlet.Utils.writeObject;

class Reference extends GitObject {

    protected final Map<String, String> map;

    Reference() {
        this.map = new HashMap<>();
    }

    boolean contains(Blob blob) {
        String fileName = blob.getFileName();
        return blob.getID().equals(map.get(fileName));
    }

    void remove(String fileName) {
    }

    void add(Blob blob) {
        if (contains(blob)) {
            return;
        }
        String fileName = blob.getFileName();
        map.put(fileName, blob.createObjectFile());
    }
}
