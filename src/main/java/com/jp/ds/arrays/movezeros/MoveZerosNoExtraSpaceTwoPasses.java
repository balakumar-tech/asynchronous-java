package com.jp.ds.arrays.movezeros;

import java.util.Arrays;

//Time Complexity: O(n)
//Space Complexity: O(1)
public class MoveZerosNoExtraSpaceTwoPasses {

    public static void main(String[] args) {
        int[] arr = {0,1,0,3,12};
        moveZerosTwoPasses(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void moveZerosTwoPasses(int[] arr) {
        int i =0, j = 0;
        while(i < arr.length) {
            if(arr[i] != 0) {
                arr[j] = arr[i];
                j++;
            }
            i++;
        }
        while(j < arr.length) {
            arr[j] = 0;
            j++;
        }
    }
}
