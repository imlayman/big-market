package cn.xufeng.domain.strategy.model.entity;

import lombok.Data;

/**
 * @author Xiexufeng @谢旭峰
 * @description
 * @create 2025/2/8 15:31
 */
@Data
public class RuleMatterEntity {
    /** 用户ID*/
    private String userId;
    /** 策略ID*/
    private Long strategyId;
    /** 抽奖奖品ID - 内部流转使用*/
    private Integer awardId;
    /** 抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】*/
    private String ruleModel;
}
