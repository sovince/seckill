package dao;

import entity.Seckill;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/26
 * Time: 22:46
 * Description:
 * <p>
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test和junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入DAO实现类依赖
    @Resource
    private SeckillDao seckillDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void reduceNumber() {
        long id = 1000;
        int result = seckillDao.reduceNumber(id, new Date());
        System.out.println(result);

    }

    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill.getCreateTime());
        //System.out.println(seckill);
        logger.info("seckill={}", seckill);

    }

    @Test
    public void queryAll() {
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

    @Test
    public void connectRedis() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);

    }

    @Test
    public void executeSeckillByProceducre() {
        /**
         *           #{seckillId,jdbcType=BIGINT,mode=IN},
         *           #{phone,jdbcType=BIGINT,mode=IN},
         *           #{killTime,jdbcType=TIMESTAMP,mode=IN},
         *           #{result,jdbcType=INTEGER,mode=OUT},
         */
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("seckillId", 1001L);
        paramMap.put("phone", 13599999999L);
        paramMap.put("killTime", new Date());
        paramMap.put("result", null);

        seckillDao.executeSeckillByProceducre(paramMap);

        Integer result = MapUtils.getInteger(paramMap, "result", -2);
        logger.info("result={}", result);

    }
}