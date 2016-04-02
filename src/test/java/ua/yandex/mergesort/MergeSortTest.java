package ua.yandex.mergesort;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Andrii Hyryla
 */
public class MergeSortTest {

    @Test
    public void mergeSortTestWithTwoElements() {
        int[] a = {5, 1};//, 3, 12, 6, 8, 11, 9};
        int[] res = {1, 5};//, 3, 5, 6, 8, 9, 11, 12};
        int[] expRes = new int[2];
        MergeSort mg = new MergeSort();
        mg.mergeSort(a, 0, 1, expRes, 0);
        Assert.assertArrayEquals(res, expRes);
    }

    @Test
    public void mergeSortTestWithEightElements() {
        int[] a = {5, 1, 3, 12, 6, 8, 11, 9};
        int[] res = {1, 3, 5, 6, 8, 9, 11, 12};
        int[] expRes = new int[8];
        MergeSort mg = new MergeSort();
        mg.mergeSort(a, 0, 7, expRes, 0);
        Assert.assertArrayEquals(res, expRes);
    }
    
    @Test
    public void mergeSortTestWithManyElements() {
        int[] a = {5, 1, 3, 12, 6, 8, 11, 9, 51, 48, 89, 125, 4, 16, 142, -6, -91, 85, -31, 31};
        int[] res = {-91, -31, -6, 1, 3, 4, 5, 6, 8, 9, 11, 12, 16, 31, 48, 51, 85, 89, 125, 142};
        int[] expRes = new int[20];
        MergeSort mg = new MergeSort();
        mg.mergeSort(a, 0, 19, expRes, 0);
        Assert.assertArrayEquals(res, expRes);
    }
}
