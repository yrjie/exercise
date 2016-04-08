public static class SuffixArray {
    private class RankCmp implements Comparator<Integer> {
        @Override
        public int compare(Integer x1, Integer x2) {
            if (rank[x1] != rank[x2]) {
                return rank[x1] - rank[x2];
            } else {
                int r1 = x1 + k >= n ? -1 : rank[x1 + k];
                int r2 = x2 + k >= n ? -1 : rank[x2 + k];
                return r1 - r2;
            }
        }
    }

    public Integer[] sa;
    public int[] rank;
    public int[] height;
    private int n;
    private String str;
    private RankCmp cmp;
    private int k;

    public SuffixArray(String str) {
        this.str = str;
        this.n = str.length();
        this.sa = new Integer[n + 1];
        this.rank = new int[n + 1];
        this.height = new int[n + 1];
        cmp = new RankCmp();
        constructSa();
        constructH();
    }

    private void constructSa() {
        for (int i = 0; i <= n; i++) {
            rank[i] = i == n ? -1 : str.charAt(i);
            sa[i] = i;
        }
        Arrays.sort(sa, cmp);
        for (k = 1; k < n; k += k) {
            Arrays.sort(sa, cmp);
            int[] tmp = new int[n + 1];
            tmp[sa[0]] = 0;
            for (int i = 1; i <= n; i++) {
                tmp[sa[i]] = tmp[sa[i - 1]] + (cmp.compare(sa[i], sa[i - 1]) == 0 ? 0 : 1);
            }
            for (int i = 0; i <= n; i++) {
                rank[i] = tmp[i];
            }
        }
        for (int i = 0; i <= n; i++) {
            rank[sa[i]] = i;
        }
    }

    private void constructH() {
        int h = 0;
        height[sa[0]] = 0;
        for (int i = 0; i < n; i++) {
            int j = sa[rank[i] - 1];
            if (h > 0) h--;
            while (i + h < n && j + h < n && str.charAt(i+h) == str.charAt(j+h)) {
                h++;
            }
            height[rank[i]] = h;
        }
    }
}