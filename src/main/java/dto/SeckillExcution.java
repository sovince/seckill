package dto;

import entity.SuccessKilled;
import enums.SeckillStatEnum;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/29
 * Time: 20:41
 * Description: 封装秒杀执行结果
 */
public class SeckillExcution {
    private Long seckillId;

    private Integer state;//结果状态

    private String stateInfo;//结果信息

    //秒杀成功的明细对象
    private SuccessKilled successKilled;

    /**
     * 成功时给出所有信息
     *
     * @param seckillId
     * @param seckillStatEnum
     * @param successKilled
     */
    public SeckillExcution(Long seckillId, SeckillStatEnum seckillStatEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    /**
     * 失败就不给明细
     *
     * @param seckillId
     * @param seckillStatEnum
     */
    public SeckillExcution(Long seckillId, SeckillStatEnum seckillStatEnum) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExcution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
