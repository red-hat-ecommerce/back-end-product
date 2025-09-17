package com.redhat.ecommerce.product.service;

import com.redhat.ecommerce.product.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * <pre>
 *  com.redhat.ecommerce.product.service.ProductService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Sep 2025 14:05
 */
@RegisterForReflection
@ApplicationScoped
public class ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    public List<Product> getProducts() {
        LOG.info("get list of Products");
        return getProductList();
    }

    public Product getProduct(Integer id) {
        LOG.info("get Product id {}", id);
        return getProductList().stream().filter(product -> product.getProductId().equals(id)).findFirst().orElse(null);
    }

    private String generateProductDescription(Integer productId) {
        return " <p>Product " + productId + " </p> " +
                "<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat. </p>" +
                "<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </p>" +
                "<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. </p>";
    }

    private List<Product> getProductList() {
        return List.of(
                new Product(4, "Product 4", 100_000.0, "Product Short Desc 4", generateProductDescription(4)),
                new Product(5, "Product 5", 50_000.0, "Product Short Desc 5", generateProductDescription(5)),
                new Product(6, "Product 6", 202_500.0, "Product Short Desc 6", generateProductDescription(6))
        );
    }

}
