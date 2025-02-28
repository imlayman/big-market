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

    /**
     * 根据规则树ID，查询树结构信息
     *
     * @param treeId 规则树ID
     * @return 树结构信息
     */
    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    /**
     * 缓存奖品库存
     *
     * @param cacheKey   key
     * @param awardCount 库存值
     */
    void cacheStrategyAwardCount(String cacheKey, Integer awardCount);

    /**
     * 缓存key，decr 方式扣减库存
     *
     * @param cacheKey 缓存Key
     * @return 扣减结果
     */
    Boolean subtractionAwardStock(String cacheKey);

    /**
     * 写入奖品库存消费队列
     *
     * @param strategyAwardStockKeyVO 对象值对象
     */
    void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO);

    /**
     * 获取奖品库存消费队列
     */
    StrategyAwardStockKeyVO takeQueueValue();

    /**
     * 更新奖品库存消耗
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     */
    void upupdateStrategyAwardStock(Long strategyId, Integer awardId);

    /**
     * 根据策略ID+奖品ID的唯一值组合，查询奖品信息
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @return 奖品信息
     */
    StrategyAwardEntity queryStrategyAwardEntity(Long strategyId, Integer awardId);
}
