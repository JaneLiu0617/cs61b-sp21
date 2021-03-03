package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

import static gitlet.Utils.*;

/**
 * Represents a gitlet commit object.
 * TODO: It's a good idea to give a description here of what else this Class
 * does at a high level.
 *
 * @author st3v3
 */
class Commit implements Serializable {

    public static final File COMMIT_DIR = join(Repository.GITLET_DIR, "commits");
    public static final Commit INITIAL_COMMIT =
            new Commit("initial commit", Instant.EPOCH, null, null, null);

    private final String message;
    private final Instant timestamp;
    private final String parentID;
    private final String secondParentID;
    private final Map<String, String> fileMapping;

    public Commit(String message, Instant timestamp,
                  String parentID, String secondParentID,
                  Map<String, String> fileMapping) {
        this.message = message;
        this.timestamp = timestamp;
        this.parentID = parentID;
        this.secondParentID = secondParentID;
        this.fileMapping = fileMapping;
    }

    public Commit(String message, String parentID,
                  String secondParentID, Map<String, String> fileMapping) {
        this(message, Instant.now(), parentID, secondParentID, fileMapping);
    }

    public Commit(String message, String parentID,
                  Map<String, String> fileMapping) {
        this(message, parentID, null, fileMapping);
    }

    public static String creatInitialCommit() {
        COMMIT_DIR.mkdirs();
        File initCommitFile = join(COMMIT_DIR, sha1(serialize(INITIAL_COMMIT)));
        try {
            initCommitFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        writeObject(initCommitFile, INITIAL_COMMIT);
        return sha1(serialize(INITIAL_COMMIT));
    }

    public String getMessage() {
        return message;
    }

    public String getParentID() {
        return parentID;
    }

    public String getSecondParentID() {
        return secondParentID;
    }

    public Map<String, String> getMap() {
        return fileMapping;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
