/*
 * 460. LFU Cache — Hard
 * https://leetcode.com/problems/lfu-cache/
 *
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 * - LFUCache(int capacity) — initializes the object with the capacity of the data structure.
 * - int get(int key) — gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * - void put(int key, int value) — updates the value of the key if present, or inserts the key if not already
 *   present. When the cache reaches its capacity, it should invalidate and remove the least frequently used
 *   key before inserting a new item. When there is a tie (two or more keys with the same use count), the
 *   least recently used key is invalidated.
 *
 * A use counter is maintained for each key. When a key is first inserted its counter is set to 1, and it is
 * incremented every time get or put is called on it. Both get and put must run in O(1) average time.
 *
 * Example:
 *   Input:
 *   ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 *   [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 *   Output: [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 *   Explanation:
 *   LFUCache(2)
 *   put(1, 1)   → cnt(1) = 1
 *   put(2, 2)   → cnt(2) = 1, cnt(1) = 1
 *   get(1)      → 1        (cnt(1) = 2)
 *   put(3, 3)   → 2 is LFU (cnt(2) = 1 is smallest), evict it
 *   get(2)      → -1       (evicted)
 *   get(3)      → 3        (cnt(3) = 2)
 *   put(4, 4)   → cnt(1) = cnt(3) = 2, tie broken by recency: 1 is LRU, evict it
 *   get(1)      → -1       (evicted)
 *   get(3)      → 3
 *   get(4)      → 4
 *
 * Constraints:
 *   1 <= capacity <= 10^4
 *   0 <= key <= 10^5
 *   0 <= value <= 10^9
 *   At most 2 * 10^5 calls will be made to get and put.
 *
 * Approach: HashMap<key, Node> for O(1) lookup + HashMap<freq, DoublyLinkedList> bucketing nodes by use
 * count (each bucket MRU-first) + a minFreq pointer maintained incrementally — O(1) amortized per
 * get/put, O(capacity) space.
 */
package design;

import java.util.*;

class LT_0460_LFU_Cache {

    private final int capacity;;
    private int size;
    private int minFreq;

    private final Map<Integer, Node> cache;
    private final Map<Integer, DoublyLinkedList> freqMap;

    public LT_0460_LFU_Cache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 0;

        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        updateFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            updateFrequency(node);
            return;
        }

        if (size == capacity) {
            DoublyLinkedList minFreqList = freqMap.get(minFreq);
            Node nodeToRemove = minFreqList.removeLast();
            cache.remove(nodeToRemove.key);
            size--;
        }

        Node newNode = new Node (key, value, 1);
        cache.put(key, newNode);
        DoublyLinkedList list = freqMap.computeIfAbsent(1, k-> new DoublyLinkedList());

        list.addFirst(newNode);
        minFreq = 1;
        size++;
    }

    private void updateFrequency(Node node) {
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqMap.get(oldFreq);
        oldList.remove(node);
        if (oldFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }

        node.freq++;

        DoublyLinkedList newList = freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList());
        newList.addFirst(node);
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        DoublyLinkedList () {
            this.head = new Node(-1,-1, 0);
            this.tail = new Node(-1, -1, 0);
            this.size = 0;

            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;

            size++;
        }

        void remove (Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            size--;
        }

        Node removeLast () {
            if (size == 0) return null;
            Node node = tail.prev;
            remove(node);
            return node;
        }

    }

    static class Node {
        int key;
        int value;
        int freq;

        Node next;
        Node prev;

        Node (int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

    public static void main(String[] args) {
        // ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
        // [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
        // expected: [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
        LT_0460_LFU_Cache lfu = new LT_0460_LFU_Cache(2);
        lfu.put(1, 1);
        System.out.println("null");              // put(1, 1)
        lfu.put(2, 2);
        System.out.println("null");              // put(2, 2)
        System.out.println(lfu.get(1));           // expected: 1
        lfu.put(3, 3);
        System.out.println("null");              // put(3, 3) -> evicts key 2
        System.out.println(lfu.get(2));           // expected: -1
        System.out.println(lfu.get(3));           // expected: 3
        lfu.put(4, 4);
        System.out.println("null");              // put(4, 4) -> evicts key 1
        System.out.println(lfu.get(1));           // expected: -1
        System.out.println(lfu.get(3));           // expected: 3
        System.out.println(lfu.get(4));           // expected: 4
    }
}
