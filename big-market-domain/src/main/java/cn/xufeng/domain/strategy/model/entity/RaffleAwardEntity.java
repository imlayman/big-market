package cn.xufeng.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiexufeng @谢旭峰
 * @description 抽奖奖品实体
 * @create 2025/2/8 15:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {
    /** 抽奖奖品ID - 内部流转使用*/
    private Integer awardId;
    /** 奖品配置信息*/
    private String awardConfig;
    /** 奖品顺序号*/
    private Integer sort;
}
