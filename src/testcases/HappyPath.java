package testcases;

public class HappyPath {
    public static void main(String[] args) {
        // TC-02: Basic statement completion (missing semicolon)
        // Action: Delete the semicolon at the end of the line below and trigger command completion
        System.out.println("Test");

        // Test case for TC-03: Create local variable
        // Action: Comment out the 'String hello' line, then trigger completion on the 'hello' symbol in println
        String hello = "Hello";
        System.out.println(hello);
    }
}