package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    /**필드 주입 사용하지 않는 것을 추천**/
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**생성자 주입**/
     @Autowired //생성자가 하나있을 때는 @Autowired를 생략할 수 있음
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        //System.out.println("memberRepository = " + memberRepository);
        //System.out.println("discountPolicy = " + discountPolicy);   <-- 값들이 잘 들어왔는지 확인하기 위해서
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;

    }
    /**수정자 주입**/
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        //System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository=memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        //System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy=discountPolicy;
//    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findById(memberId);
        int discountPrice= discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
