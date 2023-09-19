# Ten-Year Risk Test Case Generator
This is for zyBooks problem 5.3.1. Instead of manually finding test cases, I decided to generate them.
The problem that this is based on is on the last page of this
document: https://www.nhlbi.nih.gov/sites/default/files/publications/05-3290.pdf

## Explanation

Each generated test case is a person with values that fall in different ranges for their respective category. These
ranges award different amounts of points to indicate one's ten-year risk. The more points, the worse one is off, and
the fewer points, the better one is off.

The test case generation is based on the age ranges being treated as pairs. For example, the age range 20-34 is a pair
of 20 and 34. For all age ranges, the smaller number in the pair is assigned the lowest values of the other categories,
and the larger number in the pair is assigned the largest values of the other categories. Smoking status and treatment
for systolic BP is randomly assigned.

After that, there are 10 random values between each age range, which are assigned random values in the other
categories' ranges, excluding the smallest and largest number. The more randomness, the better, so the male cases and
female cases are generated separately.

In total, this generates 240 test cases.
