package Model;

public class Customer {
    private int id;
    private String lastName;
    private String firstName;
    private String email;



    public Customer()
    {
        id=0;
        lastName="";
        firstName="";
        email="";


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer(int id, String lastName, String firstName, String email){
        this.id=id;
        this.lastName=lastName;
        this.firstName=firstName;
        this.email=email;
        //this.situation=situation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
