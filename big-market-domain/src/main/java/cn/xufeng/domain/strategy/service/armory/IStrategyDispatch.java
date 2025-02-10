package cn.xufeng.domain.strategy.service.armory;

/**
 * @author Xiexufeng @谢旭峰
 * @description
 * @create 2025/2/7 17:53
 */
public interface IStrategyDispatch {
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId,String ruleWeightValue);
}
