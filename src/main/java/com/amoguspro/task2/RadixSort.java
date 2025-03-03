package com.amoguspro.task2;

import lombok.SneakyThrows;

public class RadixSort {

    private static final int RADIX = 10;

    @SneakyThrows
    public static int[] sort(int[] arr) {
        // Handle empty array
        if (arr.length == 0) {
            return arr;
        }

        // Count negatives and non-negatives
        int negativeCount = 0;
        int nonNegativeCount = 0;
        for (int num : arr) {
            if (num < 0) {
                negativeCount++;
            } else {
                nonNegativeCount++;
            }
        }

        // Separate the numbers into two arrays.
        int[] negatives = new int[negativeCount];
        int[] nonNegatives = new int[nonNegativeCount];
        int negIndex = 0;
        int nonNegIndex = 0;

        for (int num : arr) {
            if (num < 0) {
                // Multiply by -1 to convert to positive for sorting.
                negatives[negIndex++] = -num;
            } else {
                nonNegatives[nonNegIndex++] = num;
            }
        }

        // Sort both arrays (they contain only non-negative numbers now).
        negatives = radixSortNonNegative(negatives);
        nonNegatives = radixSortNonNegative(nonNegatives);

        // Reverse the negatives array and revert the sign back.
        for (int i = 0, j = negatives.length - 1; i < j; i++, j--) {
            int temp = negatives[i];
            negatives[i] = negatives[j];
            negatives[j] = temp;
        }
        for (int i = 0; i < negatives.length; i++) {
            negatives[i] = -negatives[i];
        }

        // Merge negatives and non-negatives into the result array.
        int[] result = new int[arr.length];
        System.arraycopy(negatives, 0, result, 0, negatives.length);
        System.arraycopy(nonNegatives, 0, result, negatives.length, nonNegatives.length);
        return result;
    }

    // Helper method: Radix sort for non-negative integers.
    private static int[] radixSortNonNegative(int[] arr) {
        if (arr.length == 0) return arr;
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= RADIX) {
            countingSort(arr, exp);
        }
        return arr;
    }

    // Utility method to get the maximum value in the array.
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    // Counting sort based on the digit represented by exp.
    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];      // Output array
        int[] count = new int[RADIX];     // Count array for digits 0 to RADIX-1

        // Count occurrences of digits.
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % RADIX;
            count[digit]++;
        }

        // Transform count so that count[i] contains the actual position.
        for (int i = 1; i < RADIX; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array (iterating backwards to ensure stability).
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % RADIX;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy the output array back to arr.
        System.arraycopy(output, 0, arr, 0, n);
    }
}
