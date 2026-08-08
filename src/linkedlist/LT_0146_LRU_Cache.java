// LRU Cache — https://leetcode.com/problems/lru-cache/
package linkedlist;

import java.util.*;

class LT_0146_LRU_Cache {

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    int capacity;
    Map<Integer, Node> map;

    public LT_0146_LRU_Cache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);

        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        insert(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node oldNode = map.get(key);
            remove(oldNode);
        }

        Node newNode = new Node(key, value);
        insert(newNode);
        map.put(key, newNode);

        if(map.size() > capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }

    }

    public void remove(Node node) {
        // Head <-> A <-> B <-> C <-> Tail
        Node prevNode = node.prev;
        Node nextNode = node.next;

        if(prevNode!=null) prevNode.next = nextNode;
        if(nextNode!=null) nextNode.prev = prevNode;

        node.next = null;
        node.prev = null;
    }

    public void insert(Node node) {
        // Head <-> A <-> B <-> C <-> Tail
        Node nextNode = head.next;
        
        head.next = node;

        node.next = nextNode;
        node.prev = head;

        nextNode.prev = node;
    }

    public static void main(String[] args) {
        // ["LRUCache","put","put","get","put","get","put","get","get","get"]
        // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        // expected: [null, null, null, 1, null, -1, null, -1, 3, 4]
        LT_0146_LRU_Cache cache = new LT_0146_LRU_Cache(2);
        System.out.println("null");              // LRUCache(2)
        cache.put(1, 1);
        System.out.println("null");              // put(1, 1)
        cache.put(2, 2);
        System.out.println("null");              // put(2, 2)
        System.out.println(cache.get(1));         // expected: 1
        cache.put(3, 3);
        System.out.println("null");              // put(3, 3) -> evicts key 2
        System.out.println(cache.get(2));         // expected: -1
        cache.put(4, 4);
        System.out.println("null");              // put(4, 4) -> evicts key 1
        System.out.println(cache.get(1));         // expected: -1
        System.out.println(cache.get(3));         // expected: 3
        System.out.println(cache.get(4));         // expected: 4
    }
}
