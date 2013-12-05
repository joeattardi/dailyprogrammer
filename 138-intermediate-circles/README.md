Challenge 138 [Intermediate]: Overlapping Circles
=================================================
[http://www.reddit.com/r/dailyprogrammer/comments/1s6484/120513_challenge_138_intermediate_overlapping/](http://www.reddit.com/r/dailyprogrammer/comments/1s6484/120513\_challenge\_138\_intermediate\_overlapping/)

Computing the volume of a circle is pretty straight-forward: Pi x Radius x Radius, or simply Pi x r^2.

What if we wanted to computer the volume of two circles? Easy, just sum it! Yet, what about two intersecting circles, much like the classic Venn diagram?

Your goal is to write a program that takes two unit-circles (radius of one) at given locations, and compute that shape's volume. You must make sure to not double-count the intersecting volume!

As a starting point, check out how to compute [http://en.wikipedia.org/wiki/Circular_segment](circle segments).

## Formal Inputs & Outputs
### Input Description
On standard input you will be given four floating-point space-delimited values: x y u w. x and y are the first circle's position in Cartesian coordinates. The second pair u and w are the second circle's position.

Note that the given circles may not actually intersect. If this is the case, return the sum of both circles (which will always be Pi x 2 since our circles are unit-circles).

### Output Description
Print the summed volume of the two circles, up to an accuracy of 4 digits after the decimal place.

## Sample Inputs & Outputs
### Sample Input
`-0.5 0 0.5 0`
### Sample Output
`5.0548`
