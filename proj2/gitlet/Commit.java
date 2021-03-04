package gitlet;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a gitlet commit object.
 * TODO: It's a good idea to give a description here of what else this Class
 * does at a high level.
 *
 * @author st3v3
 */
class Commit implements Serializable {
    private final String message;
    private final Instant timestamp;
    private final String treeID;
    private final String parentID;
    private final String secondParentID;

    public static final Commit INIT_COMMIT = new Commit("initial commit", null, null);

    public Commit(String message, String treeID, String parentID, String secondParentID) {
        this.message = message;
        this.timestamp = parentID == null ? Instant.EPOCH : Instant.now();
        this.treeID = null;
        this.parentID = parentID;
        this.secondParentID = secondParentID;
    }

    public Commit(String message, String treeID, String parentID) {
        this(message, treeID, parentID, null);
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getTreeID() {
        return treeID;
    }

    public String getParentID() {
        return parentID;
    }

    public String getSecondParentID() {
        return secondParentID;
    }
}
