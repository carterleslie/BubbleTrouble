#Glossary
==========

General terms
-------------
**Person:** A person is any name inputted in the file that is tied to their preferences. Person i would be located at names[i] in the names array. <br />
**Team:** A team consists of at least two people or at max half the class size. <br />
**Happy Team(s):** Teams in which the majority of members are happy to be a part of. <br />
**Happiness:** Happiness is how satisfied or dissatisfied a person is with all of their team members. May be positive or negative depending on if the person wants or does not want to be on a team with their teammate. It is specified in the file if the person wants or does not want to be on a team with other people. <br />
**Individual Happiness:** The happiness rate of each individual in a team. </br >
**Team Happiness:** The overall score of the total happiness of a team. </br >
**Number of people:** Number of people refers to the number of students per class, before students are divided into groups. </br>
**Preferences:** Wanting to work with one person over another. </br >
**Corretness:** The quality or state of being free from error; accuracy. </br >
**Matrix:** A rectangular array of quantities or expressions in rows and columns that is treated as a single entity and manipulated according to particular rules. </br >
**Range:** The area of variation between upper and lower limits of the matrix. </br >
**Unpopularity:** The state or condition of not being wanted as someones preference. </br >
**PageRank:** The pagerank of a person, which is determined by how many people they are pointing to and how many popular people are pointing to them. </br >
**Verbose:** The level of d.ing desired for the running of the code. </br >
**Nodes:** Each individual person is known as a Node in the pagerank. </br >
**Fibonacci:** The Fibonacci Sequence is the series of numbers: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ... The next number is found by adding up the two numbers before it. </br >
**Polynomial Power:** If x is raised to the p, then p is the polynomial power. </br >
**Recaman:** Recaman is the mathematician who created the Recaman sequence, which is used in this project to alter the numbers used by pagerank</br >
**Linear:** Follows the polynomial power of x to the power of p where p = 1. </br >
**Non-Linear:** Follows the polynomial power of x to the power of p where p > 1. </br >
**AdjacencyMatrix:** A matrix in which a person located at names[r] corresponds to row r in the matrix. Row r will contain a 0-6 depending on how much they want someone on their team, the higher the number the more they want this person to be on their team. </br >

Important Functions
--------------------
```createTeams()``` This function creates teams according to the pagerank ordering. The way we did this is by giving the highest ranked person their first available choice, and then the next highest ranked person their first available choice, and so on until each team has two members. Then we go through and give each person that was added to the most recent row their first available choice.  </br >
```calcIndividualHappiness()``` Calculates the happiness for each person, and stores it in the individual happiness array. </br >
```calcTeamHappiness()``` This calculates the total happiness of each team. </br >
```calcTotalHappiness()``` Calculates the total happiness of everyone throughout all teams. </br >
```calcAllHappiness()``` Calls all happiness functions so that they can all be called simultaneously rather than having to all be called implicitly. </br >
```printTeams()``` Prints the names of everyone in each team, their team number, their team happiness, the total happiness, and the number of people who got their [first choice, second choice,.., someone who was not their choice]. </br >
```fillAdjacencyMatrix()``` This function fills the adjacencyMatrix. </br >
```fillPagerankMatrix()``` This function fills the pagerank matrix which applies either a polynomial power or a sequence number to every index to adjacencyMatrix. </br >

Test Functions
---------
```TestZeroPreferences() ``` Tests to make sure the matrix works when someone has zero preferences. </br >
```TestDiagonalZeros()``` Tests to make sure the diagonal is always zeros. </br >
```TestCorretnessOfMatrixX()``` Tests the correctness of the adjacency matrix. </br >
```TestRangeX()``` Tests to make sure the matrix is the proper range. </br >
```TestFinalTotalPageRankX()``` Tests to make sure final total pagerank is always correct. </br >
```TestFinalTotalPageRankLessThanOneX()``` Tests to make sure final total pagerank is less than one when it should be. </br >
```TestFinalIndividualPageRankX()``` Tests to make sure the final individual pagerank values are correct. </br >
```TestVerboseX()``` Tests the verbose level</br >
```TestNumNodesVsPeopleX()``` Tests to make sure numNodes is always equal to numPeople</br >
```TestNumNodesVsPeopleFailsX()``` Tests to make sure numNodes will always be equal to numPeople even in edge cases. </br >
```TestTeamSizeX()``` Tests to make sure the team size works correctly. </br >
```TestTeamSizeReducesX()``` Tests to make sure the team size reduces accordingly. </br >
```TestTeamSizeExpandsX()``` Tests to make sure the team size expands accordingly. </br >
```TestTeamSizeFailsX()``` Tests to see if certain things will handle edge cases for the team size. </br >
```TestPolynomialPowerOfX()``` Tests a polynomial to the power of x. </br >
```TestSequenceFibonacci()``` Tests the fibonacci sequence. </br >
```TestSequenceRecaman()``` Tests the Recaman sequence. </br >
```TestSequencePasccal()``` Tests the Pascal sequence. </br >
```TestSequenceOverridePolynomial()``` Tests to make sure the sequence key overrides a polynomial. </br >
```TestIndividualHappiness()``` Tests to make sure individual happiness values are correct</br >
```TestTeamHappiness()``` Tests to make sure team happiness values are correct correct. </br >
```TestTotalHappiness()``` Tests to make sure the total happpiness is correct. </br >