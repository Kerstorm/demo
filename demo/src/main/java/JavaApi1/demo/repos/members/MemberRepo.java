package JavaApi1.demo.repos.members;

import JavaApi1.demo.model.Member;

import java.util.List;

public interface MemberRepo {
    List<Member> getMember();

    Member getMember(int memberId);

    void createMember(Member member);

    void updateMember(Member member, int memberId);

    void deleteMember(int memberId);
}
