package dao;

import entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/27
 * Time: 19:55
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        int i = successKilledDao.insertSuccessKilled(1000, 13588888888L);
        System.out.println(i);
    }

    @Test
    public void queryByIdAndPhoneWithSeckill() {
        SuccessKilled successKilled = successKilledDao.queryByIdAndPhoneWithSeckill(1000, 13588888888L);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}