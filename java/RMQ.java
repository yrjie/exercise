class RMQ<E extends Comparable<E>>{
    private int[][] maxq, minq;
    private E[] allE;
    final private static double EPS = 1e-8;

    public RMQ(E[] allE){
        int n = allE.length;
        int logN = (int) (Math.log(1.0*n)/Math.log(2.0)+EPS);
        this.allE = allE;
        maxq = new int[n][logN+1];
        minq = new int[n][logN+1];
        for (int i=0; i<n; i++){
            maxq[i][0] = i;
            minq[i][0] = i;
        }
        for (int j=1; j<=logN; j++){
            for (int i=n-1; i>=0; i--){
                if (this.getUpper(i, j-1)+1>=n){
                    maxq[i][j] = maxq[i][j-1];
                    minq[i][j] = minq[i][j-1];
                }
                else {
                    maxq[i][j] = this.maxIdx(maxq[i][j-1], maxq[this.getUpper(i, j-1)+1][j-1]);
                    minq[i][j] = this.minIdx(minq[i][j-1], minq[this.getUpper(i, j-1)+1][j-1]);
                }
            }
        }
    }

    public E getMax(int l, int r){
        int logN = (int) (Math.log(r-l+1.0)/Math.log(2.0)+EPS);
        return allE[this.maxIdx(maxq[l][logN], maxq[this.getLower(r, logN)][logN])];
    }

    public E getMin(int l, int r){
        int logN = (int) (Math.log(r-l+1.0)/Math.log(2.0)+EPS);
        return allE[this.minIdx(minq[l][logN], minq[this.getLower(r, logN)][logN])];
    }

    private int getUpper(int i, int j){
        return i+(1<<j)-1;
    }

    private int getLower(int i, int j){
        return i-(1<<j)+1;
    }

    private int maxIdx(int idx1, int idx2){
        return allE[idx1].compareTo(allE[idx2]) > 0 ? idx1 : idx2;
    }

    private int minIdx(int idx1, int idx2){
        return allE[idx1].compareTo(allE[idx2]) < 0 ? idx1 : idx2;
    }
}
