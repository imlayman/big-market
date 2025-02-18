package cn.xufeng.domain.strategy.service.rule.tree.impl;

import cn.xufeng.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.xufeng.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.xufeng.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Xiexufeng @谢旭峰
 * @description 库存节点
 * @create 2025/2/18 17:27
 */
@Slf4j
@Component("rule_stock")
public class RuleStockLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long StrategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();
    }
}
