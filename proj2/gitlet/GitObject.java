package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.*;

abstract class GitObject implements Serializable {

    static GitObject readObjectFile(String sha1) {
        File dir = join(Repository.OBJECTS_DIR, sha1.substring(0, 2));
        File file = join(dir, sha1.substring(2));
        return readObject(file, GitObject.class);
    }

    String createObjectFile() {
        String sha1 = getID();
        File dir = join(Repository.OBJECTS_DIR, sha1.substring(0, 2));
        File file = join(dir, sha1.substring(2));
        dir.mkdir();
        writeObject(file, this);
        return sha1;
    }

    String getID() {
        return sha1(serialize(this));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GitObject)) {
            return false;
        }
        GitObject o = (GitObject) obj;
        return sha1(serialize(o)).equals(sha1(serialize(this)));
    }

    @Override
    public int hashCode() {
        return sha1(serialize(this)).hashCode();
    }
}
