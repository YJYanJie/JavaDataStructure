package com.yj.test;

/**
 * @author YJ
 * @create 2022-09-29 17:26
 */
public class Test {

    public static void main(String[] args) {


        Pupple pupple = new Pupple();
        pupple.name = "AA";
        pupple.id = 111;
        pupple.age = 11;
        int i ;
        pupple.show();

        System.out.println();

        Pupple p1 = new Pupple();
        p1.show();

    }
}


class Person{
    String name;
    int id;
    Temp temp;
}

class Pupple extends Person{
    int age;
    int id;
    Temp temp = new Temp();

    public void show(){
        System.out.println("name = " + name + ", id = " + id + ", age = " + age);
        System.out.println("super.name = " + super.name + ", super.id = " + super.id + ", age = " + age);
//        System.out.println(super.temp == temp);
    }
}

class Temp{
    int a;
}