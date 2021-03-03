package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static gitlet.Utils.error;
import static gitlet.Utils.join;

/**
 * Represents a gitlet repository.
 * TODO: It's a good idea to give a description here of what else this Class
 * does at a high level.
 *
 * @author st3v3
 */
class Repository implements Serializable {

    // The current working directory.
    public static final File CWD = new File(System.getProperty("user.dir"));
    // The .gitlet directory.
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File STAGE_DIR = join(GITLET_DIR, "staged");
    public static final File STAGE_ADD_DIR = join(STAGE_DIR, "add");
    public static final File STAGE_RM_DIR = join(STAGE_DIR, "remove");
    public static final File REPO_FILE = join(GITLET_DIR, "repo");

    private String head;
    private final Map<String, String> branchMapping;
    private final Map<String, String> stageMapping;

    public static boolean isInitialized() {
        return GITLET_DIR.exists();
    }

    public Repository() {
        head = null;
        branchMapping = new HashMap<>();
        stageMapping = new HashMap<>();
    }

    public void initialize() {
        if (isInitialized()) {
            throw error("A Gitlet version-control system already exists in the current directory.");
        }
        GITLET_DIR.mkdirs();
        STAGE_DIR.mkdirs();
        STAGE_ADD_DIR.mkdirs();
        STAGE_RM_DIR.mkdirs();
        head = Commit.creatInitialCommit();
        branchMapping.put("master", head);
    }

    public void addFile(File file) {

    }

    public void commit(String message) {

    }

    public void removeFile(File file) {

    }

    public String getLog() {

        throw new RuntimeException("Not implemented!");
    }

    public String getGlobalLog() {

        throw new RuntimeException("Not implemented!");
    }

    public String[] getCommitIDByMsg(String message) {

        throw new RuntimeException("Not implemented!");
    }

    public String getStatus() {

        throw new RuntimeException("Not implemented!");
    }

    public void createBranch(String name) {

    }

    public void removeBranch(String name) {

    }

    public void reset(String id) {

    }

    public void mergeBranch(String name) {

    }

    public void checkoutToBranch(String name) {

    }

    public void checkoutFile(File file) {

    }

    public void checkoutFileToCommit(File file, String id) {

    }

    /* TODO: fill in the rest of this class. */
}
