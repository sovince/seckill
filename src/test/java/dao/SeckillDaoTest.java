package dao;

import entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/26
 * Time: 22:46
 * Description:
 *
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test和junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入DAO实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() {
        long id = 1000;
        int result = seckillDao.reduceNumber(id,new Date());
        System.out.println(result);

    }

    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill.getCreateTime());
        System.out.println(seckill);

    }

    @Test
    public void queryAll() {
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill:seckills){
            System.out.println(seckill);
        }

    }
}