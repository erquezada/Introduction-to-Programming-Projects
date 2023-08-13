import java.util.Arrays;
public class ArraysExercise {
    public static void main(String[] args) {
        int[] myArray = {1, 2, -3, 4, 5, 6, 7, 8, 9, 10};
        
        System.out.println("Printing the values of the array: ");
        printArray(myArray);
        
        System.out.println("The minimum value within the array is: " + findMin(myArray));
        System.out.println("The maximum value within the array is: " + findMax(myArray));
        System.out.println("The median value within the array is: " + findMedian(myArray));
        
        int[] reversedArray = reverseArray(myArray);
        System.out.println("Reversed array: ");
        printArray(reversedArray);
        
        int[] sortedArray = sortArray(myArray);
        System.out.println("Sorted array: ");
        printArray(sortedArray);
        
        int sum = calculateSum(myArray);
        System.out.println("Sum of array elements: " + sum);
        
        double average = calculateAverage(myArray);
        System.out.println("Average of array elements: " + average);
    }
    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }
    
    public static int findMin(int[] myArray) {
        int min = Integer.MAX_VALUE;
        for (int num : myArray) {
            min = Math.min(min, num);
        }
        return min;
    }
    
    public static int findMax(int[] myArray) {
        int max = Integer.MIN_VALUE;
        for (int num : myArray) {
            max = Math.max(max, num);
        }
        return max;
    }
    
    public static double findMedian(int[] myArray) {
        int n = myArray.length;
        Arrays.sort(myArray);
        if (n % 2 == 0) {
            return (double) (myArray[n / 2] + myArray[n / 2 - 1]) / 2.0;
        } else {
            return myArray[n / 2];
        }
    }
    public static int[] reverseArray(int[] arr) {
        int[] reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[arr.length - 1 - i] = arr[i];
        }
        return reversed;
    }
    
    public static int[] sortArray(int[] arr) {
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        return sorted;
    }
    
    public static int calculateSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }
    
    public static double calculateAverage(int[] arr) {
        if (arr.length == 0) {
            return 0.0;
        }
        int sum = calculateSum(arr);
        return (double) sum / arr.length;
    }
}
