package JavaApi1.demo.repos.members;

import JavaApi1.demo.model.Member;

import java.util.List;

public interface MemberRepo {
    List<Member> getMember();

    Member getMember(Integer memberId);

    void createMember(Member member);

    void updateMember(Member member, Integer memberId);

    void deleteMember(Integer memberId);
}
