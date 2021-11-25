package data_generators;

import com.github.javafaker.Faker;

public class DataCreation {

    private DataCreation() {}

    public static String firstName() {
        return new Faker().name().firstName();
    }

    public static String lastName() {
        return new Faker().name().lastName();
    }

    public static String postalCode () {
        return new Faker().number().digits(5);
    }

}
