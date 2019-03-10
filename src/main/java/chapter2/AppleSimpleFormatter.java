package chapter2;

public class AppleSimpleFormatter implements AppleFormatter {

    public String accept (Apple a){
        return "An apple of " + a.getWeight() + "g";
    }
}
