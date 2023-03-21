package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {



    @Test
    public void SingletonTest(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonTest1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonTest2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonTest1 = " + singletonTest1);
        System.out.println("singletonTest2 = " + singletonTest2);

        assertThat(singletonTest1).isSameAs(singletonTest1);
        ac.close();

    }






    static class SingletonBean{

        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }

    }
}
