class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        
        for (char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        List<Character>[] buckets = new ArrayList[s.length() + 1];

        for (char ch : freq.keySet()) {
            int count = freq.get(ch);
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }

            buckets[count].add(ch);
        }

        StringBuilder result = new StringBuilder();

        for (int i = buckets.length - 1; i >= 1; i--) {
            if (buckets[i] != null) {
                for (char ch : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        result.append(ch);
                    }
                }
            }
        }

        return result.toString();
    }
}