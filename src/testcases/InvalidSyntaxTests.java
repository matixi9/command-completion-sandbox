package testcases;

public class InvalidSyntaxTests {
    public static void main(String[] args) {
        //Test case for BUG-02: Missing semicolon behavior

        if () {
            int i = 0;
            int j = 0
        }
    }
}
