import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ArraysExercise {

    // Method to find the second largest element in an array
    public static int findSecondLargest(int[] arr) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num < first) {
                second = num;
            }
        }
        return second;
    }

    // Method to check if the array is sorted
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    // Method to find the mode of an array
    public static int findMode(int[] arr) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int mode = arr[0];
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mode = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mode;
    }

    // Method to find the range of the array (max - min)
    public static int findRange(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
        int min = Arrays.stream(arr).min().orElse(Integer.MAX_VALUE);
        return max - min;
    }

    // Method to reverse the array
    public static void reverseArray(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // Method to calculate the sum of elements
    public static int calculateSum(int[] arr) {
        return Arrays.stream(arr).sum();
    }

    // Method to calculate the average
    public static double calculateAverage(int[] arr) {
        return Arrays.stream(arr).average().orElse(0);
    }

    // New Functions

    // 1. Find the median of the array
    public static double findMedian(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        if (n % 2 == 0) {
            return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;
        } else {
            return arr[n / 2];
        }
    }

    // 2. Find the number of even elements
    public static long countEvens(int[] arr) {
        return Arrays.stream(arr).filter(x -> x % 2 == 0).count();
    }

    // 3. Find the number of odd elements
    public static long countOdds(int[] arr) {
        return Arrays.stream(arr).filter(x -> x % 2 != 0).count();
    }

    // 4. Find the standard deviation
    public static double calculateStandardDeviation(int[] arr) {
        double mean = calculateAverage(arr);
        double variance = Arrays.stream(arr).mapToDouble(x -> Math.pow(x - mean, 2)).sum() / arr.length;
        return Math.sqrt(variance);
    }

    // 5. Find the frequency of each element
    public static Map<Integer, Integer> findFrequency(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        return freqMap;
    }

    // 6. Count unique elements
    public static long countUnique(int[] arr) {
        return Arrays.stream(arr).distinct().count();
    }

    // 7. Remove duplicates
    public static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    // 8. Find the smallest missing positive integer
    public static int findSmallestMissingPositive(int[] arr) {
        int n = arr.length;
        boolean[] present = new boolean[n + 1];
        for (int num : arr) {
            if (num > 0 && num <= n) {
                present[num] = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!present[i]) {
                return i;
            }
        }
        return n + 1;
    }

    // 9. Rotate array to the right
    public static void rotateRight(int[] arr, int k) {
        int n = arr.length;
        k %= n;
        reverseArray(arr, 0, n - 1);
        reverseArray(arr, 0, k - 1);
        reverseArray(arr, k, n - 1);
    }

    private static void reverseArray(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // 10. Find the kth largest element
    public static int findKthLargest(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[arr.length - k];
    }

    // 11. Find the kth smallest element
    public static int findKthSmallest(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k - 1];
    }

    // 12. Check if the array is a palindrome
    public static boolean isPalindrome(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            if (arr[i] != arr[j]) {
                return false;
            }
        }
        return true;
    }

    // 13. Find the product of all elements
    public static long calculateProduct(int[] arr) {
        return Arrays.stream(arr).asLongStream().reduce(1, (x, y) -> x * y);
    }

    // 14. Find the cumulative sum array
    public static int[] findCumulativeSum(int[] arr) {
        int[] cumulative = new int[arr.length];
        cumulative[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            cumulative[i] = cumulative[i - 1] + arr[i];
        }
        return cumulative;
    }

    // 15. Count elements greater than a value
    public static long countGreaterThan(int[] arr, int value) {
        return Arrays.stream(arr).filter(x -> x > value).count();
    }

    // 16. Count elements less than a value
    public static long countLessThan(int[] arr, int value) {
        return Arrays.stream(arr).filter(x -> x < value).count();
    }

    // 17. Find all prime numbers in the array
    public static int[] findPrimes(int[] arr) {
        return Arrays.stream(arr).filter(ArraysExercise::isPrime).toArray();
    }

    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // 18. Find the longest increasing subsequence
    public static int findLongestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().orElse(1);
    }

    // 19. Find the maximum subarray sum (Kadane's Algorithm)
    public static int findMaxSubarraySum(int[] arr) {
        int maxSoFar = arr[0];
        int currentMax = arr[0];
        for (int i = 1; i < arr.length; i++) {
            currentMax = Math.max(arr[i], currentMax + arr[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        return maxSoFar;
    }

    // 20. Check if two arrays are equal
    public static boolean areArraysEqual(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    // Main method to create the GUI
    public static void main(String[] args) {
        JFrame frame = new JFrame("Array Operations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel instructions = new JLabel("Enter your array (comma-separated):");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(instructions);

        JTextField inputField = new JTextField();
        inputField.setMaximumSize(new Dimension(400, 30));
        panel.add(inputField);

        JButton processButton = new JButton("Process Array");
        processButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(processButton);

        JTextArea resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);

        processButton.addActionListener(e -> {
            try {
                String[] input = inputField.getText().split(",");
                int[] array = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
                StringBuilder results = new StringBuilder();
        
                results.append("Second Largest: ").append(findSecondLargest(array)).append("\n");
                results.append("Is Sorted: ").append(isSorted(array)).append("\n");
                results.append("Mode: ").append(findMode(array)).append("\n");
                results.append("Range: ").append(findRange(array)).append("\n");
                results.append("Sum: ").append(calculateSum(array)).append("\n");
                results.append("Average: ").append(String.format("%.2f", calculateAverage(array))).append("\n");
                results.append("Median: ").append(findMedian(array)).append("\n");
                results.append("Count Evens: ").append(countEvens(array)).append("\n");
                results.append("Count Odds: ").append(countOdds(array)).append("\n");
                results.append("Standard Deviation: ").append(String.format("%.2f", calculateStandardDeviation(array))).append("\n");
                results.append("Frequency Map: ").append(findFrequency(array)).append("\n");
                results.append("Unique Count: ").append(countUnique(array)).append("\n");
                results.append("Array Without Duplicates: ").append(Arrays.toString(removeDuplicates(array))).append("\n");
                results.append("Smallest Missing Positive: ").append(findSmallestMissingPositive(array)).append("\n");
                results.append("Kth Largest (k=2): ").append(findKthLargest(array, 2)).append("\n");
                results.append("Kth Smallest (k=2): ").append(findKthSmallest(array, 2)).append("\n");
                results.append("Is Palindrome: ").append(isPalindrome(array)).append("\n");
                results.append("Product of Elements: ").append(calculateProduct(array)).append("\n");
                results.append("Cumulative Sum: ").append(Arrays.toString(findCumulativeSum(array))).append("\n");
                results.append("Count Greater Than 5: ").append(countGreaterThan(array, 5)).append("\n");
                results.append("Count Less Than 5: ").append(countLessThan(array, 5)).append("\n");
                results.append("Prime Numbers: ").append(Arrays.toString(findPrimes(array))).append("\n");
                results.append("Longest Increasing Subsequence: ").append(findLongestIncreasingSubsequence(array)).append("\n");
                results.append("Maximum Subarray Sum: ").append(findMaxSubarraySum(array)).append("\n");
        
                resultArea.setText(results.toString());
            } catch (Exception ex) {
                resultArea.setText("Error processing array. Please enter valid integers separated by commas.");
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }
}