package BusinessLogicClasses;


import DataAccessClasses.CustomerDAO;
import Model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBLL {
    private ArrayList<Customer> customers;
    private CustomerDAO customerDAO;
    private int aux;

    public CustomerBLL()
    {
        customerDAO=new CustomerDAO();
        this.customers=(ArrayList<Customer>)customerDAO.findAll();


    }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public void addCustomer(String firstName, String lastName, String email)throws SQLException
    {

        int id=customers.get(customers.size()-1).getId()+1;
        Customer c=new Customer(id,firstName,lastName,email);
        customers.add(c);
        try{
            customerDAO.addObject(c);
        }catch(Exception e)
        {
            e.printStackTrace();
        }


    }

   public void printRow(int id)
   {
       System.out.println(customerDAO.findById(id).toString());
   }


    public void deleteCustomer(int id)
    {
        try{

            customerDAO.deleteObject(id);

            int i=0;
            for(Customer c:customers)
            {
                if(c.getId()==id)
                {
                    aux=i;
                }
                i++;
            }

            customers.remove(aux);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updateCustomer(int id,Customer customer)
    {
        try
        {
            customerDAO.updateObject(id,customer);
            for(Customer c:customers)
            {
                if (c.getId()==id)
                {
                    c.setLastName(customer.getLastName());
                    c.setFirstName(customer.getFirstName());
                    c.setEmail(customer.getEmail());
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void test()
    {
        customerDAO.test();
    }
}
