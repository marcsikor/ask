package pl.jkanclerz.productcatalog;

import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductCatalogTest {

    @Test
    void itAllowsToListMyProducts() {
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        List<Product> products = catalog.allProducts();
        //Assert
        assertListIsEmpty(products);
    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog();
    }

    private void assertListIsEmpty(List<Product> products) {
        assert 0 == products.size();
    }

    @Test
    void itAllowsToAddProduct() {
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act


        //Assert
    }

    @Test
    void itAllowsToChangePrice() {

    }

    @Test
    void itAllowsToAssignImage() {

    }

    @Test
    void itAllowsToPublishProduct() {

    }

    @Test
    void publicationIsPossibleWhenPriceAndImageAreDefined() {

    }
}
