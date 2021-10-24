Gift store offers 2 kind of gift, A and B. They distributed some tickets to their guests. 
The guest who got A can only pick up gift A, and who got B can only pick up gift B.
The total number of gifts and tickets are same.

The gifts are placed in a stack, and the guests made a queue to pick up the gift from the stack. 
In each step, if the guest have the ticket that is same as the gift on the top of the stack,
they will take it and leave; otherwise, they will go to the queue's end.
This continues until no guest can pick up the top-stack gift, or when all gifts are taken.

You are given two arrays guests and gifts, where gifts is the type of 
th i-th gift in the stack (i = 0 is the top of the stack) and guests[j] is the j-th guests' ticket (j=0 is the front of the queue)
Return the number of guests that are unable to get a gift.

Input: guests = ['A','A','B','B'], gifts = ['B','A','B','A']
Output: 0

Input: guests = ['A','A','A','B','B','A'], gifts = ['A','B','B','B','A','A']
Output: 3
