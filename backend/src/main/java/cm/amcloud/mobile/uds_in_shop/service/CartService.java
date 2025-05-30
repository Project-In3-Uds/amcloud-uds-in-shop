package cm.amcloud.mobile.uds_in_shop.service;

import cm.amcloud.mobile.uds_in_shop.model.Cart;
import cm.amcloud.mobile.uds_in_shop.model.CartItem;

public interface CartService {
    Cart getCartByUserId(Long userId);
    Cart addItemToCart(Long userId, CartItem cartItem);
    Cart removeItemFromCart(Long userId, Long cartItemId);
    void clearCart(Long userId);
}
