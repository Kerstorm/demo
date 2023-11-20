package JavaApi1.demo.Pack.model;

import java.util.Date;

public record Member(int memberId, int guildId, String nickname, String realName, Date dateOfJoin, GuildRanks rank,
                     boolean isActive) {

}
