package com.jp.ds.arrays.movezeros;

import java.util.Arrays;

//Time Complexity: O(n)
//Space Complexity: O(n)
public class MoveZerosExtraSpaceSinglePass {

    public static void main(String[] args) {
        int[] arr = {0,1,0,3,12};
        int[] ans = new int[arr.length];

        int i=0,j=0;

        while(i < arr.length) {
            if(arr[i] != 0) {
                ans[j] = arr[i];
                j++;
            }
            i++;
        }

        System.out.println(Arrays.toString(ans));
    }
}
