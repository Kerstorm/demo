package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record Guilds(int id, String title, Date createDate, float countMembers, boolean PvP) {

    public Guilds(int id, String title, String createDate, float countMembers, boolean PvP) throws ParseException {
        this(id, title, new SimpleDateFormat("dd.MM.yyyy").parse(createDate), countMembers, PvP);
    }
}


