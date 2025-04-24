class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> freqMap = new HashMap<>();

        int right = 0;

        for (int left = 0; left < s.length(); left++) {

            // TODO 10글자가 되면 멈추기 
            while (right < s.length() && right - left + 1 < 10) {
                right++;
            }

            if ((right - left + 1) == 10 && right < s.length()) {
                String subString = s.substring(left, right + 1);
                freqMap.put(subString, freqMap.getOrDefault(subString, 0) + 1);
            }
        }

        List<String> result = freqMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .toList();

        return result;
    }
}