package study.datajpa.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class ItemDTO {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
//    public ItemDTO(Long id, String name, int price, int stockQuantity) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.stockQuantity = stockQuantity;
//    }
}
