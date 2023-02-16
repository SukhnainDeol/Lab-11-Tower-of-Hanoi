// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/13/2023
// Assignmnet: Lab 5: Tower Of Hanoi

// Purpose: 

// Notes
    // If even 1st disc starts on 2nd tower, if odd, on 3rd tower
    //Min moves scales by (2^NUM_OF_DISKS)-1
    // loop until tower 3 has 1,2,3,4
    // once tower 2 or 3 has an ordered stack (e.g 1,2,3) add a new stack from 1st to empty one
    // if an ordered stack has even number disks, move top to 3rd tower, else 2nd
    // pattern?: across, over loop(across across, over) across

// To DO:
    // pre/post condition
    // everything

import java.util.*;

class TowerOfHanoi
{
    private static int NUM_OF_DISCS = 4;
    private static int NUM_OF_TOWERS = 3;

    // method = somesomething recurssion something find optimal path

} // end of TowerOfHanoi class
