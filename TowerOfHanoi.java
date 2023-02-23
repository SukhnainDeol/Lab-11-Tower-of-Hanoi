// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/13/2023
// Assignmnet: Lab 5: Tower Of Hanoi

// Purpose: This program will display the optimal move for solving a 
// tower of hanoi puzzle with 3 towers and 4 disks using recursion. 
// It will use both words to describe the movements of each disk and 
// create an ascii text image displaying the movement. 



// To DO:
    // Revise pre/post conditions 


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
        in.close(); // close scanner
    } // end of main method



    // keeps track of recursions(rounds in a game)
    public static int round; 



    // parameter : disk - number of disks on tower of hanoi

    // pre : disks > 0 
    // post : return print statements of optimal moves to solve tower of
           // hanoi with 3 towers for given number of disks starting from
           // the given round
    public static void solveTowerOfHanoi(int disks)
    {
        round = 0;
        int[] diskArray = new int[disks];
        // places all disks at tower 0 (tower A)
        for(int i = 0; i < disks; i++)
            {diskArray[i] = 0;}

        // prints starting positions
        System.out.println("STEP 0:");
        displayTowerOfHanoi(diskArray);
        round++;

        // begins solving
        solveTowerOfHanoi(disks, 1, 2, 3, diskArray);
    } // end of solveTowerOfHanoi method



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
    private static void solveTowerOfHanoi(int disks, int start, int extra, int end, int[] diskArray)
    {
        if (disks > 0) // ends recursion once disks = 0
        {
            // prints first half of the cycle
            solveTowerOfHanoi(disks-1, start, end, extra, diskArray);

            diskArray[disks-1] = end-1; // keeps track of where the disk is
            
            // converts tower numbers to letters
            char startTower = (char)('A'+(start-1));
            char endTower = (char)('A'+(end-1));

            // the largest number disk's movement is always in the center of the pattern
            System.out.println("STEP "+round+": Move Disk " + disks + " From Tower "+startTower+" to Tower " + endTower + ".");
            round++;
            displayTowerOfHanoi(diskArray); // printed visual representation of towers
            // prints 2nd half of the cycle
            solveTowerOfHanoi(disks-1, extra, start, end, diskArray);
        }
    } // end of solveTowerOfHanoi method


    
    public static void displayTowerOfHanoi(int[] diskArray)
    {
        // holds how many disk are at each tower
        int[] towerStacks = new int[3];
        // assigns all to start at 0
        for(int i = 0; i < towerStacks.length; i++)
            {towerStacks[i] = 0;} 

        // diskArray length +3 is to know 
        // how tall to make the display
        int towerHeight = diskArray.length +3;

        // holds visual 2d array of game
        // 19 is the length of the towers displayed
        char[][] towers = new char[19][towerHeight];

        // prints height of tower
        for(int i = 0; i < towers[0].length-1; i++)
        {
            // prints length of tower
            for(int j = 0; j < 19; j++)
            {
                // prints tower rod at appropiate space
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
        // prints base of tower
        for(int i = 0; i < 19; i++)
        {
            // spacing between the towers at appropiate locations
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
        
        // for each of the disks in the towers 
        for(int i = diskArray.length; i > 0; i--)
        {
            // evaluates tower value of disk
            // and adds to appropiate space in 2d array
            switch(diskArray[i-1])
            {
                // towerHeight-2-towerStack[index] = lo
                case 0: // disks in tower A
                    towers[2][towerHeight-2-towerStacks[0]] = (char) (i+'0');
                    towerStacks[0]++; // tracks how many disks in each tower
                    break;
                case 1: // disks in tower B
                    towers[9][towerHeight-2-towerStacks[1]] = (char) (i+'0');
                    towerStacks[1]++; // tracks how many disks in each tower
                    break;
                case 2: // disks in tower C
                    towers[16][towerHeight-2-towerStacks[2]] = (char) (i+'0');
                    towerStacks[2]++; // tracks how many disks in each tower
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
    } // end of displayTowerOfHanoi method
} // end of TowerOfHanoi class