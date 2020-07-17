package ru.noorsoft.javaeducation;

public class StringUpper {
    public static String firstNameUpperCase(String firstName){
        String s1 = firstName.substring(0, 1).toUpperCase();
        return s1 + firstName.substring(1);
    }
    public static String lastNameUpperCase(String lastName){
        String s2 = lastName.substring(0, 1).toUpperCase();
        return s2 + lastName.substring(1);
    }
}
