package ua.yandex.mergesort;

/**
 *
 * @author Andrii Hyryla
 */
public class MergeSort {

    static class MergeSortThread implements Runnable {

        private final MergeSort mg = new MergeSort();
        private final int[] a;
        private final int leftA;
        private final int rightA;
        private final int[] b;
        private final int leftB;

        public MergeSortThread(int[] a, int leftA, int rightA,
                int[] b, int leftB) {
            this.a = a;
            this.leftA = leftA;
            this.rightA = rightA;
            this.b = b;
            this.leftB = leftB;
        }

        @Override
        public void run() {
            mg.mergeSort(a, leftA, rightA, b, leftB);
        }

    }

    static class MergeThread implements Runnable {

        private final MergeSort mg = new MergeSort();
        private final int[] a;
        private final int left1;
        private final int right1;
        private final int left2;
        private final int right2;
        private final int[] b;
        private final int leftB;

        public MergeThread(int[] a, int left1, int right1,
                int left2, int right2, int[] b, int leftB) {
            this.a = a;
            this.left1 = left1;
            this.right1 = right1;
            this.left2 = left2;
            this.right2 = right2;
            this.b = b;
            this.leftB = leftB;
        }

        @Override
        public void run() {
            mg.merge(a, left1, right1, left2, right2, b, leftB);
        }

    }

    public void mergeSort(int[] a, int leftA, int rightA, int[] b, int leftB) {
        int n = rightA - leftA + 1;
        if (n == 1) {
            b[leftB] = a[leftA];
        } else {
            int[] temp = new int[n];
            int mid = (leftA + rightA) / 2;
            int newMid = mid - leftA;
            MergeSortThread mgth1 = new MergeSortThread(a, leftA, mid, temp, 0);
            MergeSortThread mgth2 = new MergeSortThread(a, mid + 1,
                    rightA, temp, newMid + 1);
            Thread th1 = new Thread(mgth1);
            Thread th2 = new Thread(mgth2);
            th1.start();
            th2.start();
            try {
                th1.join();
                th2.join();
            } catch (InterruptedException ex) {
            }

            merge(temp, 0, newMid, newMid + 1, n - 1, b, leftB);

        }
    }

    public void merge(int[] a, int left1, int right1, int left2,
            int right2, int[] b, int leftB) {
        int n1 = right1 - left1 + 1;
        int n2 = right2 - left2 + 1;
        if (n1 < n2) {
            int temp;
            temp = left1;
            left1 = left2;
            left2 = temp;
            temp = right1;
            right1 = right2;
            right2 = temp;
            temp = n1;
            n1 = n2;
            n2 = temp;
        }

        if (n1 == 0) {
        } else {
            int mid1 = (left1 + right1) / 2;
            int mid2 = binarySearch(a[mid1], a, left2, right2);
            int mid3 = leftB + (mid1 - left1) + (mid2 - left2);
            b[mid3] = a[mid1];
            MergeThread mth1 = new MergeThread(a, left1, mid1 - 1,
                    left2, mid2 - 1, b, leftB);
            MergeThread mth2 = new MergeThread(a, mid1 + 1, right1,
                    mid2, right2, b, mid3 + 1);
            Thread th1 = new Thread(mth1);
            Thread th2 = new Thread(mth2);
            th1.start();
            th2.start();
            try {
                th1.join();
                th2.join();
            } catch (InterruptedException ex) {
            }
        }

    }

    private int binarySearch(int find, int[] a, int left, int right) {
        int low = left;
        int high = left;
        if (right >= high) {
            high = right + 1;
        }
        while (low < high) {
            int mid = (low + high) / 2;
            if (find <= a[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }
}
