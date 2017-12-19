import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {
    private JTextField text = null;
    private JButton ACbutton = null;
    private static final int PRE_WIDTH = 200;
    private static final int PRE_HEIGHT = 300;
    private String[] nOp = {"AC","←","%","÷","7","8","9","×","4","5","6","-","1","2","3","+","0","00",".","="};
    private CalculatorFrame(){
        this.setTitle("计算器");
        this.setSize(PRE_WIDTH,PRE_HEIGHT);
        //设置窗口大小不可变
        this.setResizable(true);
        //设置单击关闭按钮结束程序运行
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置窗口居中
        this.setLocationRelativeTo(null);
        //设置布局
        this.setLayout(new BorderLayout());
        //设置内部组件
        //1.设置北部面板
        JPanel nPanel = new JPanel();
        nPanel.setLayout(new FlowLayout());
        nPanel.add(getTextField());
        //2.设置南部面板
        JPanel sPanel = new JPanel();
        sPanel.setSize(PRE_WIDTH,PRE_HEIGHT/3);
        sPanel.setLayout(new GridLayout(5,4));
        //2.1循环遍历数组返回按钮
        for(String s : nOp){
            JButton NumBtn = new JButton(s);
            NumBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        //当点击"AC或C"时还原
                        if(NumBtn.getText().equals("C") || NumBtn.getText().equals("AC")){
                            //当输入了式子
                            if(!(text.getText().equals("0"))){
                                text.setText("0");
                            }
                        }else if(NumBtn.getText().equals("=")) {
                            Double val;
                            String exp = text.getText();
                            //通过表达式运算结果
                            val = NumberOpration.calculateExpression(exp);
                            text.setText(val.toString());
                        }else if(NumBtn.getText().equals("←")){
                            //点按退格按键退格
                            String str = text.getText();
                            str = str.substring(0,str.length()-1);
                            text.setText(str);
                        }else{
                            String str = text.getText();
                            if(text.getText().equals("0")){
                                str = "";
                            }
                            str = str + NumBtn.getText();
                            text.setText(str);
                        }
                }
            });
            //找到AC按钮
            if(s.equals("AC")){
                ACbutton = NumBtn;
                sPanel.add(ACbutton);
            }else{
                sPanel.add(NumBtn);
            }
        }
        this.add(nPanel,BorderLayout.NORTH);
        this.add(sPanel,BorderLayout.SOUTH);
    }
    private JTextField getTextField(){
        text = new JTextField("0",15);
        //给文本框添加监听
        text.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(text.getText().equals("0")){
                    ACbutton.setText("AC");
                }else{
                    ACbutton.setText("C");
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        return text;
    }
    public static void main(String args[]){
        new CalculatorFrame().setVisible(true);
    }
}
