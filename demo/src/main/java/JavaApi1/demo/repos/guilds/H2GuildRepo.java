package JavaApi1.demo.repos.guilds;

import JavaApi1.demo.exceptions.NotFoundException;
import JavaApi1.demo.model.Guild;
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
public class H2GuildRepo implements GuildRepo {
    private static final String CREATE = """
            INSERT INTO GUILDS (GUILD_ID, TITLE, CREATE_DATE, COUNT_MEMBERS, PVP)
            VALUES (:GuildId, :Title, :Create_date, :Count_members, :PvP)
            """;

    private static final String UPDATE = """
            UPDATE GUILDS SET TITLE = :Title,
            CREATE_DATE = :Create_date,
            COUNT_MEMBERS = :Count_members,
            PVP = :PvP
            WHERE GUILD_ID = :GuildId
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
    public Guild getGuild(int guildId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM GUILDS WHERE GUILD_ID = ?", rowMapper, guildId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Guild with id = [" + guildId + "] not found", e);
        }
    }

    @Override
    public List<Member> getMemberByGuildId(int guildId) {
        return jdbcTemplate.query("SELECT * FROM MEMBERS WHERE GUILD_ID = ?", MemberRowMapper, guildId);
    }

    @Override
    public void createGuild(Guild guild) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(guild);
        namedParameterJdbcTemplate.update(CREATE, parameterSource);
    }

    @Override
    public void updateGuild(Guild guild, int guildId) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(guild);
        namedParameterJdbcTemplate.update(UPDATE, parameterSource);
    }

    @Override
    public void deleteGuild(int guildId) {
        jdbcTemplate.update("DELETE FROM GUILDS WHERE GUILD_ID =?", guildId);
    }
}
