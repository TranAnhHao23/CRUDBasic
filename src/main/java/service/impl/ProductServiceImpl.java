package service.impl;

import model.Product;
import service.IProductService;

import java.util.ArrayList;

public class ProductServiceImpl implements IProductService {
    private static final ArrayList<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product(1, "A", 10, "no"));
        products.add(new Product(2, "B", 12, "no"));
        products.add(new Product(3, "C", 11, "yes"));
        products.add(new Product(4, "D", 9, "yes"));
    }

    @Override
    public ArrayList<Product> getAll() {
        return products;
    }

    @Override
    public void createProduct(Product product) {
        products.add(product);
    }

    @Override
    public void updateProduct(Product product, int id) {
        products.set(id, product);
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
    }

    @Override
    public Product getProduct(int id) {
        for (Product product: products) {
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }
}
