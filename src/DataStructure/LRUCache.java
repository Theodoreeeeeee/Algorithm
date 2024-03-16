package DataStructure;

import java.util.HashMap;

class LRUCache {
    int capacity;
    // 伪头结点、尾结点
    DoublyLinkedNode head;
    DoublyLinkedNode tail;

    HashMap<Integer, DoublyLinkedNode> map = new HashMap<>();

    class DoublyLinkedNode {
        int key;
        int value;
        DoublyLinkedNode prev;
        DoublyLinkedNode next;

        DoublyLinkedNode() {
        }

        DoublyLinkedNode(int prev, int value) {
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.next = head;
    }

    public int get(int key) {
        return 1;
    }

    public void put(int key, int value) {
        // 先看原map有无key
        for (int k : map.keySet()) {
            if (k == key) {
                map.remove(k);
                DoublyLinkedNode node = new DoublyLinkedNode(key, value);
                map.put(key, node);
                moveToHead(node);
            }
        }
    }

    public void addToHead(DoublyLinkedNode node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }

    public void removeNode(DoublyLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = node.next = null;
    }

    public void moveToHead(DoublyLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    public DoublyLinkedNode removeTail() {
        DoublyLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}