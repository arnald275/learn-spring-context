package org.example.Chapter_1_ApplicationContext.instantiation;

public class StaticFactoryMethod {
    @Override
    public String toString() {
        return "instantiated by StaticFactoryMethod  instantiation";
    }
    private StaticFactoryMethod(){

    }
    private static final StaticFactoryMethod staticFactoryMethodObject = new StaticFactoryMethod();

     public static StaticFactoryMethod getInstance() {
        return staticFactoryMethodObject;
    }
}
