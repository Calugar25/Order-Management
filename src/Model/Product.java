package Model;

public class Product {
    private int id;
    private int quantity;
    private String name;
    private int price;



    public Product()
    {
        this.id=0;
        this.quantity=0;
        this.name="";
        this.price=0;

    }

    public Product(int id,int quantity,String name,int price)
    {
        this.id=id;
        this.quantity=quantity;
        this.name=name;
        this.price=price;

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", price=" + price +

                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
