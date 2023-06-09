package fifthWeek;

import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student[] students = {new Student("s001","老大",20,"计算机学院"),
                new Student("s002","老二",19,"计算机学院"),
                new Student("s003","老三",18,"计算机学院"),
                new Student("s004","老四",17,"计算机学院")};
        deleteBySNO("s001");
        deleteBySNO("s002");
        deleteBySNO("s003");
        deleteBySNO("s004");//重复运行

        insertData(students);
        System.out.println("第1题的效果");
        showData();

        System.out.println("第2题的效果");
        showData();

        deleteBySNO("s004");
        System.out.println("第3题的效果");
        showData();

        System.out.println("第4题:"+queryBySno("s003"));
        System.out.println("第4题的效果");
        showData();

        students[0].setCollege("通信学院");
        updateById(students[0]);
        System.out.println("第5题的效果");
        showData();

        int[] data = {9,7,8,5,6,3,2,4,1};

        System.out.println("冒泡排序：");
        Algorithm.bubbleSort(data);
        System.out.println(Arrays.toString(data));

        System.out.println("插入排序：");
        Algorithm.insertionSort(data);
        System.out.println(Arrays.toString(data));

        System.out.println("快速排序：");
        Algorithm.quickSort(data,0,data.length-1);
        System.out.println(Arrays.toString(data));

        System.out.println("合并排序：");
        System.out.println(Arrays.toString(Algorithm.mergeSort(data)));

        System.out.println("堆排序：");
        Algorithm.heapSort(data);
        System.out.println(Arrays.toString(data));

    }
    public static void insertData(Student[] students){
        Arrays.stream(students).forEach(Main::insertStudent);
    }
    public static void showData() {
        System.out.println("---------------------");
        queryAllStudent().forEach(item->{
            System.out.println(item.getSNO() + ","+item.getName() + "," + item.getAge() + "," + item.getCollege());
        });
        System.out.println("---------------------");
    }
    public static int insertStudent(Student s){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String prepare = "insert into student (SNO,Name,Age,College) values (?,?,?,?)";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(prepare);
            preparedStatement.setString(1,s.getSNO());
            preparedStatement.setString(2,s.getName());
            preparedStatement.setInt(3,s.getAge());
            preparedStatement.setString(4,s.getCollege());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return  -1;
        }finally {
            DBUtil.closeAll(null,preparedStatement,null);
        }
    }
    public static int deleteBySNO(String sno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String prepare = "delete from student where SNO = ?";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(prepare);
            preparedStatement.setString(1,sno);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return  -1;
        }finally {
            DBUtil.closeAll(null,preparedStatement,null);
        }
    }
    public static int updateById(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String prepare = "update student set Name = ?, Age = ?, College = ? where SNO = ?";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(prepare);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2,student.getAge());
            preparedStatement.setString(3,student.getCollege());
            preparedStatement.setString(4,student.getSNO());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return  -1;
        }finally {
            DBUtil.closeAll(null,preparedStatement,null);
        }
    }
    public static Student queryBySno(String sno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String prepare = "select * from student where SNO = ?";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(prepare);
            preparedStatement.setString(1,sno);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Student(resultSet.getString("SNO"), resultSet.getString("Name"),
                        resultSet.getInt("Age"),resultSet.getString("College"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.closeAll(null,preparedStatement,resultSet);
        }
    }
    public static List<Student> queryAllStudent() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> students = new ArrayList<>();
        String prepare = "select SNO,Name,Age,College from student";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(prepare);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                students.add(new Student(resultSet.getString("SNO"), resultSet.getString("Name"),
                        resultSet.getInt("Age"), resultSet.getString("College")));
            }
            return  students;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.closeAll(null,preparedStatement,resultSet);
        }
    }
}
class Student{
    private String SNO;
    private String name;
    private int age;
    private String college;

    public Student(String SNO, String name, int age, String college) {
        this.SNO = SNO;
        this.name = name;
        this.age = age;
        this.college = college;
    }

    public String getSNO() {
        return SNO;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Student{" +
                "SNO='" + SNO + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", college='" + college + '\'' +
                '}';
    }
}
class DBUtil{
    public final static ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();

    private final static String url = "jdbc:mysql://localhost:3306/csa?useSSL=false" + "&useUnicode=true&characterEncoding=utf-8"
            + "&serverTimezone=GMT%2B8";
    private final static String user = "root", password = "root",driverName = "com.mysql.cj.jdbc.Driver";
    public static Connection getConnection(){
        Connection connection = THREAD_LOCAL.get();
        if (connection == null){
            try {
                Class<?> load = Class.forName(driverName);
                return DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet){
        if (connection!=null){
            try {
                connection.close();
                THREAD_LOCAL.remove();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class Algorithm{
    public static void bubbleSort(int[] data){
        for (int i = 0; i < data.length-1; i++) {
            for (int j = 0; j < data.length-1-i; j++) {
                if (data[j]>data[j+1]){
                    int temp = data[j];
                    data[j]=data[j+1];
                    data[j+1]=temp;
                }
            }
        }
    }
    public static void insertionSort(int[] data){
        for (int i = 1; i < data.length; i++) {
            int j = i-1, temp = data[i];
            while (j>=0&&data[j]>temp){
                data[j+1]=data[j];
                j--;
            }
            data[j+1]=temp;
        }
    }
    public static void quickSort(int[] data,int left,int right){
        if (right-left>0){
            int s = partition(data,left,right);
            quickSort(data,left,s-1);
            quickSort(data,s+1,right);
        }
    }
    public static int partition(int[] data,int left,int right){
        int i=left+1,j=right;
        boolean b = false;
        while (true){
            while (i<=right&&data[i]<data[left]){
                i++;
            }
            while (j>=left&&data[j]>data[left]){
                j--;
            }
            if(i>=j){
                break;
            }
            int temp = data[i];
            data[i]=data[j];
            data[j]=temp;
        }
        if (data[j]!=data[left]){
            int temp = data[left];
            data[left]=data[j];
            data[j]=temp;
        }
        return j;
    }
    public static int[] mergeSort(int[] data){
        if (data.length<=1){
            return data;
        }
        if (data.length==2){
            if (data[0]>data[1]){
                int temp = data[0];
                data[0]=data[1];
                data[1]=temp;
            }
            return data;
        }
        int[] a = mergeSort(Arrays.copyOfRange(data, 0, data.length / 2));
        int[] b = mergeSort(Arrays.copyOfRange(data, data.length / 2, data.length));
        return merge(a, b);
    }
    public static int[] merge(int[] a,int[] b){
        int[] arr = new int[a.length+b.length];
        int i=0,j=0,index=0;
        while (i<a.length&&j<b.length){
            arr[index]=Math.min(a[i],b[j]);
            if (a[i]<b[j]){
                i++;
            }else {
                j++;
            }
            index++;
        }
        while (i<a.length){
            arr[index++]=a[i++];
        }
        while (j<b.length){
            arr[index++]=b[j++];
        }
        return arr;
    }
    public static void heapSort(int[] data){
        for (int i = 0; i < data.length; i++) {
            maxHeapify(data,data.length-i);
            int temp=data[0];
            data[0]=data[data.length-1-i];
            data[data.length-1-i]=temp;
        }
    }
    public static void maxHeapify(int[] data,int length){
        int i = length / 2;
        for (; i >=0 ; i--) {
            if (2*i+1<length&&data[2*i+1]>data[i]){
                int temp=data[i];
                data[i]=data[2*i+1];
                data[2*i+1]=temp;
            }
            if (2*i+2<length&&data[2*i+2]>data[i]){
                int temp=data[i];
                data[i]=data[2*i+2];
                data[2*i+2]=temp;
            }
        }
    }

}

