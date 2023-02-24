// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/13/2023
// Assignment: Lab 5: Tower Of Hanoi

// Purpose: This program will display the optimal move for solving a 
// tower of hanoi puzzle with 3 towers and x disks using recursion.
// It will use both words to describe the movements of each disk and 
// create an ascii text image displaying the movement. 


import java.util.*;

class TowerOfHanoi
{
    public static void main(String[] args)
    {
        // checks if positive integer entered by user
        boolean isPositiveInt = false;
        // user input scanner
        Scanner in = new Scanner(System.in);
        // placeholder value for # of disks
        int disks = 1; 

        // loop asks for # of disks to solve for
        while(!isPositiveInt)
        {
            System.out.print("Number of Disks to Solve for: ");

            if(in.hasNextInt()) // if int
            {
                disks = in.nextInt(); // take int

                if(disks > 0) // if above 0
                    {isPositiveInt = true;} // end loop
                else // else error
                {
                    System.out.print("ERROR: Please Input a ");
                    System.out.println("Positive Whole Number\n");

                    in.nextLine(); // clears input for next loop
                }
            }
            else // else error
            {
                System.out.print("ERROR: Please Input a ");
                    System.out.println("Positive Whole Number\n");
                in.nextLine(); // clears input for next loop
            }
        } // end of input loop

        // solve Tower of Hanoi for given number of disks
        solveTowerOfHanoi(disks); 
        in.close(); // close scanner
    } // end of main method



    // keeps track of recursions(rounds in a game)
    public static int round; 


    /* 
    param: 
        disks - number of disks in game
    pre  : disks > 0 
    post : print optimal moves for tower of hanoi
           game with given disks, both in text and
           an ascii representation of the towers
    public version of method for user simplification
    */
    public static void solveTowerOfHanoi(int disks)
    {
        round = 0; // starting round
        int[] diskArray = new int[disks];

        // places all disks at tower 0 (tower A)
        for(int i = 0; i < disks; i++)
            {diskArray[i] = 0;}

        // prints starting position 
        System.out.println("STEP 0:");
        displayTowerOfHanoi(diskArray);
        round++;

        // begins solving
        solveTowerOfHanoi(disks, 1, 2, 3, diskArray);
    } // end of solveTowerOfHanoi method



    /* 
    param: 
        disk - disk to move
        start - current tower of disks
        end  - tower the disks end up in
        extra - extra tower used to move between the towers
        diskArray - array representation of disk positions
                    each index is the disk and value is the
                    tower it is at
    pre  : 
        disks > 0 
        diskArray.length == disks
    post : print optimal moves for tower of hanoi
           game with given disks, both in text and
           an ascii representation of the towers
    */
    private static void solveTowerOfHanoi(int disks,
     int start, int extra, int end, int[] diskArray)
    {
        if (disks > 0) // ends recursion once disks == 0
        {
            // prints first half of the moves
            solveTowerOfHanoi(disks-1, start, end, extra, diskArray);

            // represents disk movement in array,
            // disks-1 represents what disk is being moved
            // end represents where it is being moved
            diskArray[disks-1] = end-1; 
            
            // converts tower numbers to letters
            char startTower = (char)('A'+(start-1));
            char endTower = (char)('A'+(end-1));

            // prints the center of the pattern
            System.out.print("STEP "+round+": Move Disk ");
            System.out.print(disks+" From Tower "+startTower);
            System.out.println(" to Tower " + endTower + ".");
            round++; // increments rounds after each print

            // printed visual representation of towers after move
            displayTowerOfHanoi(diskArray); 

            // prints 2nd half of the moves
            solveTowerOfHanoi(disks-1, extra, start, end, diskArray);
        }
    } // end of solveTowerOfHanoi method



    /*
    param:
        diskArray - array with each index representing
        a disk and value representing the tower its on
    pre  : 
        diskArray.length > 0, 
        diskArray values either 0, 1, or 2
    post :
        print ascii text of tower of hanoi with disks at
         position according to diskArray
    */
    private static void displayTowerOfHanoi(int[] diskArray)
    {
        // holds how many disk are at each tower
        int[] towerStacks = new int[3];

        // assigns all towers to start w/ 0 disks
        for(int i = 0; i < towerStacks.length; i++)
            {towerStacks[i] = 0;} 

        // height of the rods printed
        int towerHeight = diskArray.length +3;

        // holds visual 2d array of game
        // 19 is the length of the text display for 3 towers
        char[][] towers = new char[19][towerHeight];


        // adds height of tower rods
        for(int i = 0; i < towers[0].length-1; i++)
        {
            // adds length of tower
            for(int j = 0; j < towers.length; j++)
            {
                // prints tower rod at appropiate space
                switch(j)
                {
                    // rods at 2,9,6
                    case 2:
                    case 9:
                    case 16:
                        towers[j][i] = '|';
                        break;
                    // whitespace everywhere else
                    default:
                        towers[j][i] = ' ';
                        break;
                }
            }
        }


        // adds base of tower
        for(int i = 0; i < 19; i++)
        {
            // spacing between the towers at appropiate locations
            switch(i)
            {
                // whitespace between the towers
                case 5:
                case 6:
                case 12:
                case 13:
                    towers[i][towers[0].length-1] = ' ';
                    break;
                // base of the tower
                default:
                    towers[i][towers[0].length-1] = '-';
                    break;
            }
        } 
        

        // replaces parts of the rods with disk values,
        // for each disk in diskarray 
        for(int i = diskArray.length; i > 0; i--)
        {
            // evaluates which tower the disk is at
            // and adds to the appropiate space in 2d array
            switch(diskArray[i-1])
            {
                // tower[] 2,9,16 are towers A,B,C respectively
                // towerHeight-2-towerStacks[]
                    // towerHeight-2 place at bottom rod of tower
                    // -towerStacks[] moves up if there is a disk there
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

        
        // prints tower
        // for height of tower
        for(int i = 0; i < towers[0].length; i++)
        {
            // for length of tower
            for(int j = 0; j < towers.length; j++)
            {
                // print element 
                System.out.print(towers[j][i]);
            }
            // spacing after rows
            System.out.println();
        }
    } // end of displayTowerOfHanoi method
} // end of TowerOfHanoi class