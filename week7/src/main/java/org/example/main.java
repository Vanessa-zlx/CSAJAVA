package org.example;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

public class main {
    public static void main(String[] args) {
        /*第一题*/
        int[] nums1 = {4,1,2},nums2 = {1,3,4,2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
        /*第二题*/
        int[] nums3 = {1,2,3,4,5},nums4={4,3,5,1,2};
//        int[] nums3 = {1,2,3,4,5},nums4={4,5,3,2,1};
        System.out.println(validateStackSequences(nums3,nums4));
        /*第三题*/
        int[] nums5 = {1,2,3,2};
        System.out.println(sumOfUnique(nums5));

    }
    /*第一题*/
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            boolean equal = false,found=false;
            for(int j=0;j<nums2.length;j++){
                if(nums2[j]==nums1[i]){
                    equal=true;
                }
                if(equal && nums2[j]>nums1[i]){
                    ans[i]=nums2[j];
                    found=true;
                    break;
                }
            }
            if(!equal||!found){
                ans[i]=-1;
            }
        }
        return ans;
    }
    /*第二题*/
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<Integer>();
        int i=0,j=0;
        while (true){
            if (i<pushed.length && (s.empty()||s.peek()!=popped[j])){
                s.push(pushed[i++]);
            }else if (j<popped.length){
                if (popped[j]!=s.peek()){
                    return false;
                }
                s.pop();
                j++;
            }
            if (i>=pushed.length&&j>=popped.length){
                break;
            }
        }
        return s.empty();
    }
    /*第三题*/
    public static int sumOfUnique(int[] nums) {
        int result =0;
        Map<Integer,Boolean> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)){
                map.put(num,true);
            }else {
                map.replace(num,false);
            }
        }
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            if (entry.getValue()){
                result+=entry.getKey();
            }
        }
        return result;
    }
}
