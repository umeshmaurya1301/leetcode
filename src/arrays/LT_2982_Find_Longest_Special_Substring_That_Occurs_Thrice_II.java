// Find Longest Special Substring That Occurs Thrice II — https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/
package arrays;

import java.util.*;

class LT_2982_Find_Longest_Special_Substring_That_Occurs_Thrice_II {
    public int maximumLength(String s) {
        int n = s.length();
        int[][] lenArr = new int[26][n+1];

        for(int i=0; i<n;) {
            char ch = s.charAt(i);
            int j=i;
            while(j<n && ch== s.charAt(j)) {
                j++;
            }
            int len = j-i;
            for(int k=1; k<=len; k++) {
                lenArr[ch-'a'][k]++;
            }
            i=j;
        }

        for(int i=0; i<lenArr.length; i++) {
            int sum = 0;
            for (int j=n; j>=1; j--) {
                int val = lenArr[i][j];
                sum += val;
                lenArr[i][j] = sum;
            }
        }

        int max = -1;
        for(int i=0; i<lenArr.length; i++) {
            for(int j=n-1; j>=0; j--) {
                if(lenArr[i][j]>=3) {
                    max = Math.max(max, j);
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LT_2982_Find_Longest_Special_Substring_That_Occurs_Thrice_II sol = new LT_2982_Find_Longest_Special_Substring_That_Occurs_Thrice_II();
        System.out.println(sol.maximumLength("aaaa")); // expected: 2
        System.out.println(sol.maximumLength("abcdef")); // expected: -1
        System.out.println(sol.maximumLength("abcaba")); // expected: 1
    }
}
