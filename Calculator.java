import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator implements ActionListener {
    JFrame frm;
    JTextField textField;
    JPanel btnPanel;

    JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, clearBtn, plusBtn, minusBtn, mulBtn, divBtn,
            equalBtn;

    Calculator() {
        frm = new JFrame("Calculator");
        frm.setLocation(450, 250);
        frm.setLayout(new BorderLayout());
        frm.setSize(400, 400);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(400, 70));
        textField.setEditable(false);
        textField.setFont(new Font("Times new roman", Font.BOLD, 40));
        textField.setHorizontalAlignment(JTextField.RIGHT);

        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(4, 4));

        Font myFont = new Font("Times new roman",Font.PLAIN,35);

        // Initialize all buttons and add listeners
        btn1 = new JButton("1");
        btn1.setFont(myFont);
        btn1.addActionListener(this);
        btn2 = new JButton("2");
        btn2.setFont(myFont);
        btn2.addActionListener(this);
        btn3 = new JButton("3");
        btn3.setFont(myFont);
        btn3.addActionListener(this);
        btn4 = new JButton("4");
        btn4.setFont(myFont);
        btn4.addActionListener(this);
        btn5 = new JButton("5");
        btn5.setFont(myFont);
        btn5.addActionListener(this);
        btn6 = new JButton("6");
        btn6.setFont(myFont);
        btn6.addActionListener(this);
        btn7 = new JButton("7");
        btn7.setFont(myFont);
        btn7.addActionListener(this);
        btn8 = new JButton("8");
        btn8.setFont(myFont);
        btn8.addActionListener(this);
        btn9 = new JButton("9");
        btn9.setFont(myFont);
        btn9.addActionListener(this);
        btn0 = new JButton("0");
        btn0.setFont(myFont);
        btn0.addActionListener(this);

        clearBtn = new JButton("AC");
        clearBtn.setFont(myFont);
        clearBtn.addActionListener(this);
        plusBtn = new JButton("+");
        plusBtn.setFont(myFont);
        plusBtn.addActionListener(this);
        minusBtn = new JButton("-");
        minusBtn.setFont(myFont);
        minusBtn.addActionListener(this);
        mulBtn = new JButton("*");
        mulBtn.setFont(myFont);
        mulBtn.addActionListener(this);
        divBtn = new JButton("/");
        divBtn.setFont(myFont);
        divBtn.addActionListener(this);
        equalBtn = new JButton("=");
        equalBtn.setFont(myFont);
        equalBtn.addActionListener(this);

        // Add components to frame
        frm.add(textField, BorderLayout.NORTH);
        frm.add(btnPanel, BorderLayout.CENTER);

        // Add buttons to panel
        btnPanel.add(btn1);
        btnPanel.add(btn2);
        btnPanel.add(btn3);
        btnPanel.add(plusBtn);
        btnPanel.add(btn4);
        btnPanel.add(btn5);
        btnPanel.add(btn6);
        btnPanel.add(minusBtn);
        btnPanel.add(btn7);
        btnPanel.add(btn8);
        btnPanel.add(btn9);
        btnPanel.add(mulBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(btn0);
        btnPanel.add(equalBtn);
        btnPanel.add(divBtn);

        frm.setVisible(true);
    }

    String operator = "";// operator
    int num1 = 0;// 0 indicates num1 is ready to be set
    int num2 = -1;// -1 indicates num2 is not ready to be set, first num1 will be set after that num2 will be set

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();// get the source of the event

        if (src == clearBtn) {
            textField.setText("");// clear the text field
            num1 = 0;// reset num1
            num2 = -1;// reset num2
            operator = "";// reset operator
        }
        else if (src == equalBtn) {
            try {
                num2 = Integer.parseInt(textField.getText());
                int result = 0;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num2 != 0 ? num1 / num2 : 0;
                        break;
                }

                textField.setText("" + result);// display the result
                num1 = result;// set num1 to the result
                num2 = -1;// reset num2
                operator = "";
            } catch (Exception ex) {
                textField.setText("Error");
            }
        } else if (src == plusBtn || src == minusBtn || src == mulBtn || src == divBtn) {
            try {
                num1 = Integer.parseInt(textField.getText());// num1 successfully set
                operator = e.getActionCommand();// get the operator
                textField.setText("" + operator);// display the operator
                num2 = 0;// num2 is ready to be set
            } catch (Exception ex) {
                textField.setText("Error");
            }
        } else {
            // Number button pressed

            // if num2 is -1, it means num1 is being set by user
            if (num2 == -1) {
                num1 = (num1 * 10) + Integer.parseInt(e.getActionCommand());
                textField.setText(num1 + "");
            }
            // if num2 is not -1,num1 is setted successfully and num2 is being set by user
            else {
                num2 = (num2 * 10) + Integer.parseInt(e.getActionCommand());
                textField.setText(num2 + "");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
