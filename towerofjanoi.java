public class towerofjanoi 
{
    public static void main(String[] args)
    {
        solveTowerOfHanoi(4, 1, 2, 3, 0);
    }


    public static void solveTowerOfHanoi(int disks)
    {
        int[] diskArray = new int[disks];
        // places all disks at tower 0 (tower A)
        for(int i = 0; i < disks; i++)
            {diskArray[i] = 0;}
        solveTowerOfHanoi(disks, 1, 2, 3, diskArray);
    }

    private static void solveTowerOfHanoi(int disks, int start, int extra, int end, int[] diskArray)
    {
        if (disks > 0) // ends recursion once disks = 0
        {
            // prints first half of the cycle
            solveTowerOfHanoi(disks-1, start, end, extra, diskArray);

            diskArray[disk-1] = end-1; // keeps track of where the disk is
            
            // converts tower numbers to letters
            char startTower = (char)(A+(start-1));
            char endTower = (char)(A+(end-1));

            // the largest number disk's movement is always in the center of the pattern
            System.out.println("Move Disk " + disks + " From Tower "+startTower+" to Tower " + endTower + ".");
            displayTowerOfHanoi(diskArray); // printed visual representation of towers
            // prints 2nd half of the cycle
            solveTowerOfHanoi(disks-1, extra, start, end, diskArray);
        }
    }

    public static void displayTowerOfHanoi(int[] diskArray)
    {
        // holds how many disk are at each tower
        int[] towerStacks = new int[3];
        // assigns all to start at 0
        for(int i = 0; i < towerStacks.length; i++)
            {towerStacks[i] = 0;} 

        // diskArray length +3 is to know 
        // how tall to make the display
        int towerHeight = diskTowers +3;

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
            switch(diskTowers[i-1])
            {
                // 4-tower stacks
                case 0: // disks in tower A
                    towers[2][towerHeight-1-towerStacks[0]] = (char) (i+'0');
                    towerStacks[0]++; // tracks how many disks in each tower
                    break;
                case 1: // disks in tower B
                    towers[9][towerHeight-1-towerStacks[1]] = (char) (i+'0');
                    towerStacks[1]++; // tracks how many disks in each tower
                    break;
                case 2: // disks in tower C
                    towers[16][towerHeight-1-towerStacks[2]] = (char) (i+'0');
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
    }
}
