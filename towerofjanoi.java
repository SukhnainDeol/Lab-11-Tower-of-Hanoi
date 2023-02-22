public class towerofjanoi 
{
    public static void main(String[] args)
    {
        solveTowerOfHanoi(4, 1, 2, 3, 0);
    }

    public static void solveTowerOfHanoi(int disks, int start, int extra, int end, int diskArray)
    {
        if (disks > 0)
        {
            solveTowerOfHanoi(disks-1, start, end, extra, diskArray);
            System.out.println("Move Disk " + disks + " From Tower "+start+" to Tower " + end + ".");
            solveTowerOfHanoi(disks-1, extra, start, end, diskArray);
        }
        return;
    }

    public static void displayTowerOfHanoi(int disks, int towerA, int towerB, int towerC)
    {

    }
}
