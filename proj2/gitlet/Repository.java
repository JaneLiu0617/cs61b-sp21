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
        clearIndex();
    }

    static void stage(String fileName) {
    }

    static void commit(String message) {
        GitMap index = readIndex();
        Commit headCommit = getHeadCommit();
        if (headCommit.getMap().equals(index)) {
            throw error("No changes added to the commit.");
        }
        if (message.isBlank()) {
            throw error("Please enter a commit message.");
        }
        Commit commit = new Commit(message);
        commit.writeAsHead();
        clearIndex();
    }

    static void unstage(String fileName) {

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

    static void createBranch(String name) {

    }

    static void removeBranch(String name) {

    }

    static void reset(String id) {

    }

    static void mergeBranch(String name) {

    }

    static void checkoutToBranch(String name) {

    }

    static void checkoutFile(String fileName) {

    }

    static void checkoutFileToCommit(String fileName, String id) {

    }

    static boolean isInitialized() {
        return GITLET_DIR.isDirectory();
    }

    static File getHeadCommitFile() {
        String path = readContentsAsString(HEAD_FILE);
        return join(GITLET_DIR, path);
    }

    static File getBranchCommitFile(String branch) {
        File file = join(HEADS_DIR, branch);
        String path = readContentsAsString(file);
        return join(GITLET_DIR, path);
    }

    private static String getRelativePath(File dir, File file) {
        return dir.toPath().relativize(file.toPath()).toString();
    }

    private static void clearIndex() {
        Commit headCommit = getHeadCommit();
        GitMap map = new GitMap(headCommit.getMap());
        writeObject(INDEX_FILE, map);
    }

    static GitMap readIndex() {
        return readObject(INDEX_FILE, GitMap.class);
    }

    private static Commit getHeadCommit() {
        String id = getHeadID();
        return (Commit) GitObject.read(id);
    }

    private static Commit getBranchCommit(String branch) {
        String id = getBranchID(branch);
        return (Commit) GitObject.read(id);
    }

    static String getHeadID() {
        File file = getHeadCommitFile();
        return readContentsAsString(file);
    }

    static String getBranchID(String branch) {
        File file = getBranchCommitFile(branch);
        return readContentsAsString(file);
    }
}
