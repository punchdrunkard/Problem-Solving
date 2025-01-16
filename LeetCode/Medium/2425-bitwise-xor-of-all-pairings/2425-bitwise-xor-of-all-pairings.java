class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int answer = 0;

        // If nums2.length is odd, XOR all elements of nums1
        if (nums2.length % 2 != 0) {
            answer ^= calculateXORInArray(nums1);
        }

        // If nums1.length is odd, XOR all elements of nums2
        if (nums1.length % 2 != 0) {
            answer ^= calculateXORInArray(nums2);
        }

        return answer;
    }

    int calculateXORInArray(int[] arr) {
        return IntStream.of(arr).reduce(0, (a, b) -> a ^ b);
    }
}