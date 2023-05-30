package forthWeek;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(reverseInt(0));
        System.out.println(climbStairs(1));
        int[] nums ={1,2,3};
        subsets(nums);
    }

    static int reverseInt(int x) {
        String s = x>=0?String.valueOf(x):String.valueOf(x).substring(1);
        String maxString = String.valueOf(Integer.MAX_VALUE);
        String minAbsString = String.valueOf(Integer.MIN_VALUE).substring(1);
        String reversed = new StringBuffer(s).reverse().toString();
        if (x>=0){
            if (reversed.length()==maxString.length() && reversed.compareTo(maxString)>0){
                return 0;
            }
            return Integer.parseInt(reversed);
        }else {
            if (reversed.length()==minAbsString.length() && reversed.compareTo(minAbsString)>0){
                    return 0;
            }
            return Integer.parseInt("-"+reversed);
        }
    }
//        f(n) = f(n-1)+f(n-2)
//        f(1) = 1 f(2) = 2
    static int climbStairs(int n){
        int[] facts = new int[n];
        facts[0]=1;
        if (n>1){
            facts[1]=2;
        }
        for (int i = 2; i < n; i++) {
            facts[i]=facts[i-1]+facts[i-2];
        }
        return facts[n-1];
    }
    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subList = new ArrayList<>();
        int x = (1<<nums.length) -1;
        for (; x>=0;x--) {
//            System.out.println(Integer.toBinaryString(x));
            List<Integer> temp=new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (((1<<i)&x)!=0){
                    temp.add(nums[i]);
                }
            }
            subList.add(temp);
        }
        return subList;
    }
}
