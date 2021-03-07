package gitlet;

import java.io.File;
import java.time.Instant;
import java.util.Map;

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
        this.parentID = Repository.getHeadID();
        this.secondParentID = branch == null ? null : Repository.getBranchID(branch);
        this.map = Map.copyOf(Repository.readIndex());
    }

    void writeAsHead() {
        String id = write();
        File file = Repository.getHeadCommitFile();
        writeContents(file, id);
    }

    Map<String, String> getMap() {
        return map;
    }
}
