package com.jp.ds.arrays;

import java.util.Arrays;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] h1 = {0,1,0,2,1,0,1,3,2,1,2,1};

        int[] h2 = {4,2,0,3,2,5};

        System.out.println(trap(h1));

        System.out.println(trap(h2));
    }

    public static int trap(int[] height) {

        int n = height.length;
        int totalWater = 0;
        int left = 0, right = n - 1;
        int leftMax = height[left], rightMax = height[right ];
        while( left < right ) {

            if(leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                totalWater += leftMax - height[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                totalWater += rightMax - height[right];
            }
        }

        return totalWater;
    }

}
