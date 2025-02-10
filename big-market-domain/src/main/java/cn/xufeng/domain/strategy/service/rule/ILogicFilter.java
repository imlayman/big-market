package cn.xufeng.domain.strategy.service.rule;

import cn.xufeng.domain.strategy.model.entity.RuleActionEntity;
import cn.xufeng.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @author Xiexufeng @谢旭峰
 * @description 抽奖规则过滤接口
 * @create 2025/2/8 15:30
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {
    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
