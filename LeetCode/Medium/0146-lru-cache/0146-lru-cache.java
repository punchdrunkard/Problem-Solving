class LRUCache {

    class Node {
        public int key, value;
        public Node next, prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoubleLinkedList {
        private Node head, tail;
        private int size;

        public DoubleLinkedList() {
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            this.size = 0;
            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node x) {
            x.next = head.next;
            head.next.prev = x;
            x.prev = head;
            head.next = x;
            size++;
        }

        public boolean removeLast() {
            return remove(tail.prev);
        }

        public boolean remove(Node x) {
            if (size <= 0 || x == head || x == tail) {
                return false;
            }

            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
            return true;
        }

        public Node getLast() {
            return tail.prev;
        }

        public int getSize() {
            return this.size;
        }
    }

    private HashMap<Integer, Node> map; // for read
    private DoubleLinkedList cache; // for write
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleLinkedList();
    }

    public int get(int key) {
        // System.out.println("get key " + key);

        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        makeRecently(node);
        return node.value;
    }

    public void put(int key, int value) {
        // System.out.println("put key: " + key + ", value: " + value);

        // update
        if (map.containsKey(key)) {
            // System.out.println("update");
            update(key, value);
            return;
        }

        // insert
        // System.out.println("insert");
        Node newNode = new Node(key, value);
        evictIfCapacityExceed();
        insertElement(newNode);
    }

    private void update(int key, int value) {
        Node node = map.get(key);
        node.value = value;
        makeRecently(node);
    }

    private void evictIfCapacityExceed() {
        if (cache.getSize() < capacity) {
            return;
        }

        Node leastRecentlyUsed = cache.getLast();
        // System.out.println("evict key " + leastRecentlyUsed.key);

        removeElement(leastRecentlyUsed);
    }

    private void insertElement(Node x) {
        cache.addFirst(x);
        map.put(x.key, x);
    }

    private void removeElement(Node x) {
        cache.remove(x);
        map.remove(x.key);
    }

    private void makeRecently(Node x) {
        cache.remove(x);
        cache.addFirst(x);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */