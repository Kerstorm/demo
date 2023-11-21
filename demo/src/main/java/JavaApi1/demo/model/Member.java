package JavaApi1.demo.model;

import java.util.Date;

public record Member(int memberId, int guildId, String nickname, String realname, Date dateOfJoin, GuildRanks ranks,
                     boolean isActive) {
}
