// Russian Doll Envelopes — https://leetcode.com/problems/russian-doll-envelopes/
package binarysearch;

import java.util.*;

class LT_0354_Russian_Doll_Envelopes {
    public int maxEnvelopes(int[][] envelopes) {
        /*
        1  4
        2  2
        3  3
        4  4
        5  7
        5  6
        5  5
        6  9
        */
        Arrays.sort(envelopes, (a,b) -> {
            if (a[0]==b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        return lis(envelopes);

    }

    private int lis (int[][] nums) {
        int row = nums.length;
        int col = nums[0].length;
        List<Integer> tail = new ArrayList<>();

        for (int i=0; i<row; i++) {
            int num = nums[i][col-1];
            int left = 0;
            int right = tail.size();

            while (left < right) {
                int mid = left + (right - left)/2;
                if (tail.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (left==tail.size()) {
                tail.add(num);
            } else {
                tail.set(left, num);
            }
        }

        return tail.size();
    }

    public static void main(String[] args) {
        LT_0354_Russian_Doll_Envelopes sol = new LT_0354_Russian_Doll_Envelopes();
        System.out.println(sol.maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}})); // expected: 3
        System.out.println(sol.maxEnvelopes(new int[][]{{1, 1}, {1, 1}, {1, 1}})); // expected: 1
    }
}
