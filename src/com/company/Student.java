package com.company;
import java.util.*;
/**
 * Created by vianrosal on 4/12/17.
 */
public class Student implements Comparable{
public String name = "[n.a]";
public int snum;
    public double q1;
    public double q2;
    public double t;
    public ArrayList<Double> scoresq1 = new ArrayList<>();
    public ArrayList<Double> scoresq2 = new ArrayList<>();
     public static ArrayList<Integer> snums = new ArrayList<Integer>();
    public static ArrayList<Student> all = new ArrayList<>();
    public static boolean numsNull = false;
public Student (Integer i){
    snum = i;
    snums.add(i);
    if(!numsNull){
        numsNull = true;
    }
}
    public int compareTo(Object ss){
        Student s = (Student)ss;
        return(this.name.compareTo(s.name));
    }

public void giveName(String s){
    name  = s;
}


public void addScoreQ1(double i){
    scoresq1.add(i);
}
public void addScoreQ2(double i){
    scoresq2.add(i);}
public void totalScoreQ1() {
    for (double s : scoresq1){
        q1 += s;
}
    q1 =  (q1) /2;

}
    public void totalScoreQ2(){
        for(double s: scoresq2) {
            q2 += s;
        }
        q2 = (q2 )/2;
    }
    public void total(){
        t = q1 + q2;
    }

}