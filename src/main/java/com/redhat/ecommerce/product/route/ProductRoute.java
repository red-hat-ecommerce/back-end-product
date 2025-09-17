package com.redhat.ecommerce.product.route;

import com.redhat.ecommerce.product.service.ProductService;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 * <pre>
 *  com.redhat.ecommerce.product.route.ProductRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Sep 2025 14:00
 */
@ApplicationScoped
public class ProductRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/api/product")
                .get("/")
                    .produces("application/json")
                    .to("direct:get-products")
                .get("/{id}")
                    .produces("application/json")
                    .to("direct:get-product-by-id");

        from("direct:get-products")
                .routeId("get-products-api")
                    .log("calling get-products")
                    .bean(ProductService.class, "getProducts")
                        .marshal().json(JsonLibrary.Jackson);

        from("direct:get-product-by-id")
                .routeId("get-product-by-id-api")
                .log("calling get-product-by-id")
                .bean(ProductService.class, "getProduct(${header.id})")
                .marshal().json(JsonLibrary.Jackson);

    }


}
