package com.redhat.ecommerce.product;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

/**
 * <pre>
 *  com.redhat.ecommerce.product.ApiTest
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Sep 2025 15:57
 */
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTest {

    @Test
    @Order(1)
    @DisplayName("This test is to check if api get all products is working as expected")
    public void testGetAllProducts() {
        given()
            .log().all()
                .when()
                    .get("/api/product/")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("$", hasSize(3));
    }

}
