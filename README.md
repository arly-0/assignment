# Java 20
# Comment on algorithm

Before starting to code, I had two approach ideas:

- load everything in memory and then process (as it's done)
- read and process data on the fly

In-memory way is technically acceptable for this test assignment because of small data size. However, it's clearly not suitable for enterprise-scale application. I knew it before starting to code but was more focused on figuring out how to better design the betting part of the solution and code structure. As a result, I didn't really dive into and forgot about on the fly approach. 

As I was progressing through the logic, I started thinking why did you emphasise that ``player_data.txt`` is grouped by player ID. When I've finished the task, I took a day off to reflect, write a README and ask Darja whether you'd like to see some tests or no. 

It was when I started writing a README I finally understood the enterprise-suitable way (and purpose of id-grouping) to approach the problem I was trying to think of in the beginning. I created a new branch and tried to rewrite the app, wasted half the Saturday and got lost. Not sure whether I was overcomplicating, expecting things to work the way they don't or simply didn't figure it out. 

## I understood that the efficient way is one of this:

### Option 1 (erratic in terms of data structures or I didn't find a good way to do it):

Read players line by line and for each bet operation lookup match with another Scanner/Stream/BufferedReader. 

### Option 2

As players are grouped by id, read chunk -> loop over it for match lookup (if it's BET operation) with another Scanner/Stream/BufferedReader and process operation -> write to result.txt -> go to next chunk. For each chunk create one player object + list of operations. 

At the moment of writing this option seems more clear to me then when I was actually trying to implement any of them and confused myself in-between. 


# Other things I realised while trying to rewrite
- It'd be better to put ``isLegal`` flag on the player rather than on every single operation 
- With previous done, it's possible to save only illegal operation needed for output, if next is true
- There is a better mathematical way to calculate casino balance than one more loop
- I had to come up with a reason to not write tests, so here it is: 
    - let's agree that data you provided serves as a feature test
- Maybe something else that I forgot


# END
I want to touch some grass on Sunday, so I'm leaving everything as it is. But I'm actually curious to discuss the proper implementation of **Option 2** during the interview.