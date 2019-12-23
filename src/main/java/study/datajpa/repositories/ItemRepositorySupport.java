package study.datajpa.repositories;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import study.datajpa.dtos.ItemDTO;
import study.datajpa.entities.Item;

import java.util.List;

import static study.datajpa.entities.QItem.item;

@Repository
public class ItemRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public ItemRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Item.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Item> isExpensiveList(int price) {
        return jpaQueryFactory
                .selectFrom(item)
                .where(item.isExpensive(price))
                .orderBy(item.price.desc())
                .fetch();
    }

    // projections to custom dto
    public List<ItemDTO> getCustomDTO(int price) {
        QBean<ItemDTO> bean = Projections.bean(ItemDTO.class, item.id, item.name, item.price, item.stockQuantity);

        return jpaQueryFactory
                .select(bean)
                .from(item)
                .where(item.isExpensive(price))
                .orderBy(item.price.desc())
                .fetch();
    }
}
