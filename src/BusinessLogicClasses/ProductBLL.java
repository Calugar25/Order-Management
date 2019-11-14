package BusinessLogicClasses;

import DataAccessClasses.ProductDAO;
import Model.Product;

import java.util.ArrayList;

public class ProductBLL {
    private ArrayList<Product> products;
    private ProductDAO productDAO;

    int aux;

    public ProductBLL()
    {
        productDAO=new ProductDAO();
        this.products=(ArrayList<Product>)productDAO.findAll();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(int quantity, String name, int price)
    {
        int id=products.get(products.size()-1).getId()+1;
        Product p=new Product(id,quantity,name,price);
        products.add(p);

        try{
            productDAO.addObject(p);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id)
    {
        try{
            productDAO.deleteObject(id);

            int i=0;
            for(Product c:products)
            {
                if(c.getId()==id)
                {
                    aux=i;
                }
                i++;
            }

            products.remove(aux);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updateProduct(int id,Product product)
    {
        try{
            productDAO.updateObject(id,product);
            for(Product p:products)
            {
                if (id==p.getId())
                {
                    p.setQuantity(product.getQuantity());
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void test()
    {
        for(Product p:products)
        {
            System.out.println(p.toString());
        }
    }

    public Product findById(int id)
    {
        return productDAO.findById(id);
    }

}
