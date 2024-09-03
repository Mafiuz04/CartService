package src.mapper;

import src.model.Cart;
import src.model.CartDTO;
import org.mapstruct.Mapper;
import src.model.CartItem;
import src.model.CartItemDTO;


@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO toDto(Cart cart);

    Cart toEntity(CartDTO CartDto);

    CartItemDTO toDto(CartItem cartItem);

    CartItem toEntity(CartItemDTO cartItemDTO);
}
