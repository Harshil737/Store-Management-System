public interface ProductActionClickListner {
    void onProductAddClick(Product p);

    Product onProductFind(int id);

    void onProductUpdate(Product p);

    boolean checkProductIdAvailable(int id);
}
