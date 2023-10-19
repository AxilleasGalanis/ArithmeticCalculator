# Arithmetic Calculator
An arithmetic calculator made in Java using Binary Trees. The program caclulates complex arithmetic expressions that include:

1. **Parentheses:** indicate the priority of operations.
2. **The symbols of the operators:**
      - \+ (addition), (eg. 3.3 + 2.2).
      - \- (subtraction), (eg. 3.3 - 2.2).
      -  x or \* (multiplication), (eg. 3.3 x 2.2 or 3.3 * 2.2).
      -  / (division), (eg. 3.3 / 2.2).
      -  ^ (exponentiation) (eg. 3.3^2.2)

3. **Positive numbers (integer or floating point):** All calculations are done between floating point numbers.
4. **Space characters** ([ \t]) between parentheses, operators and numbers.

An example of a complex numerical expression is the following:
```
5 + (((3.3 + 6.6) * 9.2 ) + 12,546) * 2,323 +
( ( ( 33.3 + 2342.1 ) * 55.555 ) - 10000.009 ) + 11.334 * 2.3 ^3.
```
The program does the following:
1. Prints the message "Expression: " and reads a numeric expression from the keyboard.
2. Reads a string that consists of one of the following choices:
      - -s: prints to stdout “Postfix: ” and the equivalent expression in postfix format (of the equivalent Binary Tree).
      - -c: prints to stdout “Result: ” and the result of the numeric expression up to 6 decimal places.
      - -d: prints to stdout the equivalent expression in a format suitable for todot program of the graphviz suite.
