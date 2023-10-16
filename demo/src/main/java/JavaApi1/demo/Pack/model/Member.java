package JavaApi1.demo.Pack.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record Member(Integer id, Integer guildId, String nickname, String realName, Date dateOfJoin, GuildRanks rank,
                      boolean isActive) {

    public Member(Integer id, Integer guildId, String Nickname, String RealName, String dateOfJoin, GuildRanks rank, boolean isActive) throws ParseException {
        this(id, guildId, Nickname, RealName, new SimpleDateFormat("dd.MM.yyyy").parse(dateOfJoin), rank, isActive);
    }
}
