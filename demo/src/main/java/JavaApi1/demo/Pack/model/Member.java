package JavaApi1.demo.Pack.model;

import java.util.Date;

public record Member(Integer id, Integer guildId, String nickname, String realName, Date dateOfJoin, GuildRanks rank,
                     boolean isActive) {

}
