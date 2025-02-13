package cn.xufeng.domain.strategy.service.rule.chain.impl;

import cn.xufeng.domain.strategy.service.armory.IStrategyDispatch;
import cn.xufeng.domain.strategy.service.AbstractLogicChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Xiexufeng @谢旭峰
 * @description 兜底
 * @create 2025/2/13 17:10
 */
@Slf4j
@Component("default")
public class DefaultLogicChain extends AbstractLogicChain {
    @Resource
    protected IStrategyDispatch strategyDispatch;

    @Override
    public Integer logic(String userId, Long strategyId) {
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
        return awardId;
    }

    @Override
    protected String ruleModel() {
        return "default";
    }
}
