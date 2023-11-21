package JavaApi1.demo.controller;

import JavaApi1.demo.model.Guild;
import JavaApi1.demo.model.Member;
import JavaApi1.demo.repos.guilds.H2GuildRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/guilds")
public class GuildsController {
    private final H2GuildRepo repository;

    @Autowired
    public GuildsController(H2GuildRepo repository) throws ParseException {
        this.repository = repository;
    }

    @GetMapping
    public List<Guild> getGuild() {
        return repository.getGuild();
    }

    @GetMapping("/{guild_id}")
    public Guild getGuilds(@PathVariable("guild_id") int guildId) {
        return repository.getGuild(guildId);
    }

    @GetMapping("/{guild_id}/members")
    public List<Member> getMemberByGuildId(@PathVariable("guild_id") int guildId) throws ParseException {
        return repository.getMemberByGuildId(guildId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createGuild(@RequestBody Guild guild) {
        repository.createGuild(guild);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{guild_id}")
    public void updateGuild(@RequestBody Guild guild, @PathVariable("guild_id") int guildId) {
        repository.updateGuild(guild, guildId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{guild_id}")
    public void deleteGuild(@PathVariable("guild_id") int guildId) {
        repository.deleteGuild(guildId);
    }
}
