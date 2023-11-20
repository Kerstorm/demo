package JavaApi1.demo.Pack.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record Guild(Integer id, String title, Date createDate, Integer countMembers, boolean pvp) {

    public Guild(Integer id, String title, String createDate, Integer countMembers, boolean pvp) throws ParseException {
        this(id, title, new SimpleDateFormat("dd.MM.yyyy").parse(createDate), countMembers, pvp);
    }
}


