package com.jp.ds.arrays;

import java.util.Arrays;

public class TwoPointerTwoSum {
    public static void main(String[] args) {
        int[] arr = {4, 7, 8, 1, 3};
        twoSum(arr, 7);
    }

    public static void twoSum(int[] arr, int target) {

        Arrays.sort(arr);

        int i = 0, j= arr.length - 1;

        while(i < j) {

            if(arr[i] + arr[j] == target) {
                System.out.println(arr[i]+","+arr[j]);
                break;
            } else if(arr[i] + arr[j] < target) {
                i++;
            } else {
                j--;
            }
        }
    }
}
