package JavaApi1.demo.repos.members;

import JavaApi1.demo.exceptions.NotFoundException;
import JavaApi1.demo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class member_db implements member_repo {
    private static final String CREATE = """
            INSERT INTO MEMBERS (MEMBER_ID, GUILD_ID, NICKNAME, REALNAME, DATE_OF_JOIN, RANKS, IS_ACTIVE)
            VALUES (:MemberId, :GuildId, :Nickname, :Realname, :Date_of_join, :Ranks, :Is_active)
            """;

    private static final String UPDATE = """
            UPDATE MEMBERS SET 
            GUILD_ID = :GuildId,
            NICKNAME = :Nickname,
            REALNAME = :Realname,
            DATE_OF_JOIN = :Date_of_join,
            RANKS = :Ranks,
            IS_ACTIVE = :Is_active
            WHERE MEMBER_ID = :MemberId
            """;

    private final RowMapper<Member> rowMapper = new DataClassRowMapper<>(Member.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public member_db(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Member> getMember() {
        return jdbcTemplate.query("SELECT * FROM MEMBERS", rowMapper);
    }

    public Member getMember(int memberId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM MEMBERS WHERE MEMBER_ID = ?", rowMapper, memberId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Member with id = [" + memberId + "] not found", e);
        }
    }

    public void createMember(Member member) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);
        namedParameterJdbcTemplate.update(CREATE, parameterSource);
    }

    public void updateMember(Member member, int memberId) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);
        namedParameterJdbcTemplate.update(UPDATE, parameterSource);
    }

    public void deleteMember(int memberId) {
        jdbcTemplate.update("DELETE FROM MEMBERS WHERE MEMBER_ID = ?", memberId);
    }
}
