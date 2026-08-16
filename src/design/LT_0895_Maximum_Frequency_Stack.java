/*
 * 895. Maximum Frequency Stack — Hard
 * https://leetcode.com/problems/maximum-frequency-stack/
 *
 * Design a stack-like data structure to push elements to the stack and pop the most frequent
 * element from the stack.
 *
 * Implement the FreqStack class:
 * - FreqStack() constructs an empty frequency stack.
 * - void push(int val) pushes an integer val onto the top of the stack.
 * - int pop() removes and returns the most frequent element in the stack.
 *
 * If there is a tie for the most frequent element, the element closest to the stack's top is
 * removed and returned.
 *
 * Example:
 *   Input:
 *   ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"]
 *   [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 *   Output: [null,null,null,null,null,null,null,5,7,5,4]
 *
 *   Explanation: After six push operations, the stack contains [5,7,5,7,4,5] from bottom to top.
 *   pop() -> 5 (most frequent, count 3)
 *   pop() -> 7 (tied with 5 at count 2, but 7 is nearest the top)
 *   pop() -> 5
 *   pop() -> 4
 *
 * Constraints:
 *   0 <= val <= 10^9
 *   The total number of push calls will not exceed 10^4 in a single test case; the total number
 *   of pop calls will not exceed 10^4 in a single test case; the total number of push and pop
 *   calls will not exceed 1.5 * 10^5 across all test cases.
 *   It is guaranteed that pop() won't be called if the stack has zero elements.
 *
 * Approach: HashMap<val, freq> for O(1) frequency lookup + HashMap<freq, Stack<val>> bucketing
 * values by count (each bucket naturally ordered by recency of reaching that count) + a maxFreq
 * pointer maintained incrementally — O(1) amortized per push/pop, O(n) space.
 */
package design;

import java.util.*;

class LT_0895_Maximum_Frequency_Stack {

    int maxFreq;
    Map<Integer, Integer> freqMap = new HashMap<>();
    Map<Integer, Stack<Integer>> freqStack = new HashMap<>();

    public LT_0895_Maximum_Frequency_Stack() {
        freqMap = new HashMap<>();
        freqStack = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int f = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, f);
        maxFreq = Math.max(maxFreq, f);
        freqStack.computeIfAbsent(f, k -> new Stack()).push(val);
    }

    public int pop() {
        int val = freqStack.get(maxFreq).pop();
        freqMap.put(val, freqMap.get(val)-1);
        if(freqStack.get(maxFreq).isEmpty()) maxFreq--;
        return val;
    }

    public static void main(String[] args) {
        // ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"]
        // [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
        // expected: [null,null,null,null,null,null,null,5,7,5,4]
        LT_0895_Maximum_Frequency_Stack fs = new LT_0895_Maximum_Frequency_Stack();
        fs.push(5);
        System.out.println("null");              // push(5)
        fs.push(7);
        System.out.println("null");              // push(7)
        fs.push(5);
        System.out.println("null");              // push(5)
        fs.push(7);
        System.out.println("null");              // push(7)
        fs.push(4);
        System.out.println("null");              // push(4)
        fs.push(5);
        System.out.println("null");              // push(5)
        System.out.println(fs.pop());             // expected: 5
        System.out.println(fs.pop());             // expected: 7
        System.out.println(fs.pop());             // expected: 5
        System.out.println(fs.pop());             // expected: 4
    }
}
