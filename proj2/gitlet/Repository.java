package gitlet;

import java.io.File;

import static gitlet.Utils.*;

/**
 * Represents a gitlet repository.
 * It's a good idea to give a description here of what else this Class
 * does at a high level.
 *
 * @author st3v3
 */
class Repository {

    // The current working directory.
    static final File CWD = new File(System.getProperty("user.dir"));
    // The .gitlet directory.
    static final File GITLET_DIR = join(CWD, ".gitlet");
    static final File OBJECTS_DIR = join(GITLET_DIR, "objects");
    static final File REFS_DIR = join(GITLET_DIR, "refs");
    static final File HEADS_DIR = join(REFS_DIR, "heads");
    static final File HEAD_FILE = join(GITLET_DIR, "HEAD");
    static final File INDEX_FILE = join(GITLET_DIR, "index");
    static final File MASTER_FILE = join(HEADS_DIR, "master");

    static void initialize() {
        if (isInitialized()) {
            throw error("A Gitlet version-control system already exists in the current directory.");
        }
        GITLET_DIR.mkdir();
        OBJECTS_DIR.mkdir();
        REFS_DIR.mkdir();
        HEADS_DIR.mkdir();
        String content = getRelativePath(GITLET_DIR, MASTER_FILE);
        writeContents(HEAD_FILE, content);
        Commit.INIT.writeAsHead();
        Index.clear();
    }

    static void stage(File file) {
        if (!file.isFile()) {
            throw error("File does not exist.");
        }
        Commit headCommit = Commit.getHead();
        Index index = Index.read();
        if (headCommit.contains(file)) {
            index.remove(file);
        } else {
            index.stage(file);
        }
        index.write();
    }

    static void commit(String message) {
        Index index = Index.read();
        if (index.isEmpty()) {
            throw error("No changes added to the commit.");
        }
        if (message.isBlank()) {
            throw error("Please enter a commit message.");
        }
        Commit commit = new Commit(message);
        commit.writeAsHead();
        Index.clear();
    }

    static void unstage(File file) {
        Commit headCommit = Commit.getHead();
        Index index = Index.read();
        if (!index.isTracking(file) && !headCommit.istracking(file)) {
            throw error("No reason to remove the file.");
        }
        index.unstage(file);
        index.write();
    }

    static String getLog() {

        throw new RuntimeException("Not implemented!");
    }

    static String getGlobalLog() {

        throw new RuntimeException("Not implemented!");
    }

    static String[] getCommitIDs(String message) {

        throw new RuntimeException("Not implemented!");
    }

    static String getStatus() {

        throw new RuntimeException("Not implemented!");
    }

    static void createBranch(String branch) {

    }

    static void removeBranch(String branch) {

    }

    static void reset(String id) {

    }

    static void mergeBranch(String branch) {

    }

    static void checkoutToBranch(String branch) {

    }

    static void checkoutFile(File file) {

    }

    static void checkoutFileToCommit(File file, String id) {

    }

    static boolean isInitialized() {
        return GITLET_DIR.isDirectory();
    }

    static File getHeadFile() {
        String path = readContentsAsString(HEAD_FILE);
        return join(GITLET_DIR, path);
    }

    static File getBranchFile(String branch) {
        File file = join(HEADS_DIR, branch);
        String path = readContentsAsString(file);
        return join(GITLET_DIR, path);
    }

    private static String getRelativePath(File dir, File file) {
        return dir.toPath().relativize(file.toPath()).toString();
    }
}
