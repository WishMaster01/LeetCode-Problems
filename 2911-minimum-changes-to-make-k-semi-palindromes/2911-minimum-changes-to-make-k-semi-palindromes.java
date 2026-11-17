class Solution {

    private int segmentCost(char[] s, int l, int m, List<Integer>[] factors) {
        int best = m;
        for (int d : factors[m]) {
            int groups = m / d; 
            int changes = 0;
            for (int offset = 0; offset < d; offset++) {
                for (int t = 0; t < groups / 2; t++) {
                    if (
                        s[l + offset + t * d] !=
                        s[l + offset + (groups - 1 - t) * d]
                    ) {
                        changes++;
                    }
                }
            }
            best = Math.min(best, changes);
        }
        return best;
    }

    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] ch = s.toCharArray();
        List<Integer>[] factors = new ArrayList[n + 1];
        Arrays.setAll(factors, x -> new ArrayList<>());
        for (int d = 1; d <= n / 2; d++) {
            for (int m = d * 2; m <= n; m += d) {
                factors[m].add(d);
            }
        }
        int[][] cost = new int[n][n];
        for (int l = 0; l + 1 < n; l++) {
            for (int r = l + 1; r < n; r++) {
                cost[l][r] = segmentCost(ch, l, r - l + 1, factors);
            }
        }
        int[] prev = new int[n];
        for (int start = 0; start < n; start++) {
            prev[start] = cost[start][n - 1];
        }
        for (int seg = 2; seg <= k; seg++) {
            int[] cur = new int[n];
            Arrays.fill(cur, n);
            int limit = n - 1 - 2 * (seg - 1);
            for (int start = 0; start + 2 * seg <= n; start++) {
                for (int e = start + 1; e <= limit; e++) {
                    cur[start] = Math.min(
                        cur[start],
                        cost[start][e] + prev[e + 1]
                    );
                }
            }
            prev = cur;
        }
        return prev[0];
    }
}