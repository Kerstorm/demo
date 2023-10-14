package JavaApi1.demo.Pack.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record Members(int id, int GuildId, String Nickname, String RealName, Date dateOfJoin, GuildRanks rank,
                      boolean isActive) {

    public Members(int id, int GuildId, String Nickname, String RealName, String dateOfJoin, GuildRanks rank, boolean isActive) throws ParseException {
        this(id, GuildId, Nickname, RealName, new SimpleDateFormat("dd.MM.yyyy").parse(dateOfJoin), rank, isActive);
    }
}
