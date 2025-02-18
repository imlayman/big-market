package cn.xufeng.domain.strategy.service.rule.tree.factory.engine;

import cn.xufeng.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author Xiexufeng @谢旭峰
 * @description 规则树组合接口
 * @create 2025/2/18 17:30
 */
public interface IDecisionTreeEngine {

    DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId);
}
