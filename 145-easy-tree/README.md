# Tree Generation
[http://www.reddit.com/r/dailyprogrammer/comments/1t0r09/121613_challenge_145_easy_tree_generation/](http://www.reddit.com/r/dailyprogrammer/comments/1t0r09/121613_challenge_145_easy_tree_generation/)

Your goal is to draw a tree given the base-width of the tree (the number of characters on the bottom-most row of the triangle section). This "tree" must be drawn through ASCII art-style graphics on standard console output. It will consist of a 1x3 trunk on the bottom, and a triangle shape on the top. The tree must be centered, with the leaves growing from a base of N-characters, up to a top-layer of 1 character. Each layer reduces by 2 character, so the bottom might be 7, while shrinks to 5, 3, and 1 on top layers. See example output.

# Formal Inputs & Outputs
## Input Description
You will be given one line of text on standard-console input: an integer and two characters, all space-delimited. The integer, N, will range inclusively from 3 to 21 and always be odd. The next character will be your trunk character. The next character will be your leaves character. Draw the trunk and leaves components with these characters, respectively.
## Output Description
Given the three input arguments, draw a centered-tree. It should follow this pattern: (this is the smallest tree possible, with a base of 3)

     *
    ***
    ###

Here's a much larger tree, of base 7:

       *
      ***
     *****
    *******
      ###

