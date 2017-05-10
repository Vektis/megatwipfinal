package com.company;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner data = new Scanner(new File("rawDataU6.txt"));
        Integer c = -1;
        String fbewesbreobr = data.nextLine();
        while(data.hasNextLine()){
            String slice = data.nextLine();
            if(slice.length() == 0){
                continue;
            }
            else{
                analyzeSlice(slice);
            }
        }
        output();
    }
    public static void output(){
        System.out.println("Number\tName\tAvg\t\t Q1\t Q2");
        for (int i = 0; i < Student.all.size(); i++) {
            Student temp = Student.all.get(i);
            temp.totalScoreQ1();
            temp.totalScoreQ2();
            temp.total();
            System.out.println(temp.snum + "\t" + temp.name + "\t\t" + temp.t + "/19  " + temp.q1 + "  " + temp.q2);
        }
    }
    public static void analyzeSlice(String s) throws IOException {
        //check 4 new student
        String snum = s.substring(0, 2);
        String dnum = s.substring(0, 1);
        int num = 0;
        if (snum.substring(1).matches("\\d")) {
            num = Integer.parseInt(snum);
        } else if (dnum.matches("\\d")) {
            num = Integer.parseInt(dnum);
        } else {
            num = -1;
        }
        if (num != -1) {
            boolean is = true;
            for (int o : Student.snums) {
                if (o == num)
                    is = false;
            }
            if (is) {
                Student toAdd = new Student(num);
                Student.all.add(toAdd);
                Scanner names=  new Scanner(new File("names.txt"));
                int c = 0;
                ArrayList<String> namae = new ArrayList<>();
                while(names.hasNext()){

                    namae.add(names.next());
                }
                for (int i = 1; i < namae.size(); i++) {
                    String temp = namae.get(i);
                    String d1, d2;
                    d1 = d2 = "";
                    if(temp.length() == 2){
                       d1 = temp.substring(0,1);
                        d2 = temp.substring(1,2);
                    }
                    String b4 = namae.get(i-1);
                    if(temp.matches("\\d") || d1.matches("\\d") && d2.matches("\\d")){
                    int ch = 0;
                        if(d1.matches("\\d") && d2.matches("\\d")) {
                            int cc = Integer.parseInt(d2);
                            int d11 = Integer.parseInt(d1);
                            ch = (10 * d11) + cc;
                        }
                        if(temp.matches("\\d")){
                            ch = Integer.parseInt(temp);
                        }
                        System.out.println(ch + ":" + temp);
                        if(ch == num){
                            toAdd.giveName(b4);

                            break;
                        }
                    }
                }
            }
            whatKind(s, num);
        }
    }
        public static void whatKind(String s, int i){
            Student stu = null;
            for (int j = 0; j < Student.all.size(); j++) {
                if(Student.all.get(j).snum == i){
                    stu = Student.all.get(j);
                }
            }
            boolean plus,deci,tab,alr;
            plus = deci = tab = alr = false;
            for (int j = 1; j < s.length(); j++) {
                String p = s.substring(j,j+1);
                double add = -1;
                if(p.equals(" "))
                    continue;
                else if(p.equals("\t")){
                    tab = true;
                    deci = false;
                    plus = false;
                }
                else if(p.equals("+")){
                    tab = false;
                    plus = true;
                }
                else if(p.equals(".")){
                    deci = true;
                    tab = false;
                }
                else if(p.matches("\\d")){
                    add = Integer.parseInt(p);
                    if(deci){
                        add /=10;
                    }
                }
                else{
                    plus = deci = tab = false;
                }


                if(add >= 0 ) {
                    if (!alr) {
                        if (plus)
                            stu.addScoreQ1(add);
                        else if(tab){
                            stu.addScoreQ1(-.25 * add);
                            alr = true;
                    }
                    }
                    else{
                        if(plus)
                            stu.addScoreQ2(add);
                        else if(tab)
                            stu.addScoreQ2(-.25 * add);
                    }
                }
            }
    }
}