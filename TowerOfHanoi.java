// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/13/2023
// Assignmnet: Lab 5: Tower Of Hanoi

// Purpose: This program will display the optimal move for solving a 
// tower of hanoi puzzle with 3 towers and 4 disks using recursion. 
// It will use both words to describe the movements of each disk and 
// create an ascii text image displaying the movement. 



// To DO:
    // make dynamic
        // formula for ints in between 1 2 
            // recursive method?
        // formula for when round is is +2 move instead of 1
            // recursive method?
        // make display height dynamic

// Revise
    // pre/post conditions 

// DONE
    // input
    // display


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
            System.out.print("How many disks to solve for Tower of Hanoi?: ");

            if(in.hasNextInt()) // if int
            {
                disks = in.nextInt(); // take int

                if(disks > 0) // if above 0
                    {isPositiveInt = true;} // end loop
                else // error
                {
                    System.out.println("ERROR: Please Input a Positive Whole Number\n");
                    in.nextLine(); // clears input for next loop
                }
            }
            else // error
            {
                System.out.println("ERROR: Please Input a Positive Whole Number\n");
                in.nextLine(); // clears input for next loop
            }
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
        
        for(int i = 0; i < diskTowers.length; i++) // assign each index to 0
            {diskTowers[i] = 0;}
        
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
        /*if((round % 6) -2 == 0)
            {move = 2;}
        System.out.println(move);  */
        switch(round)
            {
                case 2:
                case 6:
                case 8:
                case 10:
                case 14:
                    move = 2;
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

        System.out.println("STEP "+ round +": Move Disk " + movedDisk + " From Tower "+startTower+" to Tower " + moveTower + ".");

        displayTowerOfHanoi(diskTowers);
        System.out.println("\n"); // adds 2 line breaks

        if(round < maxRounds) // if less rounds is less than max
            {solveTowerOfHanoi(disks, round+1, diskTowers);} // recurse with next round
    } // end of private solveTowerOfHanoi method


    
    public static void displayTowerOfHanoi(int[] diskTowers)
    {
        // holds how many disk are at each tower
        int[] towerStacks = new int[3];
        for(int i = 0; i < towerStacks.length; i++)
            {towerStacks[i] = 0;} // assigns all to 0

        // holds visual 2d array of game
        char[][] towers = new char[19][diskTowers.length+3];
        // for first 5 rows
        for(int i = 0; i < towers[0].length-1; i++)
        {
            for(int j = 0; j < 19; j++)
            {
                switch(j)
                {
                    case 2:
                    case 9:
                    case 16:
                        towers[j][i] = '|';
                        break;
                    default:
                        towers[j][i] = ' ';
                        break;
                }
            }
        }
        for(int i = 0; i < 19; i++)
        {
            switch(i)
            {
                case 5:
                case 6:
                case 12:
                case 13:
                    towers[i][towers[0].length-1] = ' ';
                    break;
                default:
                    towers[i][towers[0].length-1] = '-';
                    break;
            }
        }
        
        // adds tower values
        for(int i = diskTowers.length; i > 0; i--)
        {
            switch(diskTowers[i-1])
            {
                case 0:
                    towers[2][4-towerStacks[0]] = (char) (i+'0');
                    towerStacks[0]++;
                    break;
                case 1:
                    towers[9][4-towerStacks[1]] = (char) (i+'0');
                    towerStacks[1]++;
                    break;
                case 2:
                    towers[16][4-towerStacks[2]] = (char) (i+'0');
                    towerStacks[2]++;
                    break;
            }
        }

        // prints display
        for(int i = 0; i < towers[0].length; i++)
        {
            for(int j = 0; j < 19; j++)
            {
                System.out.print(towers[j][i]);
            }
            System.out.println();
        }
    }
} // end of TowerOfHanoi class