package gitlet;

import java.io.File;

import static gitlet.Utils.join;

public class Test {
    public static void main(String[] args) {
        File file = join(Repository.CWD, "lab1");
        System.out.println(file.getPath());
    }
}
