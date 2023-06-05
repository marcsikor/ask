package pl.jkanclerz.sales;

import pl.jkanclerz.sales.cart.Cart;
import pl.jkanclerz.sales.cart.CartStorage;
import pl.jkanclerz.sales.offering.EveryNItemLineDiscountPolicy;
import pl.jkanclerz.sales.offering.Offer;
import pl.jkanclerz.sales.offering.OfferCalculator;
import pl.jkanclerz.sales.offering.TotalDiscountPolicy;
import pl.jkanclerz.sales.productdetails.NoSuchProductException;
import pl.jkanclerz.sales.productdetails.ProductDetails;
import pl.jkanclerz.sales.productdetails.ProductDetailsProvider;

import java.math.BigDecimal;
import java.util.Optional;

public class Sales {
    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;
    private final OfferCalculator offerCalculator;

    public Sales(CartStorage cartStorage, ProductDetailsProvider productDetails, OfferCalculator offerCalculator) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetails;
        this.offerCalculator = offerCalculator;
    }

    public void addToCart(String customerId, String productId) {
        Cart customerCart = loadCartForCustomer(customerId)
                .orElse(Cart.empty());

        ProductDetails product = loadProductDetails(productId)
                .orElseThrow(() -> new NoSuchProductException());

        customerCart.add(product.getId());

        cartStorage.addForCustomer(customerId, customerCart);
    }

    private Optional<ProductDetails> loadProductDetails(String productId) {
        return productDetailsProvider.load(productId);
    }

    private Optional<Cart> loadCartForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }

    public Offer getCurrentOffer(String customerId) {
        Cart customerCart = loadCartForCustomer(customerId)
                .orElse(Cart.empty());

        Offer offer = this.offerCalculator.calculateOffer(
                customerCart.getCartItems(),
                new TotalDiscountPolicy(BigDecimal.valueOf(500), BigDecimal.valueOf(50)),
                new EveryNItemLineDiscountPolicy(5)
        );

        return offer;
    }

    public void acceptOffer() {

    }
}
