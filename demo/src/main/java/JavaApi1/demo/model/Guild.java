package JavaApi1.demo.model;

import java.util.Date;

public record Guild(Integer guildId, String title, Date createDate, Integer countMembers, boolean PvP) {
}


