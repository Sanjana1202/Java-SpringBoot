import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringApplicationContext.class)
public class TestSPR1 {
    @Inject
    @Named("firstBean")
    String hello;


    @Test
    void testHelloBean(){
        System.out.println(hello);
        Assertions.assertEquals(hello, "test from spring!");
    }
}

