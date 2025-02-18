package cn.xufeng.domain.strategy.service.rule.tree;

import cn.xufeng.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author Xiexufeng @谢旭峰
 * @description 规则树接口
 * @create 2025/2/18 17:23
 */
public interface ILogicTreeNode {

    DefaultTreeFactory.TreeActionEntity logic(String userId, Long StrategyId, Integer awardId);


}
