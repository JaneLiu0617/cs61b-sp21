package gitlet;

import java.time.Instant;

/**
 * Represents a gitlet commit object.
 * It's a good idea to give a description here of what else this Class
 * does at a high level.
 *
 * @author st3v3
 */
class Commit extends GitObject {

    private final String message;
    private final Instant timestamp;
    private final String referenceID;
    private final String parentID;
    private final String secondParentID;

    Commit() {
        this.message = "initial commit";
        this.timestamp = Instant.EPOCH;
        this.referenceID = new Reference().createObjectFile();
        this.parentID = null;
        this.secondParentID = null;
    }

    Commit(String message, String referenceID,
           String parentID, String secondParentID) {
        this.message = message;
        this.timestamp = Instant.now();
        this.referenceID = referenceID;
        this.parentID = parentID;
        this.secondParentID = secondParentID;
    }

    Commit(String message, String referenceID, String parentID) {
        this(message, referenceID, parentID, null);
    }

    static String createInitCommitFile() {
        return new Commit().createObjectFile();
    }

    boolean contains(Blob blob) {
        Reference reference = getReference();
        return reference.contains(blob);
    }

    Reference getReference() {
        return (Reference) readObjectFile(referenceID);
    }
}
