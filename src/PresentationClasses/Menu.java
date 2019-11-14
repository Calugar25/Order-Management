package PresentationClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu  extends JFrame{

    private JFrame frame;
    private JButton customers ;
    private JButton products;
    private JButton orders;
    private JPanel panel;

    public Menu()
    {
        frame=new JFrame("Order Manegement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,700);
        //frame.setLayout(new FlowLayout());
        panel=new JPanel(new GridLayout(1,3));
        customers=new JButton("CLIENTS");
        customers.setPreferredSize(new Dimension(150,150));
        panel.add(customers);
       // clients.setBounds(10,10,200,200);
        products=new JButton("PRODUCTS");
        products.setPreferredSize(new Dimension(150,150));
        panel.add(products);
        orders=new JButton("ORDERS");
        orders.setPreferredSize(new Dimension(150,150));
        panel.add(orders);

        customers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Customers();
            }
        });

        products.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Products();
            }
        });

        orders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Orders();
            }
        });

        frame.setContentPane(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);




    }
}
