package BusinessLogicClasses;


import DataAccessClasses.CustomerDAO;
import DataAccessClasses.OrderDAO;
import DataAccessClasses.ProductDAO;
import Model.Customer;
import Model.Order;
import Model.Product;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
public class Logic
{
    private ArrayList<Customer> customers ;
    private CustomerDAO customerDAO;
    private ArrayList<Product> products ;
    private ProductDAO productDAO;
    private ArrayList<Order> orders;
    private OrderDAO orderDAO;

    public Logic() {
        customerDAO = new CustomerDAO();
        this.customers = (ArrayList<Customer>) customerDAO.findAll();
        orderDAO = new OrderDAO();
        this.orders = (ArrayList<Order>) orderDAO.findAll();
        productDAO = new ProductDAO();
        this.products = (ArrayList<Product>) productDAO.findAll();

    }

    public void addCustomer(String firstName,String lastName, String email)throws SQLException
    {
        int id=customers.size()+1;
        Customer c =new Customer(id,lastName,firstName,email);
        customers.add(c);
        try
        {
            customerDAO.addObject(c);
        }catch(SQLException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }






}