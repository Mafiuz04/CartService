package src.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private Long productId;
    private Integer amount;
    private BigDecimal price;
}
