package thirdWeek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("第一题：");
        System.out.println("输入两个整数：");
        int one = scanner.nextInt(), two = scanner.nextInt();
        UseCompute useCom = new UseCompute();
        Compute[] computes = {new Adder(),new Subtractor(),new Multiplier(),new Divider()};
        Arrays.stream(computes).forEach(compute->useCom.userCom(compute,one,two));

        System.out.println("第二题：");
        System.out.println("请输入分数：");
        inputScore(scanner.nextInt());

        System.out.println("第三题：");
        System.out.println("请输入N；");
        int n = scanner.nextInt();
        getAverage(n,scanner);

        System.out.println("第四题：见类定义");

        System.out.println("第五题：");
//        String S = "abcde";
//        String[] words = {"a", "bb", "acd", "ace"};
        System.out.println("请输入S：");
        String S = scanner.next();
        ArrayList<String> arrayList = new ArrayList<String>();
        System.out.println("请输入words：（输入-1结束）");
        String temp = scanner.next();
        while (!temp.equals("-1")) {
            arrayList.add(temp);
            temp = scanner.next();
        }
        String[] words = new String[arrayList.size()];
        arrayList.toArray(words);
        System.out.println(getSubStringNum(S, words));

    }
//    第二题
    static void inputScore(int score){
        if (score<0 || score>100){
            throw new RuntimeException("分数必须在 0—100 之间");
        }else {
            System.out.println(score);
        }
    }
//    第三题
    static void getAverage(int n,Scanner scanner){
        double sum=0;
        System.out.println("请输入"+n+"个正数:");
        for (int i =0;i<n;){
            try {
                int temp = scanner.nextInt();
                if (temp<0){
                    throw new RuntimeException();
                }else {
                    sum+=temp;
                }
            }catch (RuntimeException e){
                System.out.println("必须是正数或者0, 请再次输入该数:");
                continue;
            }
            i++;
        }
        System.out.println("平均值是："+sum/n);
    }
//    第五题
    static int getSubStringNum(String s, String[] words){
        int num = 0;
        for (String word : words) {
            StringBuilder builder = new StringBuilder(s);
            boolean isSub = true;
            for (char c : word.toCharArray()) {
                int i = builder.indexOf(String.valueOf(c));
                if (i==-1){
                    isSub = false;
                    break;
                }else {
                    builder.replace(0,i+1,"");
                }
            }
            num+=isSub?1:0;
        }
        return num;
    }
}

//第一题
interface Compute{
    int computer(int n,int m);
}
class  Adder implements Compute{
    @Override
    public int computer(int n, int m) {
        return n+m;
    }
}
class Subtractor implements Compute{
    @Override
    public int computer(int n, int m) {
        return n-m;
    }
}
class Divider implements Compute{
    @Override
    public int computer(int n, int m) {
        return n/m;
    }
}
class Multiplier implements Compute{
    @Override
    public int computer(int n, int m) {
        return n*m;
    }
}
class UseCompute{
    public void userCom(Compute com,int one,int two){
        System.out.println(com.computer(one,two));
    }
}

//第四题：
class MyDate{
    final private int month;
    final private int day;
    final private int year;
    public MyDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    @Override
    public String toString() {
        return  month + "-" + day + "-" + year;
    }
}
abstract class Employee{
    private String name;
    private int number;
    private MyDate birthday;
    abstract double earnings();

    @Override
    public String toString() {
        return "name=" + name + "\nnumber=" + number +
                "\nbirthday=" + birthday;
    }
}