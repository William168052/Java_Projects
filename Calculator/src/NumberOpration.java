import java.util.Stack;

/**
 * Created by william on 2017/11/11.
 */
public class NumberOpration {
    public static Double plus(Double a,Double b){
        return a + b;
    }
    public static Double substract(Double a,Double b){
        return a - b;
    }
    public static Double multiply(Double a,Double b){
        return a * b;
    }
    public static Double divide(Double a,Double b){
        double c;
        try {
            c = a / b;
        }catch(ArithmeticException e){
            e.printStackTrace();
            c = 0.0;
        }
        return c;
    }
    public static Double remainder(Double a,Double b){
        if(a.intValue() != a && b.intValue() != b){
            return 0.0;
        }
        Integer c;
        try {
            c = a.intValue() % b.intValue();
        }catch(ArithmeticException e){
            e.printStackTrace();
            c = 0;
        }
        return c.doubleValue();
    }

    private static boolean comparePro(String inputOper, String sTopOperator) {
        // 如果当前栈顶的操作运算符为“+”或“-”，并且输入运算符为“*”或“/”时，输入的运算符优先级大
        if ((inputOper.equals("×") || inputOper.equals("÷")||inputOper.equals("%"))
                && (sTopOperator.equals("+") || sTopOperator.equals("-"))) {
            return true;
        }
        return false;
    }

    private static Double calculate(Double val1,Double val2,String opr){
        switch (opr){
            case "+":
                return plus(val1,val2);
            case "-":
                return substract(val1,val2);
            case "×":
                return multiply(val1,val2);
            case "÷":
                return divide(val1,val2);
            case "%":
                return remainder(val1,val2);
            default:
                return 0.0;
        }
    }



    public static Double calculateExpression(String exp){
        Stack<String> oprStack = new Stack<>();
        Stack<String>numStack = new Stack<>();
        Double result;
        for(int i = 0;i<exp.length();i++){
            Character c1 = exp.charAt(i);

            if(c1>='0'&&c1<='9'){
                String s = "";
                s = s + c1.toString();
                for(int j = i+1;j<exp.length();j++){
                    Character c2 = exp.charAt(j);
                    if(c2>='0'&&c2<='9'){
                        s = s + c2.toString();
                        i = j;
                    }else{
                        break;
                    }
                }
                numStack.push(s);
            }else{
                if(oprStack.isEmpty() == true){
                    oprStack.push(c1.toString());
                }
                //输入运算符优先级高于栈顶运算符
                else if(comparePro(c1.toString(),oprStack.peek())){
                    //入栈
                    oprStack.push(c1.toString());
                }else if(!comparePro(c1.toString(),oprStack.peek())){
                    //取数据栈栈顶两个元素进行运算
                    Double val1 = Double.valueOf(numStack.pop());
                    Double val2 = Double.valueOf(numStack.pop());
                    Double res = calculate(val2,val1,c1.toString());
                    numStack.push(res.toString());

                }else{
                    break;
                }
            }
        }
        while(oprStack.isEmpty() != true){
            Double val1 = Double.valueOf(numStack.pop());
            Double val2 = Double.valueOf(numStack.pop());
            String opr = oprStack.pop();
            numStack.push(calculate(val2,val1,opr).toString());
        }
        //oprStack栈为空则最后结果就是numStack的栈顶元素
        result = Double.valueOf(numStack.pop());
        return result;
    }

}
