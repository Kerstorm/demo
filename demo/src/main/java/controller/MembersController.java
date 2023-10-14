package controller;

import JavaApi1.demo.Details;
import model.Members;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/members")
public class MembersController {

    List<Members> membersList = new ArrayList<>();

    public MembersController() throws ParseException {
        membersList.addAll(Details.getMembersList());
    }

    @GetMapping
    public List<Members> getMembers() {
        return membersList;
    }

    @GetMapping("/{members_id}")
    public Members getMembers(@PathVariable("members_id") int membersId) {
        return membersList.stream()
                .filter(members -> members.id() == membersId)
                .findAny()
                .orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Members createMembers(@RequestBody Members members) {
        membersList.add(members);
        return members;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{members_id}")
    public void updateMembers(@RequestBody Members members, @PathVariable("members_id") int membersId) {
        var exsistingMembers = membersList
                .stream()
                .filter(x -> x.id() == membersId)
                .findAny()
                .orElse(null);

        membersList.remove(exsistingMembers);
        membersList.add(members);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{members_id}")
    public void deleteMembers(@PathVariable("members_id") int membersId) {
        var exsistingMembers = membersList
                .stream()
                .filter(x -> x.id() == membersId)
                .findAny()
                .orElse(null);

        membersList.remove(exsistingMembers);
    }
}