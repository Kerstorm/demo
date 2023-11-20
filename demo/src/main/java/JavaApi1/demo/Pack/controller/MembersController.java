package JavaApi1.demo.Pack.controller;

import JavaApi1.demo.Pack.model.Member;
import JavaApi1.demo.repos.members.member_repo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/members")
public class MembersController {
    private final member_repo repository;


    public MembersController(member_repo repository) throws ParseException {
        this.repository = repository;
    }

    @GetMapping
    public List<Member> getMember() {
        return repository.getMember();
    }

    @GetMapping("/{member_id}")
    public Member getMember(@PathVariable("member_id") int memberId) {
        return repository.getMember(memberId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createMember(@RequestBody Member member) {
        repository.createMember(member);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{member_id}")
    public void updateMember(@RequestBody Member member, @PathVariable("member_id") int memberId) {
        repository.updateMember(member, memberId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{member_id}")
    public void deleteMember(@PathVariable("member_id") int memberId) {
        repository.deleteMember(memberId);
    }
}