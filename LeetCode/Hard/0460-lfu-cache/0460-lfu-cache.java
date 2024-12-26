class LFUCache {

    private int capacity, size;
    private Map<Integer, Integer> counter, valueMap;
    private Map<Integer, Map<Integer, Integer>> cache;

    private static int minFrequency = Integer.MAX_VALUE;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        counter = new HashMap<>();
        valueMap = new HashMap<>();
        cache = new HashMap<>();
        cache.put(1, new LinkedHashMap<>());
    }

    public int get(int key) {
        if (doesNotExist(key)) {
            return -1;
        }

        updateFrequency(key);
        return getValue(key);
    }

    public void put(int key, int value) {
        if (exist(key)) {
            valueMap.put(key, value);
            updateFrequency(key);
            return;
        }

        if (isCacheFull()) {
            evict();
        }

        // 원소 삽입
        minFrequency = 1;
        insertElement(key, value, 1);
    }

    private void insertElement(int key, int value, int freq) {
        valueMap.put(key, value);
        counter.put(key, 1);
        cache.get(freq).put(key, value);
        size++;
    }

    private void evict() {
        Map<Integer, Integer> leastFrequencyUsedMap = cache.get(minFrequency);

        int victim = leastFrequencyUsedMap.keySet().iterator().next();
        valueMap.remove(victim);
        counter.remove(victim);
        leastFrequencyUsedMap.remove(victim);
    }

    private boolean isCacheFull() {
        return size >= capacity;
    }

    private void updateFrequency(int key) {

        int currentCount = counter.get(key);
        int nextCount = currentCount + 1;

        // update counter map
        counter.put(key, nextCount);

        // update cache
        // - remove this element in previousCount
        cache.get(currentCount).remove(key);

        // - update min frequency 
        if (currentCount == minFrequency && cache.get(currentCount).isEmpty()) {
            minFrequency++;
        }

        // update key
        if (!cache.containsKey(nextCount)) {
            cache.put(nextCount, new LinkedHashMap<>());
        }

        cache.get(nextCount).put(key, getValue(key));
    }

    private boolean doesNotExist(int key) {
        return !valueMap.containsKey(key);
    }

    private boolean exist(int key) {
        return valueMap.containsKey(key);
    }

    private int getValue(int key) {
        return valueMap.get(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);leastFrequencyUsedMap
 */