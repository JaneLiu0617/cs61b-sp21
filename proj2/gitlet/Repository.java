package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static gitlet.Utils.*;

/**
 * Represents a gitlet repository.
 * TODO: It's a good idea to give a description here of what else this Class
 * does at a high level.
 *
 * @author st3v3
 */
class Repository {

    // The current working directory.
    public static final File CWD = new File(System.getProperty("user.dir"));
    // The .gitlet directory.
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File HEAD_FILE = join(GITLET_DIR, "HEAD");
    public static final File INDEX_FILE = join(GITLET_DIR, "index");
    public static final File OBJECTS_DIR = join(GITLET_DIR, "objects");
    public static final File REFS_DIR = join(GITLET_DIR, "refs");
    public static final File HEADS_DIR = join(REFS_DIR, "heads");

    public static boolean isInitialized() {
        return GITLET_DIR.exists();
    }

    public static void initialize() throws IOException {
        if (isInitialized()) {
            throw error("A Gitlet version-control system already exists in the current directory.");
        }
        GITLET_DIR.mkdir();
        HEAD_FILE.createNewFile();
        INDEX_FILE.createNewFile();
        OBJECTS_DIR.mkdir();
        REFS_DIR.mkdir();
        HEADS_DIR.mkdir();
        File master = join(HEADS_DIR, "master");
        master.createNewFile();
        writeContents(HEAD_FILE, "refs/heads/master");
        writeContents(master, creatObjectFile(Commit.INIT_COMMIT));
    }

    public static void addFile(File file) {

    }

    public static void commit(String message) {

    }

    public static void removeFile(File file) {

    }

    public static String getLog() {

        throw new RuntimeException("Not implemented!");
    }

    public static String getGlobalLog() {

        throw new RuntimeException("Not implemented!");
    }

    public static String[] getCommitIDByMsg(String message) {

        throw new RuntimeException("Not implemented!");
    }

    public static String getStatus() {

        throw new RuntimeException("Not implemented!");
    }

    public static void createBranch(String name) {

    }

    public static void removeBranch(String name) {

    }

    public static void reset(String id) {

    }

    public static void mergeBranch(String name) {

    }

    public static void checkoutToBranch(String name) {

    }

    public static void checkoutFile(File file) {

    }

    public static void checkoutFileToCommit(File file, String id) {

    }

    private static String creatObjectFile(Serializable obj) throws IOException {
        String sha1 = sha1(serialize(obj));
        File dir = join(OBJECTS_DIR, sha1.substring(0, 2));
        File file = join(dir, sha1.substring(2));
        dir.mkdir();
        file.createNewFile();
        writeObject(file, obj);
        return sha1;
    }

    /* TODO: fill in the rest of this class. */
}
