package study.datajpa.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by finrir on 2019-12-16
 * Description:
 */
@Repository
public class MemberJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



}
