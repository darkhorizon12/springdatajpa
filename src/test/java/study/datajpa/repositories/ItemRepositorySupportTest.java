package study.datajpa.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entities.Item;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class ItemRepositorySupportTest {

    @Autowired ItemRepositorySupport repositorySupport;

    @Test
    public void 쿼리DSL_프로젝션_테스트() {
        List<Item> expensiveList = repositorySupport.isExpensiveList(9_000_000);

        assertThat(expensiveList.size()).isEqualTo(10);
        Item item = expensiveList.stream().findFirst().get();
        assertThat(item.getId()).isEqualTo(272L);
    }
}