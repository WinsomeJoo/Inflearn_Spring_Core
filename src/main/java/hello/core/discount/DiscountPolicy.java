package hello.core.discount;

import hello.core.member.Member;

/** return 할인 금액대상  **/
public interface DiscountPolicy {

    int discount(Member member,int price);

}
