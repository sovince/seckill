package service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/4/5
 * Time: 14:35
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-aop.xml"
})
public class UselesServiceImplTest {

    @Autowired
    UselessServiceImpl testService;

    @Test
    public void say() {
        testService.say();
    }
}