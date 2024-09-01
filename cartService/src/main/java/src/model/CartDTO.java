package src.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private BigDecimal cartPrice;
    private List<CartItemDTO> items;
    private LocalDateTime createdTime;
}
