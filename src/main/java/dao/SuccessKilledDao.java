package dao;

import entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/24
 * Time: 18:33
 * Description:
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细 可过滤重复  long seckillId,long userPhone 是联合主键
     *
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    /**
     * 根据主键查询明细 携带产品对象
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdAndPhoneWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}
