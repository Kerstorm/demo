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
public class H2MemberRepo implements MemberRepo {
    private static final String CREATE = """
            INSERT IntegerO MEMBERS (MEMBER_ID, GUILD_ID, NICKNAME, REALNAME, DATE_OF_JOIN, RANKS, IS_ACTIVE)
            VALUES (:memberId, :guildId, :nickname, :realname, :dateOfJoin, :ranks, :isActive)
            """;

    private static final String UPDATE = """
            UPDATE MEMBERS SET 
            GUILD_ID = :guildId,
            NICKNAME = :nickname,
            REALNAME = :realname,
            DATE_OF_JOIN = :dateOfJoin,
            RANKS = :ranks,
            IS_ACTIVE = :isActive
            WHERE MEMBER_ID = :memberId
            """;

    private final RowMapper<Member> rowMapper = new DataClassRowMapper<>(Member.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public H2MemberRepo(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Member> getMember() {
        return jdbcTemplate.query("SELECT * FROM MEMBERS", rowMapper);
    }

    public Member getMember(Integer memberId) {
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

    public void updateMember(Member member, Integer memberId) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);
        namedParameterJdbcTemplate.update(UPDATE, parameterSource);
    }

    public void deleteMember(Integer memberId) {
        jdbcTemplate.update("DELETE FROM MEMBERS WHERE MEMBER_ID = ?", memberId);
    }
}
