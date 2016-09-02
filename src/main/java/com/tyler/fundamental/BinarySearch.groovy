package com.tyler.fundamental;

/**
 *  Created by Created by XD.Wang on 2016/9/1.
 *  二分搜索
 */
public class BinarySearch {

    private BinarySearch(){}

    /**
     *  返回关键字的在排序完成后于数组中的下标
     */
    public static int indexOf(int[] a, int key){
        int lo = 0
        int sortCount = a.length - 1
        while(lo <= sortCount){
            int mid = lo + (sortCount - lo) / 2
            if(key < a[mid]) sortCount = mid - 1
            else if(key > a[mid]) lo = mid + 1
            else return mid
        }
        -1
    }

}
