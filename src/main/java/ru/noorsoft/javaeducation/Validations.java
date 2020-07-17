package ru.noorsoft.javaeducation;

import org.apache.commons.validator.routines.EmailValidator;

public class Validations {
    public static boolean numberValidator(String number){
        return number.matches("\\d{11}");
    }

    public static boolean emailValidator(String email){
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public static boolean fullNameValidator(String firstName, String lastName){
        return firstName.matches("[a-zA-Z]+")&&lastName.matches("[a-zA-Z]+");
    }
}
