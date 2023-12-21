package com.techalgoviews.coinsfaces;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * We flip a coin several times. Each time we annotate the result. If it is Head we write "H", if it is
 * not head we write "T". By given the list of results and a constant factor K, find the longest consecutive
 * subsequence of them which the number of H is k times the number of T
 *
 * Example: K = 2, flips = "HTHHTHHHHHTH"
 */
public class SolutionTechAlgoView {
    /**
     * Initial analysis. It could be similar to a problem of finding the maxium number of sums that
     * is zero. Example below. We can substitute H by -1, and T by k
     * @param k
     * @param flips
     * @return
     */
    public int longestSubsequence(int k, char[] flips) {
        int[] subs = new int[flips.length];

        for (int i = 0; i < flips.length; i++) {
            if (flips[i] == 'H') {
                subs[i] = -1;
            } else {
                subs[i] = k;
            }
        }

        return findMaximumSubsequenceSumZero(subs);
    }

    public int findMaximumSubsequenceSumZero(int[] array) {
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
    public void testLongestSubsequence() {
        // Given
        int k = 2;
        char[] flipResult = new char[]{'H', 'T', 'H', 'H', 'T', 'H', 'H', 'H', 'H', 'H', 'T', 'H'};

        // When
        int result = longestSubsequence(k, flipResult);

        // Then
        assertEquals(6, result);
    }
}