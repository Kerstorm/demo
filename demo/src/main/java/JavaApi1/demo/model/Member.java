package JavaApi1.demo.model;

import java.util.Date;

public record Member(Integer memberId, Integer guildId, String nickname, String realname, Date dateOfJoin, GuildRanks ranks,
                     boolean isActive) {
}
