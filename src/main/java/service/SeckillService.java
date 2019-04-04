package service;

import dto.Exposer;
import dto.SeckillExcution;
import entity.Seckill;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import exception.SeckillException;

import java.util.List;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/29
 * Time: 20:11
 * Description: 业务接口
 *
 * 站在使用者的角度设计接口
 *
 * 注意三个方面：方法定义的力度，参数，返回类型
 */
public interface SeckillService {
    /**
     * 查询所有
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个
     * @param seckillId
     * @return
     */
    Seckill getById(Long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址，
     * 否则输出系统时间和秒杀时间
     * @param seckillId id
     * @return
     */
    Exposer exportSeckillUrl(Long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5 验证
     * @return SeckillExcution dto
     */
    SeckillExcution executeSeckill(Long seckillId, Long userPhone, String md5)
        throws SeckillException,SeckillCloseException, RepeatKillException;

    SeckillExcution executeSeckillByProceducre(Long seckillId, Long userPhone, String md5);
}
