package hu.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;

public class Reflect {
    public static void main(String[] args) {

    }
    public static void showDeclaredMethods(Class<?> c){
        System.out.println(c.toString()+"--------------");
        for (Method declaredMethod : c.getDeclaredMethods()) {
            System.out.println(declaredMethod.toGenericString());
        }
    }
    public static void showDeclaredFields(Class<?> c){
        System.out.println(c.toString()+"--------------");
        for (Field declaredField : c.getDeclaredFields()) {
            System.out.println(declaredField.toGenericString());
        }
    }
}
