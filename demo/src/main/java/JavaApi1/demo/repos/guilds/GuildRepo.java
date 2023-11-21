package JavaApi1.demo.repos.guilds;
import JavaApi1.demo.model.Guild;
import JavaApi1.demo.model.Member;
import java.util.List;

public interface GuildRepo {
    List<Guild> getGuild();

    Guild getGuild(Integer guildId);

    List<Member> getMemberByGuildId(Integer guildId);

    void createGuild(Guild guild);

    void updateGuild(Guild guild, Integer guildId);

    void deleteGuild(Integer guildId);
}