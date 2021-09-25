# Gauss Jordan Algorithm Java

A command line Gauß-Jordan-Algorithm for solving a system of linear equations with three lines and three unknown quantities.

## How to use

If you simply just run the application, you will be asked for all values and you can enter them in the console.

You can also start the application with the following program arguments:
```
-row1  -row2  -row3  -resultsVector 
```
For example:
```
java Gauss-Jordan -row1 1,2.6,3 -row2 4,5,6 -row3 -7.2,8,9 -resultsVector 3,-4,5
```
The linear systems of the example above would look like this:
```
1x + 4y + -7.2z = 3
2.6x + 5y + 8z = -4
3x + 6y + 9z = 5
```