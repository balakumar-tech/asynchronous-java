package com.jp.ds.arrays.movezeros;

import java.util.Arrays;

//Time Complexity: O(n)
//Space Complexity: O(1)
public class MoveZerosNoExtraSpaceSinglePass {

    public static void main(String[] args) {
        int[] arr = {0,1,0,3,12};

        moveZerosSinglePass(arr);

        System.out.println(Arrays.toString(arr));
    }

    static void moveZerosSinglePass(int[] arr) {
        int lastPosition = 0;
        int currentPosition = 0;

        while(currentPosition < arr.length) {

            if(arr[currentPosition] != 0) {
                int temp = arr[currentPosition];
                arr[currentPosition] = arr[lastPosition];
                arr[lastPosition] = temp;
                lastPosition++;
            }
            currentPosition++;
        }
    }
}
