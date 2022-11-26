package su.yesha.pitest;

import java.util.Objects;

public class Calculator {

    public double sum(Double arg1,Double arg2){
        Objects.requireNonNull(arg1);
        Objects.requireNonNull(arg2);
        return arg1 + arg2;
    }

    public double mul(Double arg1, Double arg2){
        Objects.requireNonNull(arg1);
        Objects.requireNonNull(arg2);
        return arg1 * arg2;
    }

    public double divide(Double arg1,Double arg2){
        Objects.requireNonNull(arg1);
        Objects.requireNonNull(arg2);
        return arg1 / arg2;
    }

    public double sub(Double arg1, Double arg2){
        Objects.requireNonNull(arg1);
        Objects.requireNonNull(arg2);
        return arg1 - arg2;
    }

}

