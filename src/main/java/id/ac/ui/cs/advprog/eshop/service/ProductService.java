package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public boolean deleteProductById(String id);
    public Product getProductId(String id);
    public Product editProduct(Product product);

}