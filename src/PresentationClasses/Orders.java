package PresentationClasses;

import BusinessLogicClasses.CustomerBLL;
import BusinessLogicClasses.OrderBLL;
import BusinessLogicClasses.ProductBLL;
import Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Orders {
    private JFrame frameOrders;
    private JTable tableCustomers;
    private JTable tableProducts;

    private JTextField customerIdtext;
    private JTextField productIdtext;
    private JTextField quantitytext;

    private JButton addButton;


    private CustomerBLL customerBLL;
    private ProductBLL productBLL;
    private TableModel tableModel;
    private OrderBLL orderBLL;



    private int customerId;
    private int productId;
    private int quantity;

    private int selectedIdCustomer;
    private int selectedIdProduct;

    private int productQuantity;


    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }


    public Orders()
    {
        tableModel=new TableModel();
        customerBLL=new CustomerBLL();
        productBLL=new ProductBLL();
        orderBLL=new OrderBLL();

        frameOrders=new JFrame("ORDERS");

        tableCustomers=new JTable(tableModel.CreateModel(customerBLL.getCustomers()));
        tableProducts=new JTable(tableModel.CreateModel(productBLL.getProducts()));

        customerIdtext=new JTextField();
        productIdtext=new JTextField();
        quantitytext=new JTextField();

        addButton=new JButton("Add Order");


        quantitytext.setBounds(20,220,100,25);

        addButton.setBounds(150,220,100,25);



        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quantity=Integer.parseInt(quantitytext.getText());
                productQuantity=Integer.parseInt(tableProducts.getModel().getValueAt(tableProducts.getSelectedRow(),1).toString());
                customerId=Integer.parseInt(tableCustomers.getModel().getValueAt(tableCustomers.getSelectedRow(),0).toString());
                productId=Integer.parseInt(tableProducts.getModel().getValueAt(tableProducts.getSelectedRow(),0).toString());

                if(productQuantity>=quantity)
                {

                    orderBLL.addOrder(customerId,productId,quantity);
                    String productName=tableProducts.getModel().getValueAt(tableProducts.getSelectedRow(),2).toString();
                    int price=Integer.parseInt(tableProducts.getModel().getValueAt(tableProducts.getSelectedRow(),3).toString());
                    int newQuantity=productQuantity-quantity;
                    Product p=new Product(productId,newQuantity,productName,price);

                    productBLL.updateProduct(productId,p);
                    tableProducts.setModel(tableModel.CreateModel(productBLL.getProducts()));

                }else
                {
                    infoBox("Not enought quantity to place order","ERROR");
                }

            }
        });

        JScrollPane paneCustomers=new JScrollPane(tableCustomers);

        paneCustomers.setBounds(0,0,600,200);

        JScrollPane paneProducts=new JScrollPane(tableProducts);

        paneProducts.setBounds(700,0,400,200);

        frameOrders.setLayout(null);

        frameOrders.add(paneCustomers);
        frameOrders.add(paneProducts);
        frameOrders.add(quantitytext);

        frameOrders.add(addButton);


        frameOrders.setSize(1200,500);
        frameOrders.setLocationRelativeTo(null);
        frameOrders.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOrders.setVisible(true);


    }
}
