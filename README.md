# leetcode-dsa

Java solutions extracted from my [Obsidian DSA notes](../../Notes/Obsidian-Notes/Tech/DSA/Questions) — mirrors the problems tracked in `Tech/DSA/Roadmap.md`.

Flat layout, no build tool: every `.java` file under `src/` is self-contained (its own `import java.util.*;` and a top-level class named after the file) so the whole folder compiles together with a single `javac`:

```
javac -d out src/*.java
```

Problems with more than one approach documented in the notes (e.g. brute force vs. optimal) get one file per approach, suffixed `_1_…`, `_2_…`, etc.

## Problems (44)

| # | Problem | Difficulty | Solution(s) |
|---|---|---|---|
| 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | [`LT_0003_Longest_Substring_Without_Repeating_Characters.java`](src/LT_0003_Longest_Substring_Without_Repeating_Characters.java) |
| 4 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | Hard | [`LT_0004_Median_of_Two_Sorted_Arrays.java`](src/LT_0004_Median_of_Two_Sorted_Arrays.java) |
| 5 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | Medium | [`LT_0005_Longest_Palindromic_Substring_1_TopDownMemoization.java`](src/LT_0005_Longest_Palindromic_Substring_1_TopDownMemoization.java)<br>[`LT_0005_Longest_Palindromic_Substring_2_BottomUpTabulation.java`](src/LT_0005_Longest_Palindromic_Substring_2_BottomUpTabulation.java)<br>[`LT_0005_Longest_Palindromic_Substring_3_ExpandAroundCenter.java`](src/LT_0005_Longest_Palindromic_Substring_3_ExpandAroundCenter.java) |
| 17 | [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) | Medium | [`LT_0017_Letter_Combinations_of_a_Phone_Number.java`](src/LT_0017_Letter_Combinations_of_a_Phone_Number.java) |
| 30 | [Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/) | Hard | [`LT_0030_Substring_With_Concatenation_Of_All_Words.java`](src/LT_0030_Substring_With_Concatenation_Of_All_Words.java) |
| 33 | [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) | Medium | [`LT_0033_Search_in_Rotated_Sorted_Array.java`](src/LT_0033_Search_in_Rotated_Sorted_Array.java) |
| 36 | [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | Medium | [`LT_0036_Valid_Sudoku.java`](src/LT_0036_Valid_Sudoku.java) |
| 37 | [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) | Hard | [`LT_0037_Sudoku_Solver.java`](src/LT_0037_Sudoku_Solver.java) |
| 41 | [First Missing Positive](https://leetcode.com/problems/first-missing-positive/description/) | Hard | [`LT_0041_First_Missing_Positive.java`](src/LT_0041_First_Missing_Positive.java) |
| 42 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/description/) | Hard | [`LT_0042_Trapping_Rain_Water.java`](src/LT_0042_Trapping_Rain_Water.java) |
| 43 | [Multiply Strings](https://leetcode.com/problems/multiply-strings/) | Medium | [`LT_0043_Multiply_Strings.java`](src/LT_0043_Multiply_Strings.java) |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/description/) | Medium | [`LT_0049_Group_Anagrams_1_SortingKey.java`](src/LT_0049_Group_Anagrams_1_SortingKey.java)<br>[`LT_0049_Group_Anagrams_2_FrequencyCountKey.java`](src/LT_0049_Group_Anagrams_2_FrequencyCountKey.java) |
| 51 | [N-Queens](https://leetcode.com/problems/n-queens/) | Hard | [`LT_0051_N_Queens.java`](src/LT_0051_N_Queens.java) |
| 76 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | Hard | [`LT_0076_Minimum_Window_Substring.java`](src/LT_0076_Minimum_Window_Substring.java) |
| 121 | [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/) | Easy | [`LT_0121_Best_Time_to_Buy_and_Sell_Stock.java`](src/LT_0121_Best_Time_to_Buy_and_Sell_Stock.java) |
| 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/description/) | Medium | [`LT_0128_Longest_Consecutive_Sequence.java`](src/LT_0128_Longest_Consecutive_Sequence.java) |
| 146 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | Medium | [`LT_0146_LRU_Cache.java`](src/LT_0146_LRU_Cache.java) |
| 152 | [Maximum Product SubArray](https://leetcode.com/problems/maximum-product-subarray/) | Medium | [`LT_152_Maximum_Product_SubArray.java`](src/LT_152_Maximum_Product_SubArray.java) |
| 162 | [Find Peak Element](https://leetcode.com/problems/find-peak-element/) | Medium | [`LT_0162_Find_Peak_Element.java`](src/LT_0162_Find_Peak_Element.java) |
| 220 | [Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/description/) | Hard | [`LT_0220_Contains_Duplicate_III.java`](src/LT_0220_Contains_Duplicate_III.java) |
| 300 | [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) | Medium | [`LT_0300_Longest_Increasing_Subsequence_1_Memoization.java`](src/LT_0300_Longest_Increasing_Subsequence_1_Memoization.java)<br>[`LT_0300_Longest_Increasing_Subsequence_2_Tabulation.java`](src/LT_0300_Longest_Increasing_Subsequence_2_Tabulation.java)<br>[`LT_0300_Longest_Increasing_Subsequence_3_BinarySearchPatience.java`](src/LT_0300_Longest_Increasing_Subsequence_3_BinarySearchPatience.java) |
| 322 | [Coin Change](https://leetcode.com/problems/coin-change/) | Medium | [`LT_0322_Coin_Change.java`](src/LT_0322_Coin_Change.java) |
| 354 | [Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/) | Hard | [`LT_0354_Russian_Doll_Envelopes.java`](src/LT_0354_Russian_Doll_Envelopes.java) |
| 377 | [Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/description/) | Medium | [`LT_0377_Combination_Sum_IV.java`](src/LT_0377_Combination_Sum_IV.java) |
| 435 | [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) | Medium | [`LT_0435_Non_Overlapping_Intervals.java`](src/LT_0435_Non_Overlapping_Intervals.java) |
| 454 | [4Sum II](https://leetcode.com/problems/4sum-ii/description/) | Medium | [`LT_0454_4Sum_II.java`](src/LT_0454_4Sum_II.java) |
| 474 | [Ones and Zeroes](https://leetcode.com/problems/ones-and-zeroes/) | Medium | [`LT_0474_Ones_and_Zeroes.java`](src/LT_0474_Ones_and_Zeroes.java) |
| 518 | [Coin Change II](https://leetcode.com/problems/coin-change-ii/description/) | Medium | [`LT_0518_Coin_Change_II.java`](src/LT_0518_Coin_Change_II.java) |
| 523 | [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) | Medium | [`LT_0523_Continuous_Subarry_Sum.java`](src/LT_0523_Continuous_Subarry_Sum.java) |
| 713 | [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) | Medium | [`LT_0713_Subarray_Product_Less_Than_K.java`](src/LT_0713_Subarray_Product_Less_Than_K.java) |
| 740 | [Delete And Earn](https://leetcode.com/problems/delete-and-earn/) | Medium | [`LT_0740_Delete_And_Earn.java`](src/LT_0740_Delete_And_Earn.java) |
| 904 | [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) | Medium | [`LT_0904_Fruit_Into_Baskets.java`](src/LT_0904_Fruit_Into_Baskets.java) |
| 974 | [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) | Medium | [`LT_0974_Subarray_Sums_Divisible_by_K_1_PrefixSumHashMap.java`](src/LT_0974_Subarray_Sums_Divisible_by_K_1_PrefixSumHashMap.java)<br>[`LT_0974_Subarray_Sums_Divisible_by_K_2_PrefixModArray.java`](src/LT_0974_Subarray_Sums_Divisible_by_K_2_PrefixModArray.java) |
| 992 | [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) | Hard | [`LT_992_Subaary_with_K_different_Integers.java`](src/LT_992_Subaary_with_K_different_Integers.java) |
| 1011 | [Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) | Medium | [`LT_1011_Capacity_To_Ship_Packages_Within_D_Days.java`](src/LT_1011_Capacity_To_Ship_Packages_Within_D_Days.java) |
| 1695 | [Maximum Erasure Value](https://leetcode.com/problems/maximum-erasure-value/) | Medium | [`LT_1695_Maximum_Erasure_Value_1_BruteForceWrong.java`](src/LT_1695_Maximum_Erasure_Value_1_BruteForceWrong.java)<br>[`LT_1695_Maximum_Erasure_Value_2_SlidingWindowSet.java`](src/LT_1695_Maximum_Erasure_Value_2_SlidingWindowSet.java)<br>[`LT_1695_Maximum_Erasure_Value_3_SlidingWindowMapOptimal.java`](src/LT_1695_Maximum_Erasure_Value_3_SlidingWindowMapOptimal.java) |
| 1711 | [Count Good Meals](https://leetcode.com/problems/count-good-meals/) | Medium | [`LT_1711_Count_Good_Meals.java`](src/LT_1711_Count_Good_Meals.java) |
| 2401 | [Longest Nice Subarray](https://leetcode.com/problems/longest-nice-subarray/) | Medium | [`LT_2401_Longest_Nice_SubArray.java`](src/LT_2401_Longest_Nice_SubArray.java) |
| 2981 | [Find Longest Special Substring That Occurs Thrice I](https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/) | Medium | [`LT_2981_Find_Longest_Special_Substring_That_Occurs_Thrice_I.java`](src/LT_2981_Find_Longest_Special_Substring_That_Occurs_Thrice_I.java) |
| 2982 | [Find Longest Special Substring That Occurs Thrice II](https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/) | Medium | [`LT_2982_Find_Longest_Special_Substring_That_Occurs_Thrice_II.java`](src/LT_2982_Find_Longest_Special_Substring_That_Occurs_Thrice_II.java) |
| 3932 | [Count K-th Roots in a Range](https://leetcode.com/problems/count-k-th-roots-in-a-range/) | Medium | [`LT_3932_Count_K_th_Roots_in_a_Range.java`](src/LT_3932_Count_K_th_Roots_in_a_Range.java) |
| 3964 | [Minimum Lights To Illuminate A Road](https://leetcode.com/problems/minimum-lights-to-illuminate-a-road/) | Medium | [`LT_3964_Minimum_Lights_To_Illuminate_A_Road.java`](src/LT_3964_Minimum_Lights_To_Illuminate_A_Road.java) |
| 3965 | [Finish Time Of Tasks I](https://leetcode.com/problems/finish-time-of-tasks-i/) | Medium | [`LT_3965_Finish_Time_Of_Tasks_I.java`](src/LT_3965_Finish_Time_Of_Tasks_I.java) |
| 3968 | [Maximum Manhattan Distance After All Moves](https://leetcode.com/problems/maximum-manhattan-distance-after-all-moves/) | Easy | [`LT_3968_Maximum_Manhattan_Distance_After_All_Moves.java`](src/LT_3968_Maximum_Manhattan_Distance_After_All_Moves.java) |

