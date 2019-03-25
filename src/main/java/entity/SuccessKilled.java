package entity;

import java.util.Date;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/21
 * Time: 21:52
 * Description:
 * `seckill_id` BIGINT NOT NULL COMMENT '商品库存id',
 * `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
 * `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识  -1:无效 0:成功 1:已付款',
 * `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 */
public class SuccessKilled {
    private long seckillId;
    private long userPhone;
    private short state;
    private Date createTime;

    //多对一，多个明细记录对应一个商品
    private Seckill seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
