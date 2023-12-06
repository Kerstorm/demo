package JavaApi1.demo.repos.guilds;

import JavaApi1.demo.exceptions.NotFoundException;
import JavaApi1.demo.exceptions.NotUniquePrimaryKeyException;
import JavaApi1.demo.model.Guild;
import JavaApi1.demo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class H2GuildRepo implements GuildRepo {
    private static final String CREATE = """
            INSERT INTO GUILDS (GUILD_ID, TITLE, CREATE_DATE, COUNT_MEMBERS, PVP)
            VALUES (:guildId, :title, :createDate, :countMembers, :pvp)
            """;

    private static final String UPDATE = """
            UPDATE GUILDS SET TITLE = :title,
            CREATE_DATE = :createDate,
            COUNT_MEMBERS = :countMembers,
            PVP = :pvp
            WHERE GUILD_ID = :guildId
            """;

    private final RowMapper<Guild> rowMapper = new DataClassRowMapper<>(Guild.class);
    private final RowMapper<Member> MemberRowMapper = new DataClassRowMapper<>(Member.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public H2GuildRepo(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Guild> getGuild() {
        return jdbcTemplate.query("SELECT * FROM GUILDS", rowMapper);
    }

    @Override
    public Guild getGuild(Integer guildId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM GUILDS WHERE GUILD_ID = ?", rowMapper, guildId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Guild with id = [" + guildId + "] not found", e);
        }
    }

    @Override
    public List<Member> getMemberByGuildId(Integer guildId) {
        return jdbcTemplate.query("SELECT * FROM MEMBERS WHERE GUILD_ID = ?", MemberRowMapper, guildId);
    }

    @Override
    public void createGuild(Guild guild) {
        try {
            BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(guild);
            namedParameterJdbcTemplate.update(CREATE, parameterSource);
        } catch (DuplicateKeyException e) {
            throw new NotUniquePrimaryKeyException("Такая гильдия уже существует" + guild.guildId(), e);
        }
    }

    @Override
    public void updateGuild(Guild guild, Integer guildId) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(guild);
        namedParameterJdbcTemplate.update(UPDATE, parameterSource);
    }

    @Override
    public void deleteGuild(Integer guildId) {
        jdbcTemplate.update("DELETE FROM GUILDS WHERE GUILD_ID =?", guildId);
    }
}
