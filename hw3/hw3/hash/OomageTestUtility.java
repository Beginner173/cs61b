package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int N = oomages.size();
        int[] Array =new int[M];
        for (int i : Array) {
            i = 0;
        }
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            Array[bucketNum] = Array[bucketNum] + 1;
        }
        for (int i : Array) {
            if ((i < N/50) || (i > N/2.5)) {
                return false;
            }
        }
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        return true;
    }
}
