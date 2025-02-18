package cn.xufeng.domain.strategy.service.rule.chain;

import cn.xufeng.domain.strategy.service.rule.chain.factory.DefaultChainFactory;

/**
 * @author Xiexufeng @谢旭峰
 * @description 责任链接口
 * @create 2025/2/13 17:03
 */
public interface ILogicChain extends  ILogicChainArmory{
    /**
     * 责任链接口
     * @param userId    用户ID
     * @param strategyId 策略ID
     * @return  奖品ID
     */
    DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId);


}
