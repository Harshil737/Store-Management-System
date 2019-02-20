public class Customer {
    private int customerId;
    private String name, phone, email, postalCode;

    public Customer() {
        customerId = 0;
        name = "";
        phone = "";
        email = "";
        postalCode = "";
    }

    public Customer(int customerId, String name, String phone, String email, String postalCode) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.postalCode = postalCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "\nCustomer Details\n" +
                "\nCustomer ID = " + customerId +
                "\n Name = " + name +
                "\n Phone = " + phone +
                "\n Email = " + email +
                "\n Postal Code = " + postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId;
    }
}
