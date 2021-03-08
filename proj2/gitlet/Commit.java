package gitlet;

import java.io.File;
import java.time.Instant;
import java.util.Map;

import static gitlet.Utils.readContentsAsString;
import static gitlet.Utils.writeContents;

/**
 * Represents a gitlet commit object.
 * It's a good idea to give a description here of what else this Class
 * does at a high level.
 *
 * @author st3v3
 */
class Commit implements GitObject {

    static final Commit INIT = new Commit();
    private final String message;
    private final Instant timestamp;
    private final String parentID;
    private final String secondParentID;
    private final Map<String, String> map;

    Commit() {
        this.message = "initial commit";
        this.timestamp = Instant.EPOCH;
        this.parentID = null;
        this.secondParentID = null;
        this.map = Map.of();
    }

    Commit(String message) {
        this(message, null);
    }

    Commit(String message, String branch) {
        this.message = message;
        this.timestamp = Instant.now();
        this.parentID = getHeadID();
        this.secondParentID = branch == null ? null : getBranchID(branch);
        this.map = Map.copyOf(Index.read().produceMap());
    }

    void writeAsHead() {
        String id = write();
        File file = Repository.getHeadFile();
        writeContents(file, id);
    }

    Map<String, String> getMap() {
        return map;
    }

    static Commit getHead() {
        String id = getHeadID();
        return (Commit) GitObject.read(id);
    }

    static Commit getBranch(String branch) {
        String id = getBranchID(branch);
        return (Commit) GitObject.read(id);
    }

    static String getHeadID() {
        File file = Repository.getHeadFile();
        return readContentsAsString(file);
    }

    static String getBranchID(String branch) {
        File file = Repository.getBranchFile(branch);
        return readContentsAsString(file);
    }

    boolean contains(File file) {
        Blob blob = new Blob(file);
        String fileName = file.getName();
        String id = map.get(fileName);
        return blob.equals(id);
    }

    boolean istracking(File file) {
        String fileName = file.getName();
        return map.containsKey(fileName);
    }
}
