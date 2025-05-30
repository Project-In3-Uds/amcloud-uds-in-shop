package cm.amcloud.mobile.uds_in_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cm.amcloud.mobile.uds_in_shop.model.Cart;
import cm.amcloud.mobile.uds_in_shop.model.CartItem;
import cm.amcloud.mobile.uds_in_shop.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long userId, @RequestBody CartItem cartItem) {
        return ResponseEntity.ok(cartService.addItemToCart(userId, cartItem));
    }

    @PutMapping("/{userId}/items/{cartItemId}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long userId, @PathVariable Long cartItemId, @RequestBody CartItem updatedCartItem) {
        Cart cart = cartService.getCartByUserId(userId);
        cart.getCartItems().stream()
            .filter(item -> item.getId().equals(cartItemId))
            .findFirst()
            .ifPresent(item -> {
                item.setQuantity(updatedCartItem.getQuantity());
                item.setProduct(updatedCartItem.getProduct());
            });
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{userId}/items/{cartItemId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long userId, @PathVariable Long cartItemId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, cartItemId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
