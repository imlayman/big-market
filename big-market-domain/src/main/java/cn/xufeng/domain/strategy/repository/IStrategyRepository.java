package cn.xufeng.domain.strategy.repository;

import cn.xufeng.domain.strategy.model.entity.StrategyAwardEntity;
import cn.xufeng.domain.strategy.model.entity.StrategyEntity;
import cn.xufeng.domain.strategy.model.entity.StrategyRuleEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author Xiexufeng @谢旭峰
 * @description 策略仓储接口
 * @create 2025/2/7 16:22
 */
public interface IStrategyRepository {

    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardSearchRateTable(String key, BigDecimal rateRange, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTable);

    Integer getStrategyAwardAssemble(String key, int rateKey);

    int getRateRange(Long strategyId);

    int getRateRange(String key);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);
}
