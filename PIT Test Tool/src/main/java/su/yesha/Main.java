package su.yesha;

import su.yesha.pitest.Calculator;


public class Main{
    public static void main(String args[]){
        Calculator calc = new Calculator();
        System.out.println("2 * 3 = " + calc.mul(2D,3D));
    }
}
