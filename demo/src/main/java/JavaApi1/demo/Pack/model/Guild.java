package JavaApi1.demo.Pack.model;

import java.util.Date;

public record Guild(int guildId, String title, Date createDate, Integer countMembers, boolean pvp) {
}


