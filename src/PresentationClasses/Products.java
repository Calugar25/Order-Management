package PresentationClasses;

import BusinessLogicClasses.ProductBLL;
import Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Products {
    private JFrame frameProducts;
    private JTable tableProducts;

    private JTextField quantitytext;
    private JTextField nametext;
    private JTextField pricetext;

    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    private ProductBLL productBLL;
    private TableModel tableModel;

    private int quantity;
    private String name;
    private int price;


    private int selectedId;

    public Products()
    {
        tableModel=new TableModel();

        productBLL=new ProductBLL();
        frameProducts=new JFrame("PRODUCTS");
        tableProducts=new JTable(tableModel.CreateModel(productBLL.getProducts()));

        quantitytext=new JTextField();
        nametext=new JTextField();
        pricetext=new JTextField();

        addButton=new JButton("Add Product");
        deleteButton=new JButton("Delete");
        updateButton=new JButton("Update");

        quantitytext.setBounds(20,200,100,25);
        nametext.setBounds(20,250,100,25);
        pricetext.setBounds(20,280,100,25);

        addButton.setBounds(150,220,100,25);
        deleteButton.setBounds(150, 265,100,25);
        updateButton.setBounds(150,310,100,25);

      addButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              quantity=Integer.parseInt(quantitytext.getText());
              name=nametext.getText();
              price=Integer.parseInt(pricetext.getText());
              try{
                  productBLL.addProduct(quantity,name,price);
                  tableProducts.setModel(tableModel.CreateModel(productBLL.getProducts()));
              }catch (Exception z)
              {
                  z.printStackTrace();
              }
          }
      });



      deleteButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              try{
                  selectedId=Integer.parseInt(tableProducts.getModel().getValueAt(tableProducts.getSelectedRow(),0).toString());
                  productBLL.deleteProduct(selectedId);
                  tableProducts.setModel(tableModel.CreateModel(productBLL.getProducts()));


              }catch (Exception z)
              {
               z.printStackTrace();
              }

          }
      });


      updateButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              selectedId=Integer.parseInt(tableProducts.getModel().getValueAt(tableProducts.getSelectedRow(),0).toString());
              quantity=Integer.parseInt(quantitytext.getText());
              name=nametext.getText();
              price=Integer.parseInt(pricetext.getText());
              Product p=new Product(selectedId,quantity,name,price);

              productBLL.updateProduct(selectedId,p);
              tableProducts.setModel(tableModel.CreateModel(productBLL.getProducts()));
          }
      });

        JScrollPane pane=new JScrollPane(tableProducts);
        pane.setBounds(0,0,880,200);

        frameProducts.setLayout(null);

        frameProducts.add(pane);

        frameProducts.add(quantitytext);
        frameProducts.add(nametext);
        frameProducts.add(pricetext);

        frameProducts.add(addButton);
        frameProducts.add(deleteButton);
        frameProducts.add(updateButton);

        frameProducts.setSize(900,400);
        frameProducts.setLocationRelativeTo(null);
        frameProducts.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameProducts.setVisible(true);




    }
}
