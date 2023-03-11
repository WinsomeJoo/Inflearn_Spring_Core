package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){

        ApplicationContext ac= new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        int price1 = statefulService1.order("user A", 10000);
        //ThreadB: B사용자 20000원 주문
        int price2 = statefulService2.order("user b", 20000);

        //ThredA: 사용지 A 주문금액 조회

        //int price1 = statefulService1.getPrice();

        //TheadA: 사용자는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        System.out.println("price1 = " + price1);

        //assertThat(price1).isEqualTo(20000);


    }


    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

    }

}