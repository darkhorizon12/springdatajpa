package study.datajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
