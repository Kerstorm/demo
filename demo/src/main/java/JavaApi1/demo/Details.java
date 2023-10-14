package JavaApi1.demo;

import model.GuildRanks;
import model.Guilds;
import model.Members;

import java.text.ParseException;
import java.util.List;

public class Details {

    public static List<Guilds> getGuildsList() throws ParseException {
        Guilds guild1 = new Guilds(1, "Medwedi", "10.04.2020", 190, false);
        Guilds guild2 = new Guilds(2, "Prets", "03.03.2018", 200, true);
        Guilds guild3 = new Guilds(3, "Кильки", "28.08.2017", 150, true);
        Guilds guild4 = new Guilds(4, "WhiteRavens", "01.09.2001", 200, true);
        return List.of(guild1, guild2, guild3, guild4);
    }

    public static List<Members> getMembersList() throws ParseException {
        Members members1 = new Members(1, 1, "Прэйли", "Рита", "10.09.2020", GuildRanks.HeadOfTheGuild, true);
        Members members2 = new Members(2, 1, "Trehoux ", "Александр", "10.12.2020", GuildRanks.DeputyGuildHeads, true);
        Members members3 = new Members(3, 1, "GreenMoon", "Александр", "20.08.2022", GuildRanks.GuildOfficer, true);
        return List.of(members1, members2);
    }
}