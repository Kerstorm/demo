package JavaApi1.demo.Pack.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record Guild(Integer id, String title, Date createDate, float countMembers, boolean pvp) {

    public Guild(Integer id, String title, String createDate, float countMembers, boolean pvp) throws ParseException {
        this(id, title, new SimpleDateFormat("dd.MM.yyyy").parse(createDate), countMembers, pvp);
    }
}


