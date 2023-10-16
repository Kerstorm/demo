package JavaApi1.demo.Pack.controller;

import JavaApi1.demo.Details;
import JavaApi1.demo.Pack.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/members")
public class MembersController {

    List<Member> membersList = new ArrayList<>();

    public MembersController() throws ParseException {
        membersList.addAll(Details.getMembersList());
    }

    @GetMapping
    public List<Member> getMembers() {
        return membersList;
    }

    @GetMapping("/{member_id}")
    public Member getMembers(@PathVariable("member_id") Integer memberId) {
        return membersList.stream()
                .filter(member -> member.id().equals(memberId))
                .findAny()
                .orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Member createMembers(@RequestBody Member member) {
        membersList.add(member);
        return member;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{member_id}")
    public void updateMembers(@RequestBody Member member, @PathVariable("member_id") Integer memberId) {
        var exsistingMember = membersList
                .stream()
                .filter(x -> x.id().equals(memberId))
                .findAny()
                .orElse(null);

        membersList.remove(exsistingMember);
        membersList.add(member);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{member_id}")
    public void deleteMembers(@PathVariable("member_id") Integer memberId) {
        var exsistingMember = membersList
                .stream()
                .filter(x -> x.id().equals(memberId))
                .findAny()
                .orElse(null);

        membersList.remove(exsistingMember);
    }
}