package src.controller;

import lombok.RequiredArgsConstructor;
import src.model.CartDTO;
import src.model.ProductDto;
import org.springframework.web.bind.annotation.*;
import src.service.CartService;

import java.util.Map;

@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public CartDTO createCart(){
       return cartService.createCart();
    }

    @GetMapping("/{id}")
    public Map<ProductDto,Integer> getCartById(@PathVariable Long id){
       return cartService.getById(id);
    }

    @PatchMapping("/{id}")
    public CartDTO addProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
       return cartService.addProduct(id,productDto);
    }
}
