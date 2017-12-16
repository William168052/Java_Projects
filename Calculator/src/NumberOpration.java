/**
 * Created by william on 2017/11/11.
 */
public class NumberOpration {
    public static Double plus(double a,double b){
        return a + b;
    }
    public static Double substract(double a,double b){
        return a - b;
    }
    public static Double multiply(double a,double b){
        return a * b;
    }
    public static Double divide(double a,double b){
        double c;
        try {
            c = a / b;
        }catch(ArithmeticException e){
            e.printStackTrace();
            c = 0.0;
        }
        return c;
    }
    public static int remainder(int a,int b){
        int c;
        try {
            c = a % b;
        }catch(ArithmeticException e){
            e.printStackTrace();
            c = 0;
        }
        return c;
    }
}
