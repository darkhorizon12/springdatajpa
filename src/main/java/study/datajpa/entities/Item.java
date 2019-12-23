package study.datajpa.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by finrir on 2019-12-16
 * Description:
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"name", "price", "stockQuantity"})
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
