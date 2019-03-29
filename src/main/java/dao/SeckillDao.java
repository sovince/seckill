package dao;

import entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/24
 * Time: 18:21
 * Description:
 */
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @param killTime 对应着createTime
     * @return 影响行数
     */
    int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);


    /**
     * 根据id查询商品
     * @param seckillId
     * @return
     */
    Seckill queryById(Long seckillId);

    /**
     * 根据偏移量查询商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") Integer offset, @Param("limit") Integer limit);
}
