package gitlet;

import java.io.Serializable;
import java.util.Map;

class Tree implements Serializable {
    private final String name;
    private final transient Map<String, Tree> treeMap;
    private final transient Map<String, Blob> blobMap;
    private final Map<String, String> treeIDMap;
    private final Map<String, String> blobIDMap;

    public Tree() {
        this.name = null;
        this.treeMap = null;
        this.blobMap = null;
        this.treeIDMap = null;
        this.blobIDMap = null;
    }
}
