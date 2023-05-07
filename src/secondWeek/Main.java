package secondWeek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        第一题
        System.out.println("<-------第一题------->");
        Monkey monkey = new Monkey("m");
        monkey.speak();
        People people = new People("p");
        people.speak();
        people.think();
//        第二题
        System.out.println("<-------第二题------->");
        Vehicle vehicle = new Vehicle(4, 1150);
        vehicle.display();
        System.out.println("-------------------------------");
        Car car1 = new Car(4, 1150, 3);
        car1.display();
        System.out.println("-------------------------------");
        Car car2 = new Car(4, 1150, 6);
        car2.display();
        System.out.println("-------------------------------");
        Trunk trunk1 = new Trunk(6, 15000, 1, 3000);
        trunk1.display();
        System.out.println("-------------------------------");
        Trunk trunk2 = new Trunk(6, 15000, 1, 7000);
        trunk2.display();
        System.out.println("-------------------------------");
        Trunk trunk3 = new Trunk(6, 15000, 3, 7000);
        trunk3.display();
//        第三题
        System.out.println("<-------第三题------->");
        System.out.print("输入：a=\"88888888888888888\",b=\"25461214124533465\"\n输出：c=");
        System.out.println(getSum("88888888888888888", "25461214124533465"));
        System.out.print("输入：a=\"13829579081298345918257\",b=\"438823897418920918472193\"\n输出：c=");
        System.out.println(getSum("13829579081298345918257", "438823897418920918472193"));
//        第四题
        System.out.println("<-------第四题------->");
        System.out.println(uniquePaths(3, 7));
//        第五题
        System.out.println("<-------第五题------->");
        String[] strs1= {"dog","racecar '","car"};
        System.out.println("输入：str="+Arrays.toString(strs1));
        String result = longestCommonPrefix(strs1);
        if(result.equals("")){
            System.out.println("输出：\"\"");
            System.out.println("解释：输入不存在公共前缀");
        }else{
            System.out.println("输出：\""+result+"\"");
        }
        System.out.println( );
        String[] strs2={"f1ower","f1ow","f1ight"};
        System.out.println("输入：str="+Arrays.toString(strs1));
        result = longestCommonPrefix(strs2);
        if(result.equals("")){
            System.out.println("输出：\"\"");
            System.out.println("解释：输入不存在公共前缀");
        }else{
            System.out.println("输出：\""+result+"\"");
        }
    }

    public static String getSum(String a, String b) {
        List<Integer> la = new ArrayList<Integer>();
        List<Integer> lb = new ArrayList<Integer>();
        String c = "";
        for (int i = a.length() - 1; i >= 0; --i) {
            la.add(a.charAt(i) - '0');
        }
        for (int i = b.length() - 1; i >= 0; --i) {
            lb.add(b.charAt(i) - '0');
        }
        int length = Math.max(a.length(), b.length()) + 1;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int sum = carry;
            if (i < a.length()) {
                sum += la.get(i);
            }
            if (i < b.length()) {
                sum += lb.get(i);
            }
            if (0 == sum) {
                break;
            }
            carry = sum / 10;
            builder.insert(0, +sum % 10);
        }
        c = builder.toString();
        return c;
    }

    static int uniquePaths(int m, int n) {
        int[][] a = new int[m][n];
        Arrays.fill(a[0], 1);
        for (int i = 0; i < m; i++) {
            a[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                a[i][j] = a[i - 1][j] + a[i][j - 1];
            }
        }
        return a[m - 1][n - 1];
    }

    public static String longestCommonPrefix(String[] strs) {
        String ans = "";
        StringBuilder builder = new StringBuilder();
        int count = 0;
        boolean flag=true;
        while (flag){
            char c=' ';
            for (int i = 0; i < strs.length; i++) {
                if (0==i&&count<strs[0].length()){
                    c=strs[0].charAt(count);
                }
                if (count>=strs[i].length()||strs[i].charAt(count)!=c){
                    flag=false;
                    break;
                }
                if (i==strs.length-1){
                    builder.append(c);
                }
            }
            count++;
        }
        ans=builder.toString();
        return ans;
    }

}

class Monkey {
    private String name;

    Monkey(String s) {
        this.name = s;
    }

    public void speak() {
        System.out.println("咿咿呀呀 .....");
    }
}

class People extends Monkey {

    People(String s) {
        super(s);
    }

    @Override
    public void speak() {
        System.out.println("小样儿，不错嘛！会说话了！");
    }

    void think() {
        System.out.println("别说话！认真思考！");
    }
}

class Vehicle {
    protected int wheels;
    protected double weight;

    public Vehicle(int wheels, double weight) {
        this.wheels = wheels;
        this.weight = weight;
    }

    public void display() {
        System.out.println("车轮的个数是：" + wheels + "  车重：" + weight);
    }
}

class Car extends Vehicle {
    private int loader;
    private static final int MAX_LOADER = 6;

    public Car(int wheels, double weight, int loader) {
        super(wheels, weight);
        this.loader = loader;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("这是一辆小车，能载" + MAX_LOADER + "人"
                + ",实载" + loader + "人"
                + ((loader < MAX_LOADER) ? "" : ",你超员了！！！"));
    }
}

class Trunk extends Vehicle {

    private int loader;
    private static final int MAX_LOADER = 3;

    private double payload;
    private static final double MAX_PAYLOAD = 5000;

    public Trunk(int wheels, double weight, int loader, double payload) {
        super(wheels, weight);
        this.loader = loader;
        this.payload = payload;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("这是一辆小卡车，能载" + MAX_LOADER + "人"
                + ",实载" + loader + "人"
                + ((loader < MAX_LOADER) ? "" : ",你超员了！！！"));
        System.out.println("这是一辆卡车，核载" + MAX_PAYLOAD + "kg"
                + ",你已装载" + payload + "kg"
                + ((payload < MAX_PAYLOAD) ? "" : ",你超载了！！！"));

    }
}

