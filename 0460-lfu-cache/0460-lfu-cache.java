import java.util.*;

class LFUCache {
    private class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private class DoublyList {
        Node head, tail;
        int size;

        DoublyList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
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

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size == 0) return null;
            Node node = tail.prev;
            remove(node);
            return node;
        }
    }

    private final int capacity;
    private int minFreq;

    private final Map<Integer, Node> values = new HashMap<>();
    private final Map<Integer, Integer> freq = new HashMap<>();
    private final Map<Integer, DoublyList> groups = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!values.containsKey(key)) return -1;

        increaseFreq(key);
        return values.get(key).value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (values.containsKey(key)) {
            values.get(key).value = value;
            increaseFreq(key);
            return;
        }

        if (values.size() == capacity) {
            DoublyList list = groups.get(minFreq);
            Node removed = list.removeLast();

            values.remove(removed.key);
            freq.remove(removed.key);
        }

        Node node = new Node(key, value);
        values.put(key, node);
        freq.put(key, 1);

        groups.computeIfAbsent(1, f -> new DoublyList())
              .addFirst(node);

        minFreq = 1;
    }

    private void increaseFreq(int key) {
        Node node = values.get(key);
        int oldFreq = freq.get(key);

        DoublyList oldList = groups.get(oldFreq);
        oldList.remove(node);

        if (oldFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }

        int newFreq = oldFreq + 1;
        freq.put(key, newFreq);

        groups.computeIfAbsent(newFreq, f -> new DoublyList())
              .addFirst(node);
    }
}