# Assignment: Package Challenge

## Solution Description

   Brute force solution to knapsack problem, having complexity 2^n, as maximum number of items are limited to 15, still not a performance problem.
 
   As Dynamic programming solution to knapsack problem requires non-fractional weight values,so to solve challenge with dynamic programming algorithm,
we need to convert all weight values to int, and as precision of fractional weights is not specified in problem,
and if we multiply with 100 for given test case which increases dynamic 2D array size,
brute force solution with 2^n complexity still behaves better when item numbers is limited to 15.



## Problem Description



You want to send your friend a package with different items. You can choose from a number of `N` items. The items are numbered from 1 to `N`. Each one of these items has a given weight and a given cost (in €), where the weights and costs of the items might be different. The package itself has a weight limit. The combined weight of the items you put in the package must not exceed the weight limit of the package, otherwise the package would be too heavy.

Your goal is to determine which items to put in the package so that the total cost of the items you put inside is as large as possible. In case the total cost the of the packaged items is the same for two sets of items, you should prefer the combination of items which has a lower total weight.



## Constraints



1. The maximum weight that a package can hold must be <= 100.

2. There may be up to 15 items you can to choose from.

3. The maximum weight of an item should be <= 100.

4. The maximum cost of an item should be <= €100.



## Program Specification



Write a program, preferably in Java, which can be run on the command line in order to solve this problem. The program should take one command line argument, which contains the path to a text file. This text file should contain several lines, each line describing one test case for the problem.



Each line starts with the maximum weight of the package for this test case. It is followed by ` : ` and then the list of descriptions of the items available for packaging. Each item description contains, in parentheses, the item's number, starting at 1, its weight and its cost (preceded by a € sign).



In case of a constraint violation, your program should indicate this fact to the user, for example by throwing an exception with a descriptive message, allowing the user to address this problem.





