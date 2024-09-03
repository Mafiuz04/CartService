package src.mapper;

import org.mapstruct.Mapper;
import src.model.CartItem;
import src.model.CartItemDTO;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDTO toDto(CartItem cartItem);

    CartItem toEntity(CartItemDTO cartItemDTO);
}
