package bank.managment.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Mini extends JFrame implements ActionListener
{
    String pin;
    JButton button;
    Mini(String pin)
    {
        this.pin = pin;
        getContentPane().setBackground(new Color(255,204,204));
        setSize(400,600);
        setLocation(20,20);
        setLayout(null);

        JLabel label1 = new JLabel();
        label1.setBounds(20,100,350,300);
        add(label1);

        JLabel label2 = new JLabel("MAHAK GUPTA");
        label2.setFont(new Font("System",Font.BOLD,15));
        label2.setBounds(120,20,200,20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20,60,300,20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20,450,300,20);
        add(label4);


        try
        {
            DBConnection c = new DBConnection();
            ResultSet resultSet = c.statement.executeQuery("select * from login where pin = '"+pin+"'");
            while(resultSet.next())
            {
                label3.setText("Card Number: "+resultSet.getString("card_number").substring(0,4) + "XXXXXXXX"+ resultSet.getString("card_number").substring(12));

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            int balance =0;
            DBConnection c = new DBConnection();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");

            StringBuilder statement = new StringBuilder("<html>");

            while(resultSet.next())
            {
                statement.append(resultSet.getString("TRANSACTION_DATE"))
                        .append("&nbsp;&nbsp;&nbsp;")
                        .append(resultSet.getString("TYPE"))
                        .append("&nbsp;&nbsp;&nbsp;")
                        .append(resultSet.getString("AMOUNT"))
                        .append("<br><br>");

                if(resultSet.getString("TYPE").equals("Deposit"))
                {
                    balance += Integer.parseInt(resultSet.getString("AMOUNT"));
                }
                else
                {
                    balance -= Integer.parseInt(resultSet.getString("AMOUNT"));
                }
            }

            statement.append("</html>");
            label1.setText(statement.toString());
            label4.setText("Your Total Balance is Rs "+balance);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        button = new JButton("Exit");
        button.setBounds(20,500,100,25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.setVisible(false);


    }

    public static void main(String[] args)
    {
        new Mini("");

    }
}
