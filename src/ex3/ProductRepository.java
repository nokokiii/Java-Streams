package ex3;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    protected void addProduct(Product product) {
        products.add(product);
    }

    protected List<Product> sortProductsByName() {
        return products.stream().sorted(comparing(Product::name)).toList();
    }

    protected List<Product> sortProductsByPriceDescending() {
        return products.stream().sorted(comparing(Product::price).reversed()).toList();
    }

    protected boolean areAllProductsExpensive(double price) {
        return products.stream().allMatch(product -> product.price() > price);
    }

    protected boolean isAnyProductCheap(double price) {
        return products.stream().anyMatch(product -> product.price() < price);
    }

    protected List<Product> findProductsByNameContaining(String name) {

        return products.stream().filter(product -> product.name().toLowerCase().contains(name)).toList();
    }
}
