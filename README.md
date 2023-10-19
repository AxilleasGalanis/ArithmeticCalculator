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
      - -d: prints to stdout the equivalent expression in a format suitable for the dot program of the [graphviz](https://dreampuf.github.io/GraphvizOnline/#digraph%20G%20%7B%0A%0A%20%20subgraph%20cluster_0%20%7B%0A%20%20%20%20style%3Dfilled%3B%0A%20%20%20%20color%3Dlightgrey%3B%0A%20%20%20%20node%20%5Bstyle%3Dfilled%2Ccolor%3Dwhite%5D%3B%0A%20%20%20%20a0%20-%3E%20a1%20-%3E%20a2%20-%3E%20a3%3B%0A%20%20%20%20label%20%3D%20%22process%20%231%22%3B%0A%20%20%7D%0A%0A%20%20subgraph%20cluster_1%20%7B%0A%20%20%20%20node%20%5Bstyle%3Dfilled%5D%3B%0A%20%20%20%20b0%20-%3E%20b1%20-%3E%20b2%20-%3E%20b3%3B%0A%20%20%20%20label%20%3D%20%22process%20%232%22%3B%0A%20%20%20%20color%3Dblue%0A%20%20%7D%0A%20%20start%20-%3E%20a0%3B%0A%20%20start%20-%3E%20b0%3B%0A%20%20a1%20-%3E%20b3%3B%0A%20%20b2%20-%3E%20a3%3B%0A%20%20a3%20-%3E%20a0%3B%0A%20%20a3%20-%3E%20end%3B%0A%20%20b3%20-%3E%20end%3B%0A%0A%20%20start%20%5Bshape%3DMdiamond%5D%3B%0A%20%20end%20%5Bshape%3DMsquare%5D%3B%0A%7D) suite.
