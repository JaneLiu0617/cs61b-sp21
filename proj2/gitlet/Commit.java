package gitlet;

import java.io.File;
import java.time.Instant;

import static gitlet.Utils.*;

/**
 * Represents a gitlet commit object.
 * It's a good idea to give a description here of what else this Class
 * does at a high level.
 *
 * @author st3v3
 */
class Commit implements GitObject {

    private final String message;
    private final Instant timestamp;
    private final String referenceID;
    private final String parentID;
    private final String secondParentID;

    Commit() {
        this.message = "initial commit";
        this.timestamp = Instant.EPOCH;
        this.referenceID = new Reference().createFile();
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

    static void createInitCommit() {
        writeContents(Repository.HEAD_FILE, "refs/heads/master");
        writeContents(Repository.MASTER_FILE, new Commit().createFile());
    }

    static void commit(String message) {
        Commit head = getHead();
        String id = head.merge(message, StageReference.read());
        writeToHead(id);
        StageReference.clearIndexFile();
    }

    static String getHeadID() {
        String pathToHead = readContentsAsString(Repository.HEAD_FILE);
        File headFile = join(Repository.GITLET_DIR, pathToHead);
        return readContentsAsString(headFile);
    }

    static Commit getHead() {
        String headCommitID = getHeadID();
        return (Commit) GitObject.read(headCommitID);
    }

    private static void writeToHead(String id) {

    }

    boolean contains(String fileName, Blob blob) {
        Reference reference = getReference();
        return reference.contains(fileName, blob);
    }

    Reference getReference() {
        return (Reference) GitObject.read(referenceID);
    }

    String merge(String message, StageReference stageRef) {
        String id = getReference().merge(stageRef);
        return new Commit(message, id, getID()).createFile();
    }
}
