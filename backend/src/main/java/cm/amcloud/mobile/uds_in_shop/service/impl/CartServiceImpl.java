package cm.amcloud.mobile.uds_in_shop.service.impl;

import org.springframework.stereotype.Service;

import cm.amcloud.mobile.uds_in_shop.exception.ResourceNotFoundException;
import cm.amcloud.mobile.uds_in_shop.model.Cart;
import cm.amcloud.mobile.uds_in_shop.model.CartItem;
import cm.amcloud.mobile.uds_in_shop.model.User;
import cm.amcloud.mobile.uds_in_shop.repository.CartItemRepository;
import cm.amcloud.mobile.uds_in_shop.repository.CartRepository;
import cm.amcloud.mobile.uds_in_shop.repository.UserRepository;
import cm.amcloud.mobile.uds_in_shop.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    @Override
    public Cart addItemToCart(Long userId, CartItem cartItem) {
        Cart cart = getCartByUserId(userId);
        cartItem.setCart(cart);
        cart.getCartItems().add(cartItem);
        cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItemFromCart(Long userId, Long cartItemId) {
        Cart cart = getCartByUserId(userId);
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + cartItemId));
        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }
}
