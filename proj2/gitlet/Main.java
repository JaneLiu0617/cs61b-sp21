package gitlet;

/**
 * Driver class for Gitlet, a subset of the Git version-control system.
 *
 * @author st3v3
 */
public class Main {

    /**
     * Usage: java gitlet.Main ARGS, where ARGS contains
     * <COMMAND> <OPERAND1> <OPERAND2> ...
     */
    public static void main(String[] args) {

        Gitlet process = new Gitlet(args);
        try {
            process.run();
        } catch (GitletException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
