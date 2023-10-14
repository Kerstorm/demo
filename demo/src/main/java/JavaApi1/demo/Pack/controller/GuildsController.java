package JavaApi1.demo.Pack.controller;

import JavaApi1.demo.Details;
import JavaApi1.demo.Pack.model.Guilds;
import JavaApi1.demo.Pack.model.Members;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/guilds")
public class GuildsController {

    List<Guilds> guildsList = new ArrayList<>();

    public GuildsController() throws ParseException {
        guildsList.addAll(Details.getGuildsList());
    }

    @GetMapping
    public List<Guilds> getGuilds() {
        return guildsList;
    }

    @GetMapping("/{guilds_id}")
    public Guilds getGuilds(@PathVariable("guilds_id") int guildsId) {
        return guildsList
                .stream()
                .filter(guilds ->guilds.id() == guildsId)
                .findAny()
                .orElse(null);
    }

    @GetMapping("/{guilds_id}/members")
    public List<Members> getMembersByGuildsId(@PathVariable("guilds_id") int guildsId) throws ParseException {
        List<Members> membersList = Details.getMembersList();
        return membersList
                .stream()
                .filter(members -> members.GuildId() == guildsId)
                .toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Guilds createGuilds(@RequestBody Guilds guilds) {
        guildsList.add(guilds);
        return guilds;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{guilds_id}")
    public void updateGuilds(@RequestBody Guilds guilds, @PathVariable("guilds_id") int guildsId) {
        var exsistingGuilds = guildsList
                .stream()
                .filter(x -> x.id() == guildsId)
                .findAny()
                .orElse(null);

        guildsList.remove(exsistingGuilds);
        guildsList.add(guilds);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{guilds_id}")
    public void deleteGuilds(@PathVariable("guilds_id") int guildsId) {
        var exsistingGuilds = guildsList
                .stream()
                .filter(x -> x.id() == guildsId)
                .findAny()
                .orElse(null);

        if (exsistingGuilds != null) {
            guildsList.remove(exsistingGuilds);
        }
    }
}
