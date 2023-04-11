package fisrtWeek;
import java.lang.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("1、B 2、B 3、D 4、B 5、D");
        //没有static不能直接调用，实例方法要由实例对象调用
        showTriangle(4);
        String s = "CQUPTYYDS";
        reverseString(s);
        isPalindrome(124321);
        ShuiXianHua();
        System.out.println();
        arrayDemo();
    }
    public static void showTriangle(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void reverseString(String s){
        char[] chars = s.toCharArray();
        char temp;
        int length =s.length();
        for (int i = 0; i < length / 2; i++) {
            temp = chars[i];
            chars[i] = chars[length-1-i];
            chars[length-1-i] = temp;
        }
        s = String.valueOf(chars);
        System.out.println(s);
    }
    //可以返回boolean类型
    public static void isPalindrome(int num){
        boolean flag = false;
        char[] chars = String.valueOf(num).toCharArray();
        int length = chars.length;
        for (int i = 0; i <= length/2; i++) {
            if(chars[i] != chars[length-1-i]){
                break;
            } else if (length - 1 - i == i || length - 1 - i == i + 1) {
                flag = true;
            }
        }
        if (flag){
            System.out.println("是的");
        }else {
            System.out.println("不是");
        }
    }
    public static void ShuiXianHua(){
        for (int i = 100; i < 999; i++) {
            if (Math.pow(i%10,3) + Math.pow(i/100,3) + Math.pow(i/10%10,3) == i){
                System.out.print(i+" ");
            }
        }
    }
    public static void arrayDemo(){
        int[] data = {6,5,4,3,2,1,9,8,7,10};
        int min = data[0];
        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max){
                max = data[i];
            }else if (data[i] < min){
                min = data[i];
            }
        }
        System.out.println("min:" + min + "  max:" + max + "  最值之和：" + (min+max));
    }
}
