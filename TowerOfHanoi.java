// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/13/2023
// Assignmnet: Lab 5: Tower Of Hanoi

// Purpose: Print the optimal steps to solve a tower 
// of hanoi with 3 towers and 4 disks with recursion



// To DO:
    // pre/post condition
    // make dynamic
    // formula for ints in between 1 2 
        // recursive method?
    // formula for when round is is +2 move instead of 1
        // recursive method?
    // need ASCII

import java.util.*;

class TowerOfHanoi
{
    public static void main(String[] args)
    {
        boolean isPositiveInt = false;
        Scanner in = new Scanner(System.in);
        int disks = 1; // placeholder value for number of disks

        // loop asks for number of disks tower to solve
        while(!isPositiveInt)
        {
            System.out.println("How many disks to solve for Tower of Hanoi?: ");

            if(in.hasNextInt()) // if int
            {
                disks = in.nextInt(); // take int

                if(disks > 0) // if above 0
                    {isPositiveInt = true;} // end loop
                else // error
                    {System.out.println("ERROR: Please Input a Positive Whole Number");}
            }
            else // error
                {System.out.println("ERROR: Please Input a Positive Whole Number");}
        } // end of input loop

        // solve Tower of Hanoi for given number of disks
        solveTowerOfHanoi(disks); 
    } // end of main method



    // parameter : disk - number of disks on tower of hanoi

    // pre : disks > 0 
    // post : return print statements of optimal moves to solve tower of
           // hanoi with 3 towers for given number of disks starting from
           // the given round
    public static void solveTowerOfHanoi(int disks)
    {
        // array index = disk (0-(disks-1))
        // array value = tower (0-2)
        int[] diskTowers = new int[disks];
        
        for(int i: diskTowers) // assign each index to 0
            {i = 0;}
        
        solveTowerOfHanoi(disks, 1, diskTowers);
    } // end of public solveTowerOfHanoi method



    // parameters: 
        // disk - number of disks on tower of hanoi
        // round - round of game
        // diskTowers - array with tower value (0-2) for each disk (each index is a disk)

    // pre : disks > 0, 
          // 0 < round <= (2^disks)-1, 
          // diskTower is at optimal position for
          // the round number and disks (0 for all values if starting)
    // post : return print statements of optimal moves to solve tower of
          // hanoi with 3 towers for given number of disks starting from
          // the given round
    private static void solveTowerOfHanoi(int disks, int round, int[] diskTowers)
    {   
        // optimal moves is always (2^Number of disks) -1
        // 1 << disks is equal to 2^disks
        final int maxRounds = (1 << disks)-1;

        int movedDisk = 0; // disk that is being moved
        int move = 1; // how many tower its is moving by

        // if on move%3 -2, move over 2 instead of 1
        switch(round)
        {
            case 2:
            case 6:
            case 8:
            case 10:
            case 14:
                move = 2;
                break;
        }
        
        // move a certain disk based on the pattern
        // if multiple of 4, move different amount
        if(round % 4 == 0)
        {
            switch(round)
            {
                case 4:
                case 12:
                    movedDisk = 3;
                    break;
                case 8:
                    movedDisk = 4;
                    break;
            }
        }
        else if (round % 2 == 0) // if multiple of 2 but not 4
            {movedDisk = 2;} // move disk 2
        else // else (odd number)
            {movedDisk = 1;} // move disk 1

        // location of disk before it moves
        char startTower = (char)('a' + diskTowers[movedDisk-1]);

        // move select disk's tower value by move var (move value depends on round)
        // if value is above 2 it loops back around to 0 (towers are 0-2)
        diskTowers[movedDisk-1] = (diskTowers[movedDisk-1] + move) % 3;

        // translates disk's int value into a char
        char moveTower = (char)('a' + diskTowers[movedDisk-1]);

        System.out.println("Move Disk " + movedDisk + " From Tower "+startTower+" to Tower " + moveTower + ".");
        if(round < maxRounds) // if less rounds is less than max
            {solveTowerOfHanoi(disks, round+1, diskTowers);} // recurse with next round
    } // end of private solveTowerOfHanoi method
} // end of TowerOfHanoi class