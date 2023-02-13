package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{


    private static Map<Long,Member> store= new HashMap<>();


    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        //멤버 id로 회원(객체) 조회하기
        return store.get(memberId);
    }
}
