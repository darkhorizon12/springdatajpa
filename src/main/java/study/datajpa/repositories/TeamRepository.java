package study.datajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
