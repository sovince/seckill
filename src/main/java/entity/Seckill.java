package entity;

import javax.xml.crypto.Data;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/21
 * Time: 21:38
 * Description:
 *
 * *      `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
 *      * `name` VARCHAR (120) NOT NULL COMMENT '商品名称',
 *      * `number` INT NOT NULL COMMENT '库存数量',
 *      * `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀开启时间',
 *      *   `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
 *      * `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 */
public class Seckill {

    /**

     */
    private long seckillId;
    private String name;
    private int number;
    private Data startTime;
    private Data endTime;
    private Data createTime;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Data getStartTime() {
        return startTime;
    }

    public void setStartTime(Data startTime) {
        this.startTime = startTime;
    }

    public Data getEndTime() {
        return endTime;
    }

    public void setEndTime(Data endTime) {
        this.endTime = endTime;
    }

    public Data getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Data createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
