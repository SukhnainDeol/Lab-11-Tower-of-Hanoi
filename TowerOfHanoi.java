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
        TowerOfHanoi solver = new TowerOfHanoi();
        solveTowerOfHanoi();
        System.out.println("apples");
    } // end of main method

    private static int NUM_OF_DISKS = 4;
    private static int NUM_OF_TOWERS = 3;

    // method = somesomething recurssion something find optimal path
    public ArrayList<Integer>[][] solveTowerOfHanoi()
    {
        int moves = 0;
        int disk;
        // optimal moves is always (2^Number of disks) -1
        while(moves < (2^NUM_OF_DISKS)-1)
        {
            // across + over
            // (across, across, over) x 4
            // across
            moves++;
            //solveTowerOfHanoi();
            return towers;
        }
    } // end of solveTowerOfHanoi method
} // end of TowerOfHanoi class
