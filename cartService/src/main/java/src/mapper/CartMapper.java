package src.mapper;

import src.model.Cart;
import src.model.CartDTO;
import src.model.Product;
import src.model.ProductDto;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO toDto(Cart cart);

    Cart toEntity(CartDTO CartDto);

    Map<ProductDto, Integer> mapProductMapToDtoMap(Map<Product, Integer> products);
}
