package service;

import dto.Exposer;
import dto.SeckillExcution;
import entity.Seckill;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/29
 * Time: 22:26
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("seckillList={}", seckillList);
    }

    @Test
    public void getById() {
        Seckill seckill = seckillService.getById(1000L);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() {
        Exposer exposer = seckillService.exportSeckillUrl(1000L);
        logger.info("exposer={}", exposer);
        //{exposed=true, md5='b00a2dff432789d2bead5a77e29c80aa', seckillId=1000,
    }

    @Test
    public void executeSeckill() {
        Long id = 1000L;
        Long phone = 13588381366L;
        String md5 = "b00a2dff432789d2bead5a77e29c80aa";
        SeckillExcution seckillExcution = seckillService.executeSeckill(id, phone, md5);
        logger.info("result={}", seckillExcution);

        //result=SeckillExcution{
        // seckillId=1000, state=1, stateInfo='秒杀成功',
        // successKilled=
        // SuccessKilled{seckillId=1000, userPhone=13588381366, state=-1, createTime=Fri Mar 29 22:44:25 CST 2019}
        // }
    }

    /**
     * 完整的逻辑测试
     */
    @Test
    public void seckillLogic() {
        Long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.getExposed()) {
            logger.info("exposer={}", exposer);
            Long phone = 13588381369L;
            String md5 = exposer.getMd5();
            try {
                SeckillExcution seckillExcution = seckillService.executeSeckill(id, phone, md5);
                logger.info("seckillExcution={}", seckillExcution);
            } catch (RepeatKillException | SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("exposer={}", exposer);
        }

    }
}