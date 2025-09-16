package com.redhat.ecommerce.product.model;

/**
 * <pre>
 *  com.redhat.ecommerce.product.model.Product
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Sep 2025 14:04
 */
public class Product {
    private Integer productId;
    private String productName;
    private Double productPrice;
    private String productShortDescription;
    private String productDescription;

    public Product() {
    }

    public Product(Integer productId, String productName, Double productPrice, String productShortDescription, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productShortDescription = productShortDescription;
        this.productDescription = productDescription;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductShortDescription() {
        return productShortDescription;
    }

    public void setProductShortDescription(String productShortDescription) {
        this.productShortDescription = productShortDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
