package PresentationClasses;

import BusinessLogicClasses.CustomerBLL;
import Model.Customer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Customers {

    private JFrame frameCustomers;
    private JTable tableCustomers;

    private JTextField lastNametext;
    private JTextField firstNametext;
    private JTextField emailtext;

    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;


    private CustomerBLL customerBLL;
    private TableModel tableModel;

    private ArrayList<Customer> customers;

    private String lastName;
    private String firstName;
    private String email;

    private int selectedId;




    public Customers()
    {
        tableModel=new TableModel();
        customerBLL=new CustomerBLL();
        frameCustomers=new JFrame("CUTOMERS");

        customers=new ArrayList<Customer>();
        customers=customerBLL.getCustomers();
        tableCustomers=new JTable(tableModel.CreateModel(customers));
        //tableCustomers.setEnabled(false);




        lastNametext=new JTextField();
        firstNametext=new JTextField();
        emailtext=new JTextField();

        addButton=new JButton("Add Client");
        deleteButton=new JButton("Delete");
        updateButton=new JButton("Update");

        lastNametext.setBounds(20,200,100,25);
        firstNametext.setBounds(20,250,100,25);
        emailtext.setBounds(20,280,100,25);

        addButton.setBounds(150,220,100,25);
        deleteButton.setBounds(150, 265,100,25);
        updateButton.setBounds(150,310,100,25);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lastName=lastNametext.getText();
                firstName=firstNametext.getText();
                email=emailtext.getText();
               try{
                   customerBLL.addCustomer(lastName,firstName,email);
                   tableCustomers.setModel(tableModel.CreateModel(customerBLL.getCustomers()));
               }catch (Exception z)
               {
                   z.printStackTrace();
               }
            }
        });




        tableCustomers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
               //deleteId=Integer.parseInt(tableCustomers.getValueAt(tableCustomers.getSelectedRow(), 0).toString());
                //System.out.println(deleteId);

            }
        });



        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    selectedId=Integer.parseInt(tableCustomers.getModel().getValueAt(tableCustomers.getSelectedRow(),0).toString());
                    //System.out.println(selectedId);
                    customerBLL.deleteCustomer(selectedId);
                    tableCustomers.setModel(tableModel.CreateModel(customerBLL.getCustomers()));

                }catch (Exception b)
                {
                    b.printStackTrace();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    selectedId=Integer.parseInt(tableCustomers.getModel().getValueAt(tableCustomers.getSelectedRow(),0).toString());
                    String firstName=firstNametext.getText();
                    String lastName=lastNametext.getText();
                    String email=emailtext.getText();
                    Customer c=new Customer( selectedId,firstName,lastName,email);

                    System.out.println(selectedId+firstName+lastName+email);
                    customerBLL.updateCustomer(selectedId,c);
                    tableCustomers.setModel(tableModel.CreateModel(customerBLL.getCustomers()));
                }catch (Exception m)
                {
                    m.printStackTrace();
                }
            }
        });


        JScrollPane pane=new JScrollPane(tableCustomers);
        pane.setBounds(0,0,880,200);

        frameCustomers.setLayout(null);

        frameCustomers.add(pane);

        frameCustomers.add(lastNametext);
        frameCustomers.add(firstNametext);
        frameCustomers.add(emailtext);

        frameCustomers.add(addButton);
        frameCustomers.add(deleteButton);
        frameCustomers.add(updateButton);

        frameCustomers.setSize(900,400);
        frameCustomers.setLocationRelativeTo(null);
        frameCustomers.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameCustomers.setVisible(true);

    }

}
