package JavaApi1.demo.Pack.model;

import java.util.Date;

public record Member(int MemberId, int GuildId, String Nickname, String Realname, Date Date_of_join, GuildRanks Ranks,
                     boolean Is_active) {
}
