package Model;

public class Order {
    private int id ;
    private int customer_id;
    private int product_id;
    private int q;

    public Order()
    {
        this.id=0;
        this.customer_id=0;
        this.product_id=0;
        this.q=0;
    }


    public Order(int id, int customerId, int  product_id, int quantity)
    {
        this.id=id;
        this.customer_id=customerId;
        this.product_id=product_id;

        this.q=quantity;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customer_id +
                ", productId=" + product_id +
                '}';
    }
}
