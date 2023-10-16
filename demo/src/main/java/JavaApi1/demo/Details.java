package JavaApi1.demo;

import JavaApi1.demo.Pack.model.GuildRanks;
import JavaApi1.demo.Pack.model.Guild;
import JavaApi1.demo.Pack.model.Member;

import java.text.ParseException;
import java.util.List;

public class Details {

    public static List<Guild> getGuildsList() throws ParseException {
        Guild guild1 = new Guild(1, "Medwedi", "10.04.2020", 190, false);
        Guild guild2 = new Guild(2, "Prets", "03.03.2018", 200, true);
        Guild guild3 = new Guild(3, "Кильки", "28.08.2017", 150, true);
        Guild guild4 = new Guild(4, "WhiteRavens", "01.09.2015", 200, true);
        return List.of(guild1, guild2, guild3, guild4);
    }

    public static List<Member> getMembersList() throws ParseException {
        Member member1 = new Member(1, 1, "Прэйли", "Рита", "10.09.2020", GuildRanks.GUILD_HEAD, true);
        Member member2 = new Member(2, 1, "Trehoux ", "Александр", "10.12.2020", GuildRanks.DEPUTY_GUILD_HEADS, true);
        Member member3 = new Member(3, 1, "GreenMoon", "Александр", "20.08.2022", GuildRanks.GUILD_OFFICER, true);
        return List.of(member1, member2, member3);
    }
}