# Command Completion Sandbox & Testing Raport


## Environment:
* **IDE** IntelliJ IDEA 2026.1 (Build #IU-261.22158.277)
* **JDK** 26
* **Language** JAVA

---

# Test Cases:

### TC-01: Replace to text block behavior inside array initializers

* #### Steps:
1. Define a string array: `String[] names = {"Josh", };`
2. Trigger command completion on the string literal `"Josh"`
3. Select the "Replace with text block" command

* #### Expected result:
The string is converted to a text block with proper indentation, preserving the array's readability on a single line (if possible).

* #### Actual result:
The IDE converts it to """\nJosh""" which introduces awkward formatting and unnecessary line breaks inside a single line array.
IDE will compile it correctly but it affects readability.

---

# Bug Reports:

### BUG-01: [Performance] Command completion experiences a ~0.5sec delay

* #### Severity:
Minor/UX

* #### Steps:
1. Write any valid java statement eg.: `String test = "Test";`
2. Trigger any completion command popup
3. Observe the loading time of the suggested commands

* #### Expected result:
The full list of completion command  suggestions loads immediately

* #### Actual result:
Initially, only 2-3 commands pop up. After about a 0.5 second delay the rest of the suggestions appear. On a fresh IDE start, delay can reach up to 2 seconds.

---

### Future Scope:
* Performance under heavy load