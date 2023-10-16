package JavaApi1.demo.Pack.controller;

import JavaApi1.demo.Details;
import JavaApi1.demo.Pack.model.Guild;
import JavaApi1.demo.Pack.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/guilds")
public class GuildsController {

    List<Guild> guildsList = new ArrayList<>();

    public GuildsController() throws ParseException {
        guildsList.addAll(Details.getGuildsList());
    }

    @GetMapping
    public List<Guild> getGuild() {
        return guildsList;
    }

    @GetMapping("/{guild_id}")
    public Guild getGuilds(@PathVariable("guild_id") Integer guildId) {
        return guildsList
                .stream()
                .filter(guild -> guild.id().equals(guildId))
                .findAny()
                .orElse(null);
    }

    @GetMapping("/{guild_id}/members")
    public List<Member> getMembersByGuildsId(@PathVariable("guild_id") Integer guildId) throws ParseException {
        List<Member> membersList = Details.getMembersList();
        return membersList
                .stream()
                .filter(members -> members.guildId().equals(guildId))
                .toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Guild createGuild(@RequestBody Guild guilds) {
        guildsList.add(guilds);
        return guilds;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{guild_id}")
    public void updateGuild(@RequestBody Guild guild, @PathVariable("guild_id") Integer guildId) {
        var exsistingGuild = guildsList
                .stream()
                .filter(x -> x.id().equals(guildId))
                .findAny()
                .orElse(null);

        guildsList.remove(exsistingGuild);
        guildsList.add(guild);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{guild_id}")
    public void deleteGuild(@PathVariable("guild_id") Integer guildId) {
        var exsistingGuild = guildsList
                .stream()
                .filter(x -> x.id().equals(guildId))
                .findAny()
                .orElse(null);

        if (exsistingGuild != null) {
            guildsList.remove(exsistingGuild);
        }
    }
}
