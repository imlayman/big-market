package cn.xufeng.domain.strategy.service.rule.chain;

/**
 * @author Xiexufeng @谢旭峰
 * @description 装配接口
 * @create 2025/2/13 17:40
 */
public interface ILogicChainArmory {
    ILogicChain appendNext(ILogicChain next);

    ILogicChain next();
}
