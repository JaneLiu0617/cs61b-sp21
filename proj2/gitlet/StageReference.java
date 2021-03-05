package gitlet;

import java.util.HashSet;
import java.util.Set;

import static gitlet.Utils.writeObject;

public class StageReference extends Reference{

    private final Set<String> removeSet;

    StageReference() {
        super();
        this.removeSet = new HashSet<>();
    }

    void writeToIndex() {
        writeObject(Repository.INDEX_FILE, this);
    }

    @Override
    void remove(String fileName) {
        super.remove(fileName);
    }
}
