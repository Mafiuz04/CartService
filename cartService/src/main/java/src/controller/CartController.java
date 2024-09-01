package src.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import src.model.CartDTO;
import org.springframework.web.bind.annotation.*;
import src.model.CartItemDTO;
import src.service.CartService;


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
    public CartDTO getCartById(@PathVariable Long id){
       return cartService.getById(id);
    }

    @PatchMapping("/add/{id}")
    public CartDTO addProduct(@PathVariable Long id, @RequestBody CartItemDTO itemDTO){
       return cartService.addProductToCart(id,itemDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        ResponseEntity.accepted();
    }
}
