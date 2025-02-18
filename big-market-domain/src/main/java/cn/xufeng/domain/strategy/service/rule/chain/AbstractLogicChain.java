package cn.xufeng.domain.strategy.service.rule.chain;

import cn.xufeng.domain.strategy.service.rule.chain.ILogicChain;

/**
 * @author Xiexufeng @谢旭峰
 * @description
 * @create 2025/2/13 17:07
 */
public abstract class AbstractLogicChain implements ILogicChain {

    private ILogicChain next;

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    @Override
    public ILogicChain next() {
        return next;
    }

    protected abstract String ruleModel();
}
