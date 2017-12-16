import java.util.Stack;

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

    public boolean comparePro(String inputOper, String sTopOperator) {
        //如果栈顶运算符为最括号，直接将输入运算符压栈
        if ("(".equals(sTopOperator)) {
            return true;
        }
        // 如果当前栈顶的操作运算符为“+”或“-”，并且输入运算符为“*”或“/”时，输入的运算符优先级大
        if (("*".equals(inputOper) || "/".equals(inputOper))
                && ("+".equals(sTopOperator) || "-".equals(sTopOperator))) {
            return true;
        }
        return false;
    }


    private Double calculateExpression(String exp){
        Stack<String> oprStack = new Stack<>();
        Stack<String>numStack = new Stack<>();
        Double result;
        for(int i = 0;i<exp.length();i++){
            Character c1 = exp.charAt(i);
            if(c1>='0'&&c1<='9'){
                String s = "";
                s = s + c1.toString();
                for(int j = i+1;;j++){
                    Character c2 = exp.charAt(j);
                    if(c1>='0'&&c1<='9'){
                        s = s + c2.toString();
                        i = j;
                    }else{
                        break;
                    }
                }
                numStack.push(s);
            }else{
                if(comparePro(c1.toString(),oprStack.peek())){
                    //入栈
                    oprStack.push(c1.toString());
                }
            }
        }
    }

}
