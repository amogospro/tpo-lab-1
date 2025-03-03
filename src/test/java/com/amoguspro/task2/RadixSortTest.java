package com.amoguspro.task2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RadixSortTest {

    @ParameterizedTest(name = "Sort test: input={0} expected={1}")
    @DisplayName("Check positive values from CSV")
    @CsvFileSource(resources = "/radix_positive_values.csv", numLinesToSkip = 1, delimiter = ';')
    void checkPositiveValuesFromCSV(String unsortedStr, String expectedStr) {
        int[] unsorted = parseArray(unsortedStr);
        int[] expected = parseArray(expectedStr);
        assertArrayEquals(expected, RadixSort.sort(unsorted));
    }

    @Test
    @DisplayName("Check empty")
    void checkEmpty() {
        assertArrayEquals(new int[]{}, RadixSort.sort(new int[]{}));
    }

    @Test
    @DisplayName("Check negative values")
    void checkNegativeValues() {
        assertArrayEquals(new int[]{-5, -4, -3, -2, -1}, RadixSort.sort(new int[]{-5, -1, -4, -3, -2}));
    }

    @Test
    @DisplayName("Check zeros")
    void checkZeros() {
        assertArrayEquals(new int[]{0, 0, 0, 0, 0}, RadixSort.sort(new int[]{0, 0, 0, 0, 0}));
    }

    @Test
    @DisplayName("Check null")
    void checkNull() {
        assertThrows(NullPointerException.class, () -> RadixSort.sort(null));
    }

    // Helper method to parse a comma-separated list of integers from a string.
    private int[] parseArray(String str) {
        if (str == null || str.trim().isEmpty()) {
            return new int[0];
        }
        String[] tokens = str.split(",");
        int[] result = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            result[i] = Integer.parseInt(tokens[i].trim());
        }
        return result;
    }
}
