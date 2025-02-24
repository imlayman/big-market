package cn.xufeng.domain.strategy.repository;

import cn.xufeng.domain.strategy.model.entity.StrategyAwardEntity;
import cn.xufeng.domain.strategy.model.entity.StrategyEntity;
import cn.xufeng.domain.strategy.model.entity.StrategyRuleEntity;
import cn.xufeng.domain.strategy.model.valobj.RuleTreeVO;
import cn.xufeng.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import cn.xufeng.domain.strategy.model.valobj.StrategyAwardStockKeyVO;

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

    void storeStrategyAwardSearchRateTable(String key, Integer rateRange, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTable);

    Integer getStrategyAwardAssemble(String key, int rateKey);

    int getRateRange(Long strategyId);

    int getRateRange(String key);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, String ruleModel);
    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModel(Long strategyId, Integer awardId);

    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    /**
     * 缓存奖品库存
     * @param cacheKey      key
     * @param awardCount   库存值
     */
    void cacheStrategyAwardCount(String cacheKey, Integer awardCount);

    /**
     * 缓存key、decr方式扣减库存
     * @param cacheKey  缓存Key
     * @return  扣减结果
     */
    Boolean subtractionAwardStock(String cacheKey);

    void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO);

    StrategyAwardStockKeyVO takeQueueValue();

    void upupdateStrategyAwardStock(Long strategyId, Integer awardId);
}
