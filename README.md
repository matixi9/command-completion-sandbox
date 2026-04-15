# Command Completion Sandbox & Testing Report


## Environment:
* **IDE:** IntelliJ IDEA 2026.1 (Build #IU-261.22158.277)
* **JDK:** 26
* **Language:** JAVA
* **Hardware:** ASUS Vivobook S14, 16GB RAM, Intel® Core™ Ultra 5 226V

---

# Test Cases:

### TC-01: Replace to text block behavior inside array initializers

* **Steps:**
1. Define a string array: `String[] names = {"Josh", };`
2. Trigger command completion on the string literal `"Josh"`
3. Select the "Replace with text block" command

* **Expected result:**
The string is converted to a text block with proper indentation, preserving the array's readability on a single line (if possible).

* **Actual result:**
The IDE converts it to """\nJosh""" which introduces awkward formatting and unnecessary line breaks inside a single line array.
IDE will compile it correctly but it affects readability.


### TC-02: Basic statement completion and formatting

* **Steps:**
1. Write a basic print statement without a semicolon e.g.: `System.out.println("Test")`
2. Trigger the command completion popup.
3. Select the default completion suggestion (adding the semicolon).

* **Expected result:**
The IDE seamlessly completes the statement and appending the missing semicolon, applies proper line formatting, and moves the cursor to end of the statement.

* **Actual result:**
The statement is correctly completed with a semicolon and formatted. However, the cursor remains positioned **before** the newly added semicolon, rather than moving past it. This might disrupt the typing flow if the user wants to immediately start a new line. 

### TC-03: "Create local variable" action for an unresolved symbol

* **Steps:**
1. Write a statement using an undeclared variable, e.g., `System.out.println(hello);`
2. Place the cursor on the unresolved symbol `hello`.
3. Trigger the command completion popup.
4. Select the "Create local variable 'hello'" command.

* **Expected result:**
  The IDE should automatically generate a local variable declaration for `hello` above the current line, correctly inferring the context, and allow immediate assignment of a value

* **Actual result:**
  As expected. The IDE successfully generates the variable declaration and places the cursor ready to assign its value.


### TC-04: Code Transformation - "Surround with try-catch"

* **Steps:**
1. Write a statement that throws a checked exception, e.g.: `Files.readAllLines(Path.of("test.txt"));`
2. Trigger the command completion popup on the statement.
3. Select the "Surround with try-catch" command.

* **Expected result:**
  The IDE correctly wraps the statement in a `try-catch` block, automatically identifying the required `IOException` and adding the necessary imports.

* **Actual result:**
  As expected. The transformation is executed seamlessly, generating syntactically correct code and properly handling the checked exception type.

---

# Bug Reports:

### BUG-01: [Performance] Command completion experiences a ~0.5sec delay

* **Severity:**
Minor/UX

* **Steps:**
1. Write any valid java statement e.g.: `String test = "Test";`
2. Trigger any completion command popup
3. Observe the loading time of the suggested commands

* **Expected result:**
The full list of completion command  suggestions loads immediately

* **Actual result:**
Initially, only 2-3 commands pop up. After about a 0.5-second delay the rest of the suggestions appear. On a fresh IDE start, delay can reach up to 2 seconds.

* **Note:** This delay was observed on a machine with Intel® Core™ Ultra 5 226V and a 16GB RAM.


### BUG-02: IDE shows more completion options for broken code than for correct one

* **Severity:** Minor

* **Steps:**
1. Write a variable declaration **without** a semicolon: `int i = 0`
2. Trigger command completion and look at the suggestions
3. Write another variable with a semicolon: `int j = 0;` and trigger command completion again
4. Compare suggestions

* **Expected result:** IDE should prioritize fixing the syntax error (Insert ';') and limit complex refactoring suggestions that require a valid AST (Abstract Syntax Tree).
* **Actual result:** When the semicolon is missing, the IDE displays a bloated list of irrelevant refactoring actions. These suggestions disappear once the code is syntactically correct:
    * **Irrelevant suggestions only present without ';':** *Extract Method*, *Introduce Constant*, *Introduce Field*, *Introduce Parameter*, *Introduce Variable*, *Type Info*.
    * **Visual noise:** These actions distract from the primary fix (*Insert ';'*) and may fail or behave unpredictably due to incomplete syntax.
---

### Future Scope:
To ensure the reliability of command completion further testing should cover:
* **Performance Under Heavy Load:** Testing response times and memory consumption in massive enterprise projects (files with >10 000 lines of code) to ensure no UI freezes occur.
* **Plugin Interoperability:** Verifying if command completion conflicts with other heavily used plugins that manipulates code in the editor e.g. Lombok, Spring Boot tools.
* **Cross Language Behavior:** Evaluating completion accuracy in mixed Java/Kotlin projects
* **Complex Code Structures** Testing suggestions inside deeply nested generic types, complex Stream API chains and advances lambda expressions.