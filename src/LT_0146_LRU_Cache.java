// LRU Cache — https://leetcode.com/problems/lru-cache/
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
}
