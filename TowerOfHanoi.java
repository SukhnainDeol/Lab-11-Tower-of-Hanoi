// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/13/2023
// Assignmnet: Lab 5: Tower Of Hanoi

// Purpose: 

// Notes
    // If even 1st disc starts on 2nd tower, if odd, on 3rd tower
    // loop (2^NUM_OF_DISKS)-1 times
    // once tower 2 or 3 has an ordered stack (e.g 1,2,3) add a new stack from 1st to empty one
    // if an ordered stack has even number disks, move top to 3rd tower, else 2nd

    // pattern?: (start with across->over if even, over_across if odd),
    // loop(across across, over) till last move, (end with over if odd, across, if even)

    // always move to +1 disk?

// To DO:
    // pre/post condition
    // everything

import java.util.*;

class TowerOfHanoi
{
    public static void main(String[] args)
    {
        solveTowerOfHanoi();
        System.out.println("apples");
    } // end of main method

    private static int NUM_OF_DISKS = 4;
    private static int NUM_OF_TOWERS = 3; // prob remove



    public static void solveTowerOfHanoi()
    {
        // each disk starts at first tower
        int[] diskTowers = {0,0,0,0};
        solveTowerOfHanoi(4, 1, diskTowers);
    }



    // method = somesomething recurssion something find optimal path
    private static void solveTowerOfHanoi(int disks, int round, int[] diskTowers)
    {
        // optimal moves is always (2^Number of disks) -1

        // 1 << disks is equal to 2^disks
        final int maxRounds = (1 << disks)-1;

        int movedDisk = 0;
        int move = 1;

        // if on move%3 -2, move over 2
        if((round % 3) -2 == 0)
            {move = 2;}
        
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
            {movedDisk = 2;}
        else // else odd
            {movedDisk = 1;}
        
        diskTowers[movedDisk-1] = (diskTowers[movedDisk-1] + move) % 3;
        char moveTower = (char)('a' + diskTowers[movedDisk-1]);

        System.out.println("Move Disk " + movedDisk + " to Tower " + moveTower + ".");
        if(round < maxRounds) // recurses if max moves not done
            {solveTowerOfHanoi(disks, round+1, diskTowers);}
    } // end of solveTowerOfHanoi method
} // end of TowerOfHanoi class
