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

    static boolean isInitialized() {
        return GITLET_DIR.isDirectory();
    }

    static void initialize() {
        if (isInitialized()) {
            throw error("A Gitlet version-control system already exists in the current directory.");
        }
        GITLET_DIR.mkdir();
        OBJECTS_DIR.mkdir();
        REFS_DIR.mkdir();
        HEADS_DIR.mkdir();
        writeContents(HEAD_FILE, "refs/heads/master");
        writeContents(MASTER_FILE, Commit.createInitCommitFile());
        new StageReference().writeToIndex();
    }

    static void addFile(String fileName) {
        File file = join(CWD, fileName);
        if (!file.isFile()) {
            throw error("File does not exist.");
        }
        Commit headCommit = getHeadCommit();
        String content = readContentsAsString(file);
        Blob blob = new Blob(fileName, content);
        StageReference stageRef = getStageRef();
        if (headCommit.contains(blob)) {
            stageRef.remove(fileName);
        } else {
            stageRef.add(blob);
        }
        stageRef.writeToIndex();
    }

    static void commit(String message) {

    }

    static void removeFile(String fileName) {

    }

    static String getLog() {

        throw new RuntimeException("Not implemented!");
    }

    static String getGlobalLog() {

        throw new RuntimeException("Not implemented!");
    }

    static String[] getCommitIDByMsg(String message) {

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

    private static String getHeadCommitID() {
        String pathToHead = readContentsAsString(HEAD_FILE);
        File headFile = join(GITLET_DIR, pathToHead);
        return readContentsAsString(headFile);
    }

    private static Commit getHeadCommit() {
        String headCommitID = getHeadCommitID();
        return (Commit) GitObject.readObjectFile(headCommitID);
    }

    private static StageReference getStageRef() {
        return readObject(INDEX_FILE, StageReference.class);
    }
}
