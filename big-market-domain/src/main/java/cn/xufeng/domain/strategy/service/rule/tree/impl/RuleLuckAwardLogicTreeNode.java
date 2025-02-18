package cn.xufeng.domain.strategy.service.rule.tree.impl;

import cn.xufeng.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.xufeng.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.xufeng.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Xiexufeng @谢旭峰
 * @description 兜底奖励节点
 * @create 2025/2/18 17:25
 */
@Slf4j
@Component("rule_luck_award")
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long StrategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                        .awardId(101)
                        .awardRuleValue("1,100")
                        .build())
                .build();
    }
}
