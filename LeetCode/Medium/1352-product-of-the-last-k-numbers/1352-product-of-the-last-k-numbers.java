class ProductOfNumbers {
    
    private List<Integer> prefixProduct = new ArrayList<>();
    private int size = 0;

    public ProductOfNumbers() {
        init();
    }
    
    public void add(int num) {
        if (num == 0) {
            init();
            return;
        }

        prefixProduct.add(prefixProduct.get(size) * num);
        size++;
    }
    
    public int getProduct(int k) {
        if (size < k) {
            return 0;
        }

        return prefixProduct.get(size) / prefixProduct.get(size - k);
    }

    private void init() {
        prefixProduct = new ArrayList<>();
        prefixProduct.add(1); // for multiplication
        size = 0;
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */