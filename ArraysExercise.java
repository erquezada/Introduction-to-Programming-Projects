public class ArraysExercise {
    public static void main(String[] args) {
        int[] myArray = {1,2,-3,4,5,6,7,8,9,10};
        System.out.println("Printing the values of the array: ");
        for(int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i]+ ", ");
        }
        System.out.println();
        System.out.println("The minimun value within the array is: " + findMin(myArray));
        System.out.println("The maximun value within the array is: " + findMax(myArray));  
        System.out.println("The median value within the array is: " + findMedian(myArray));
    }
    public static int findMin(int[] myArray) {
        int min = 0;
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i] < min) {
                min = myArray[i];
            }
        }
        return min;
    } 
    public static int findMax(int[] myArray) {
        int max = 0;
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i] > max) {
                max = myArray[i];
            }
        }
        return max;
    }
    public static double findMedian(int[] myArray) {
        if (myArray.length % 2 == 0) {
            return (double) (myArray[myArray.length/2] + myArray[myArray.length/2-1])/2.0;
        } else {                
            return myArray.length/2;
        }
    }
} 