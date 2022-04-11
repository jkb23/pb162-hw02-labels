Homework assignment no. 2, Labels
====================================

**Publication date:**  April 8rd, 2022
**Submission deadline:** April 29th, 2022
**Point:** 12 points maximum (7 for functionally correct solution, 5 for architecture)
**Bonus Points:** 3 (can only compensate lost points from homeworks and iterations)

Change Log
-----------
* 11.4: Added point information and note about operators in grammar section 
* 11.4: Test cleanup

General Information
-------------------
The goal of this homework is to implement a library capable of matching and filtering labeled items based on a simplified boolean expressions.

### Labeled Object

Labeled object is an instance of class implementing the provided ``HasLabels`` interface.

```java
public interface HasLabels {
    Set<String> getLabels();
}
```

A label is then a simple ``String``.

The ``Article`` class found in test sources is an example implementation of ``HasLabels``.

### Expression Language Examples and Interpretation

```text
# has label "animals"
animals

# does not have label "bees"
!bees

# has label "animals" or doesn't have label "bees"
animals | !bees

# has labels "animals" and "bees" or doesn't have label "pandas" 
animals & bees | !pandas

# has label "animals" and does not have label "bees" and  does not have label "pandas" 
animals & !bees & !pandas
```

### Expression Grammar

The following is the [EBNF grammar](https://en.wikipedia.org/wiki/Extended_Backus–Naur_form) of the supported expression language.

```text
expr    =   label | expr, op, expr | not, expr ;
not     =   "!" ;
op      =   space, ( "&" | "|" ), space ;
label   =   ? one or more alphanumerical characters ? ;
space   =   ? zero or more space characters ? ; 
```

*Note: I fully admit that the grammar may be flawed*

*Important:* For simplicity, both operations `&` and `|` are left associative with the same operator priority.   

### Bonus Assignment

For bonus points you can also support parentheses.

Example:

```text
# has labels "animals" and "bees" or doesn't have label "pandas" 
(animals & bees) | !pandas

# has label "animals" and has label "bees" or doesn't have label "pandas" 
animals & (bees | !pandas)

# has label "animals" and does not have label "bees" and  does not have label "pandas" 
animals & (!bees & !pandas)

# has label animals and does not have labels "bees" and "pandas" 
animals & !(bees | pandas)
```

Extended grammar:

```text
expr    =   label | expr, op, expr | not, expr; | open, expr, close ;
open    =   "(" ;
close   =   ")" ;
not     =   "!" ;
op      =   space, ( "&" | "|" ), space ;
label   =   ? one or more alphanumerical characters ? ;
space   =   ? zero or more space characters ? ; 
```

*Note: The expressiveness of both languages when interpreted as boolean expressions is equal*

### Project structure

The structure of the project provided as a base for your implementation should meet the following criteria.

1. Package ```cz.muni.fi.pb162.hw02``` contains classes and interfaces provided as a part of the assignment.

- **Do not modify or add any classes or subpackages into this package.**

2. Package  ```cz.muni.fi.pb162.hw02.impl``` should contain your implementation.

- **Anything outside this package will be ignored during evaluation.**
- **Do not change the interface of ``LabeledOperations``` class, you should only implement declared methods**

### Names in this document

Unless fully classified name is provided, all class names are relative to the package ```cz.muni.fi.pb162.hw02``` or ```cz.muni.fi.pb162.hw02.impl``` for classes
implemented as a part of your solution.

### Compiling the project

The project can be compiled and packaged in the same way you already know

```bash
# Without parentheses support
$ mvn clean install

# With parentheses support
$ mvn clean install -Dimpl.bonus=true
```

The only difference is that unlike with seminar project, this time the checks for missing documentation and style violation will produce an error. You can temporarily
disable this behavior when running this command.

```bash
# Without parentheses support
$ mvn clean install -Dcheckstyle.skip=true

# With parentheses support
$ mvn clean install -Dimpl.bonus=true -Dcheckstyle.skip=true
```

You can consult your seminar teacher to help you set the ``checkstyle.skip`` or ``iml.bonus`` properties in your IDE (or just google it).

### Submitting the assignment

The procedure to submit your solution may differ based on your seminar group. However, it should be generally OK to
submit ```target/homework02-2022-1.0-SNAPSHOT-sources.jar``` to the homework vault.

## Implementation

Your solutions must keep and finish the implementation of ``LabeledOperations`` factory class.

