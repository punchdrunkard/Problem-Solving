class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        return IntStream.of(derived).reduce(0, (a,b) -> a ^ b) == 0;
    }
}