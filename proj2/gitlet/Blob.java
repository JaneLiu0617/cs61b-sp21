package gitlet;

class Blob implements GitObject {

    private final String content;

    Blob(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Blob)) {
            return false;
        }
        Blob o = (Blob) obj;
        return o.getID().equals(this.getID());
    }

    @Override
    public int hashCode() {
        return getID().hashCode();
    }
}
