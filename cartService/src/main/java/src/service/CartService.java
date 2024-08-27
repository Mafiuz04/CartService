package src.service;

import src.exception.CartServiceException;
import lombok.RequiredArgsConstructor;
import src.mapper.CartMapper;
import src.mapper.ProductMapper;
import src.model.Cart;
import src.model.CartDTO;
import src.model.Product;
import src.model.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import src.repository.CartRepository;
import src.repository.ProductRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    public CartDTO createCart(){
        Cart cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public Map<ProductDto,Integer> getById(Long id){
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartServiceException("There is no cart with given ID", HttpStatus.BAD_REQUEST));
        return cartMapper.mapProductMapToDtoMap(cart.getProducts());
    }

    public CartDTO addProduct(Long id, ProductDto productDto){
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartServiceException("There is no cart with given ID", HttpStatus.BAD_REQUEST));
        productRepository.save(productMapper.toEntity(productDto));
        Map<Product, Integer> products = cart.getProducts();
        if(products.containsKey(productDto)){
            Integer productAmount = cart.getProducts().get(productDto);
            productAmount++;
            cartRepository.saveAndFlush(cart);
        } else if (!products.containsKey(productDto)) {
            cart.getProducts().putIfAbsent(productMapper.toEntity(productDto),Integer.valueOf(1));
            cartRepository.saveAndFlush(cart);
        }
        return cartMapper.toDto(cart);
    }
}
