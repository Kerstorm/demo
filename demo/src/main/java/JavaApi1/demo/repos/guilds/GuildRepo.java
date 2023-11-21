package JavaApi1.demo.repos.guilds;
import JavaApi1.demo.model.Guild;
import JavaApi1.demo.model.Member;
import java.util.List;

public interface GuildRepo {
    List<Guild> getGuild();

    Guild getGuild(int guildId);

    List<Member> getMemberByGuildId(int guildId);

    void createGuild(Guild guild);

    void updateGuild(Guild guild, int guildId);

    void deleteGuild(int guildId);
}