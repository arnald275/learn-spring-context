package org.example.Chapter_1_ApplicationContext.instantiation;

public class InstanceFactoryMethod {


    public Person instanceMethod(){
        System.out.println("instance method called");
        //can return any bean defined in the  meta-data beans.xml
        return new Person();
    }


}
