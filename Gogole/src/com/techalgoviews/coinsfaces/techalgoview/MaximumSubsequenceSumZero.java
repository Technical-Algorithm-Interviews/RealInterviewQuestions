package com.techalgoviews.coinsfaces.techalgoview;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MaximumSubsequenceSumZero {
    public int findMaximumSubsequenceSumZero(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int[] prefixSum = new int[array.length];
        prefixSum[0] = array[0];

        for (int i = 1; i < array.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + array[i];
        }

        // Store the possible prefix sum values and its first position
        Map<Integer, Integer> prefixSumsFirstPosition = new HashMap<>();

        int longestSubsequence = 0;
        for (int i = 0; i < prefixSum.length; i++) {
            int prefixSumItem = prefixSum[i];
            if (prefixSumsFirstPosition.containsKey(prefixSumItem)) {
                longestSubsequence = Math.max(longestSubsequence, i - prefixSumsFirstPosition.get(prefixSumItem));
            } else {
                prefixSumsFirstPosition.put(prefixSumItem, i);
            }
        }

        return longestSubsequence;
    }

    @Test
    public void testFindMaximumSubsequenceSumZero() {
        // Given
        int[] input = new int[]{5, 1, 0, 2, -3, 4, 2};

        // When
        int result = findMaximumSubsequenceSumZero(input);

        // Then
        assertEquals(4, result);
    }

    @Test
    public void testExitConditionEmptyArray() {
        // Given
        int[] input = new int[]{};

        // When
        int result = findMaximumSubsequenceSumZero(input);

        // Then
        assertEquals(0, result);
    }
}