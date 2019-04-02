package dao.cache;

import dao.SeckillDao;
import dao.SuccessKilledDao;
import entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/4/2
 * Time: 10:08
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisSeckillDaoTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    SuccessKilledDao successKilledDao;

    @Autowired
    RedisSeckillDao redisSeckillDao;

    @Test
    public void get() {
        Seckill seckill = redisSeckillDao.get(1004L);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void set() {
        String set = redisSeckillDao.set(seckillDao.queryById(1000L));// 成功返回 "OK"
        logger.info("set={}",set);
    }
}