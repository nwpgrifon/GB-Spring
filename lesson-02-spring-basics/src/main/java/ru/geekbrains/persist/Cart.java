package ru.geekbrains.persist;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component

public class Cart {

    private List<Product> productList = new ArrayList<>();
    private ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct (long id) {
        productList.add(productRepository.findById(id));
    }

    public void deleteProduct (long id) {
        productList.remove(productRepository.findById(id));
    }

    public List<Product> getProductList () {
        return new ArrayList<>(productList);
    }

}
