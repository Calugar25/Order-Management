package BusinessLogicClasses;

import DataAccessClasses.OrderDAO;
import Model.Order;

import java.io.PrintWriter;
import java.util.ArrayList;

public class OrderBLL {
    private ArrayList<Order> orders;
    private OrderDAO orderDAO;

    public OrderBLL()
    {


        orderDAO=new OrderDAO();
        this.orders=(ArrayList<Order>)orderDAO.findAll();

    }

    public void generateRecipt(int id, int customer_id,int product_id,int quantity)
    {
        try{
            PrintWriter writer = new PrintWriter("Recipt"+id+".txt", "UTF-8");
            writer.println("This is the order with id "+id);
            writer.println("This order contains  "+quantity+"products that have the id "+product_id);
            writer.println("The customer that bought the product has the id "+customer_id);
            writer.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addOrder(int customer_id,int  product_id ,int quantity)
    {
        int id=orders.get(orders.size()-1).getId()+1;
        Order o =new Order(id,customer_id,product_id,quantity);
        orders.add(o);
        try{

            orderDAO.addObject(o);
           generateRecipt(id,customer_id,product_id,quantity);


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id)
    {
        try{
            orderDAO.deleteObject(id);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void test()
    {
        for(Order o:orders)
        {
            System.out.println(o.toString());
        }
    }


}
