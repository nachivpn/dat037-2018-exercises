public class C1 {
    /**
     * Prints all subsets of the array.
     *
     * Given array length of N, the method takes O(2^N) time.
     *
     * @param a the array
     * @throws IllegalArgumentException if array missing
     */
    public static void printSubsets(int[] a) {
        if (a == null) {
            throw new IllegalArgumentException();
        }
        boolean[] c = new boolean[a.length];
        printSubsets(a, c, 0);
    }

    private static void printSubsets(int[] a, boolean[] c, int i) {
        if (i == a.length) {
            boolean printed = false;
            System.out.print("[");
            for (int j = 0; j < a.length; j++) {
                if (c[j]) {
                    System.out.print((printed ? ", " : "") + a[j]);
                    printed = true;
                }
            }
            System.out.println("]");
        } else {
            c[i] = false;
            printSubsets(a, c, i + 1);
            c[i] = true;
            printSubsets(a, c, i + 1);
        }
    }

    public static void main(String[] args){
        printSubsets(new int[] { 1, 2, 3});
    }
}
