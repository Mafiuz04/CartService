package src.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CartDTO {
    private Long id;
    private Map<Product,Integer> products = new HashMap<>();
}
