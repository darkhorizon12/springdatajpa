package study.datajpa.expressions;

import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;
import study.datajpa.entities.Item;
import study.datajpa.entities.QItem;

public class ItemExpression {
    @QueryDelegate(Item.class)
    public static BooleanExpression isExpensive(QItem item, int price) {
        return item.price.gt(price);
    }
}
