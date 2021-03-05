package gitlet;

class Blob extends GitObject {

    private final String fileName;
    private final String content;

    Blob(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    String getFileName() {
        return fileName;
    }
}
