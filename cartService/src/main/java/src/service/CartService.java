package src.service;

import org.springframework.transaction.annotation.Transactional;
import src.exception.CartServiceException;
import lombok.RequiredArgsConstructor;
import src.mapper.CartItemMapper;
import src.mapper.CartMapper;
import src.model.Cart;
import src.model.CartDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import src.model.CartItem;
import src.model.CartItemDTO;
import src.repository.CartItemRepository;
import src.repository.CartRepository;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

    public CartDTO createCart() {
        Cart cart = new Cart();
        cart.setCreatedTime(LocalDateTime.now());
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartDTO getById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartServiceException("There is no cart with given ID", HttpStatus.BAD_REQUEST));
        return cartMapper.toDto(cart);
    }

    @Transactional
    public CartDTO addProductToCart(Long id, CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartServiceException("There is no cart with given ID", HttpStatus.BAD_REQUEST));

        CartItem cartItem = cartItemMapper.toEntity(cartItemDTO);
        cartItemRepository.saveAndFlush(cartItem);

        List<CartItem> items = cart.getItems();
        boolean found = false;

        if (items.isEmpty()) {
            items.add(cartItem);
            cart.setCartPrice(BigDecimal.ZERO);
            cart.setCartPrice(cart.getCartPrice().add(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getAmount()))));
        } else {
            for (CartItem item : items) {
                if (item.getProductId().equals(cartItem.getProductId())) {
                    item.setAmount(item.getAmount() + 1);
                    cart.setCartPrice(cart.getCartPrice().add(cartItem.getPrice()));
                    found = true;
                    break;
                }
            }

            if (!found) {
                items.add(cartItem);
                cart.setCartPrice(cart.getCartPrice().add(cartItem.getPrice()));
            }
        }

        cart.setItems(items);
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }


    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}


