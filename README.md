# Algorithms
just learning. And recording some notes for algorithms.  
Cost model: Number of array accesses (for read or write).  

algorithm  | initialize | union | find |
--------- | --------| ---------| ---------|
quick-find  | N | N | 1 | 
quick-union | N | N+ | N |
weighted QU | N | lgN+ | lgN |

quick_find:order of growth of number of array accesses.

Quick-find defect  
Union too expensive(N array access)  
Trees are flat, but too expensive to keep them flat.  

Quick-union defect  
Trees can get tall.  
Find too expensive(could by N array accessed).  

algorithm  |  worst-case time
--------- | ---------- |
quick-find | M N |
quick-union | M N |
weighted QU | N + M log N |
QU + path compression | N + M log N |
weighted QU + path compression | N + M lg* N |
M union-find operation on a set of N objects
