package cn.xufeng.infrastructure.adapter.repository;

import cn.xufeng.domain.strategy.model.entity.StrategyAwardEntity;
import cn.xufeng.domain.strategy.model.entity.StrategyEntity;
import cn.xufeng.domain.strategy.model.entity.StrategyRuleEntity;
import cn.xufeng.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import cn.xufeng.domain.strategy.repository.IStrategyRepository;
import cn.xufeng.infrastructure.persistent.dao.IStrategyAwardDao;
import cn.xufeng.infrastructure.persistent.dao.IStrategyDao;
import cn.xufeng.infrastructure.persistent.dao.IStrategyRuleDao;
import cn.xufeng.infrastructure.persistent.po.Strategy;
import cn.xufeng.infrastructure.persistent.po.StrategyAward;
import cn.xufeng.infrastructure.persistent.po.StrategyRule;
import cn.xufeng.infrastructure.redis.IRedisService;
import cn.xufeng.types.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiexufeng @谢旭峰
 * @description 策略仓储实现
 * @create 2025/2/7 16:24
 */
@Repository
public class StrategyRepository implements IStrategyRepository {
    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyRuleDao strategyRuleDao;

    @Resource
    private IStrategyAwardDao strategyAwardDao;

    @Resource
    private IRedisService redisService;
    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {
        String cacheKey = Constants.Rediskey.STRATEGY_AWARD_KEY + strategyId;
        List<StrategyAwardEntity> strategyAwardEntities = redisService.getValue(cacheKey);
        if(null != strategyAwardEntities && !strategyAwardEntities.isEmpty()) return strategyAwardEntities;
        // 从库中读取数据
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardListByStrategyId(strategyId);
        strategyAwardEntities = new ArrayList<>(strategyAwards.size());
        for(StrategyAward strategyAward : strategyAwards){
            StrategyAwardEntity strategyAwardEntity = StrategyAwardEntity.builder()
                        .strategyId(strategyAward.getStrategyId())
                        .awardId(strategyAward.getAwardId())
                        .awardCount(strategyAward.getAwardCount())
                        .awardCountSurplus(strategyAward.getAwardCountSurplus())
                        .awardRate(strategyAward.getAwardRate())
                        .build();
            strategyAwardEntities.add(strategyAwardEntity);
        }
        redisService.setValue(cacheKey,strategyAwardEntities);
        return strategyAwardEntities;
    }

    @Override
    public void storeStrategyAwardSearchRateTable(String key, Integer rateRange, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTable) {
        // 1.存储抽奖策略范围值，如10000，用于生成10000以内的随机数
        redisService.setValue(Constants.Rediskey.STRATEGY_RATE_RANGE_KEY + key,rateRange.intValue());
        // 2.存储概率查找表
        Map<Integer, Integer> cacheRateTable = redisService.getMap(Constants.Rediskey.STRATEGY_RATE_TABLE_KEY + key);
        cacheRateTable.putAll(shuffleStrategyAwardSearchRateTable);
    }

    @Override
    public int getRateRange(Long strategyId) {
        return getRateRange(String.valueOf(strategyId));
    }

    @Override
    public int getRateRange(String key) {
        return redisService.getValue(Constants.Rediskey.STRATEGY_RATE_RANGE_KEY + key);
    }

    @Override
    public Integer getStrategyAwardAssemble(String key, int rateKey) {
        return redisService.getFromMap(Constants.Rediskey.STRATEGY_RATE_TABLE_KEY + key,rateKey);
    }

    @Override
    public StrategyEntity queryStrategyEntityByStrategyId(Long strategyId) {
        String cacheKey = Constants.Rediskey.STRATEGY_KEY + strategyId;
        StrategyEntity strategyEntity = redisService.getValue(cacheKey);
        if(null != strategyEntity) return strategyEntity;
        Strategy strategy = strategyDao.queryStrategyByStrategyId(strategyId);
        strategyEntity = StrategyEntity.builder()
                .strategyId(strategy.getStrategyId())
                .strategyDesc(strategy.getStrategyDesc())
                .ruleModels(strategy.getRuleModels())
                .build();
        redisService.setValue(cacheKey,strategyEntity);
        return strategyEntity;
    }

    @Override
    public StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel) {
        StrategyRule strategyRuleReq = new StrategyRule();
        strategyRuleReq.setStrategyId(strategyId);
        strategyRuleReq.setRuleModel(ruleModel);
        StrategyRule strategyRuleRes = strategyRuleDao.queryStrategyRule(strategyRuleReq);
        return StrategyRuleEntity.builder()
                .strategyId(strategyRuleRes.getStrategyId())
                .awardId(strategyRuleRes.getAwardId())
                .ruleType(strategyRuleRes.getRuleType())
                .ruleModel(strategyRuleRes.getRuleModel())
                .ruleValue(strategyRuleRes.getRuleValue())
                .ruleDesc(strategyRuleRes.getRuleDesc())
                .build();
    }

    @Override
    public String queryStrategyRuleValue(Long strategyId, String ruleModel) {
        return queryStrategyRuleValue(strategyId,null,ruleModel);
    }

    @Override
    public String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel) {
        StrategyRule strategyRule = new StrategyRule();
        strategyRule.setStrategyId(strategyId);
        strategyRule.setAwardId(awardId);
        strategyRule.setRuleModel(ruleModel);
        return strategyRuleDao.queryStrategyRuleValue(strategyRule);
    }

    @Override
    public StrategyAwardRuleModelVO queryStrategyAwardRuleModel(Long strategyId, Integer awardId) {
        StrategyAward strategyAward = new StrategyAward();
        strategyAward.setStrategyId(strategyId);
        strategyAward.setAwardId(awardId);
        String ruleMoldes = strategyAwardDao.queryStrategyAwardRuleModels(strategyAward);
        return StrategyAwardRuleModelVO.builder().ruleModels(ruleMoldes).build();
    }
}
