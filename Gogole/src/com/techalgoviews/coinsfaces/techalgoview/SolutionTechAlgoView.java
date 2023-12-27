package com.techalgoviews.coinsfaces.techalgoview;

import org.junit.Test;

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
     * Initial analysis. It could be similar to a problem of finding the maximum number of sums that
     * is zero. Example below. We can substitute H by -1, and T by k
     * @param k
     * @param flips
     * @return
     */
    public int longestSubsequence(int k, char[] flips) {
        if (k == 0) {
            return 0;
        }

        if (flips.length == 0) {
            return 0;
        }

        int headCount = 0;
        int tailCount = 0;
        for (char c : flips) {
            if (c == 'H') {
                headCount++;
            } else {
                tailCount++;
            }
        }

        if (headCount == 0 || tailCount == 0) {
            return 0;
        }

        int[] subs = new int[flips.length];

        for (int i = 0; i < flips.length; i++) {
            if (flips[i] == 'H') {
                subs[i] = -1;
            } else {
                subs[i] = k;
            }
        }

        MaximumSubsequenceSumZero maximumSubsequenceSumZero = new MaximumSubsequenceSumZero();
        return maximumSubsequenceSumZero.findMaximumSubsequenceSumZero(subs);
    }

    @Test
    public void testExitConditionKZero() {
        // Given
        int k = 0;
        char[] flipResult = new char[]{'H', 'T', 'H', 'H', 'T', 'H', 'H', 'H', 'H', 'H', 'T', 'H'};

        // When
        int result = longestSubsequence(k, flipResult);

        // Then
        assertEquals(0, result);
    }

    @Test
    public void testExitConditionEmptyList() {
        // Given
        int k = 4;
        char[] flipResult = new char[]{};

        // When
        int result = longestSubsequence(k, flipResult);

        // Then
        assertEquals(0, result);
    }

    @Test
    public void testExitConditionAllHead() {
        // Given
        int k = 2;
        char[] flipResult = new char[]{'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H', 'H'};

        // When
        int result = longestSubsequence(k, flipResult);

        // Then
        assertEquals(0, result);
    }

    @Test
    public void testExitConditionAllTail() {
        // Given
        int k = 2;
        char[] flipResult = new char[]{'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T'};

        // When
        int result = longestSubsequence(k, flipResult);

        // Then
        assertEquals(0, result);
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