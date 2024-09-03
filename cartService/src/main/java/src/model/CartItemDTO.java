package src.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class CartItemDTO {
    private Long id;
    private Long productId;
    private Integer amount;
    private BigDecimal price;
}
