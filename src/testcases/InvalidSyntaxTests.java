package testcases;

public class InvalidSyntaxTests {
    public static void main(String[] args) {
        // BUG-02: Missing semicolon behavior
        if (true) {
            int i = 0;
            // Action: Remove the semicolon from 'int j = 0;' and trigger command completion
            // to observe bloated refactoring suggestions vs simple 'Insert ;'
            int j = 0;
        }
    }
}
