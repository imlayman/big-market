package cn.xufeng.domain.strategy.service.armory;

import cn.xufeng.domain.strategy.model.entity.StrategyAwardEntity;
import cn.xufeng.domain.strategy.model.entity.StrategyEntity;
import cn.xufeng.domain.strategy.model.entity.StrategyRuleEntity;
import cn.xufeng.domain.strategy.repository.IStrategyRepository;
import cn.xufeng.types.common.Constants;
import cn.xufeng.types.enums.ResponseCode;
import cn.xufeng.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author Xiexufeng @谢旭峰
 * @description 策略装配库（兵工厂），负责初始化策略计算
 * @create 2025/2/7 16:20
 */
@Slf4j
@Service
public class StrategyArmoryDispatch implements IStrategyArmory,IStrategyDispatch{

    @Resource
    private IStrategyRepository repository;

    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {
        // 1.查询策略配置
        List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);

        // 2.缓存奖品库存【用于decr扣减库存使用】
        for(StrategyAwardEntity strategyAward : strategyAwardEntities){
            Integer awardId = strategyAward.getAwardId();
            Integer awardCount = strategyAward.getAwardCount();
            cacheStrategyAwardCount(strategyId,awardId,awardCount);
        }

        // 3.1 默认装配配置【全量抽奖概率】
        assembleLotteryStrategy(String.valueOf(strategyId),strategyAwardEntities);

        // 3.2权重策略配置 - 适用于 rule_weight 权重规则配置
        StrategyEntity strategyEntity = repository.queryStrategyEntityByStrategyId(strategyId);
        String ruleWeight = strategyEntity.getRuleWeight();
        if(null == ruleWeight) return true;
        StrategyRuleEntity strategyRuleEntity = repository.queryStrategyRule(strategyId,ruleWeight);
        if(null == strategyRuleEntity){
            throw new AppException(ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getCode(),ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getInfo());
        }
        Map<String, List<Integer>> ruleWeightValueMap = strategyRuleEntity.getRuleWeightValue();
        Set<String> keys = ruleWeightValueMap.keySet();
        for(String key : keys){
            List<Integer> ruleWeightValues = ruleWeightValueMap.get(key);
            ArrayList<StrategyAwardEntity> strategyAwardEntitiesClone = new ArrayList<>(strategyAwardEntities);
            strategyAwardEntitiesClone.removeIf(entity -> !ruleWeightValues.contains(entity.getAwardId()));
            assembleLotteryStrategy(String.valueOf(strategyId).concat("_").concat(key),strategyAwardEntitiesClone);
        }


        return true;
    }

    private void cacheStrategyAwardCount(Long strategyId, Integer awardId, Integer awardCount) {
        String cacheKey = Constants.Rediskey.STRATEGY_AWARD_COUNT_KEY + strategyId + Constants.UNDERLINE + awardId;
        repository.cacheStrategyAwardCount(cacheKey,awardCount);
    }

    public void assembleLotteryStrategy(String key,List<StrategyAwardEntity> strategyAwardEntities){
        // 1.获取最小概率值
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        // 2.获取概率值总和
        BigDecimal totalAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3.用 1 % 0.0001 获取概率范围，百分位、千分位、万分位
        BigDecimal rateRange = totalAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);

        // 4.
        ArrayList<Integer> strategyAwardSearchRateTable = new ArrayList<>(rateRange.intValue());
        for(StrategyAwardEntity strategyAward : strategyAwardEntities){
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();

            // 计算出每个概率值需要存放到查找表的数量，循环填充
            for(int i = 0;i < rateRange.multiply(awardRate).setScale(0,RoundingMode.CEILING).intValue();i++){
                strategyAwardSearchRateTable.add(awardId);
            }
        }

        // 5.乱序
        Collections.shuffle(strategyAwardSearchRateTable);

        // 6.
        HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTable = new HashMap<>();
        for(int i = 0;i < strategyAwardSearchRateTable.size();i++){
            shuffleStrategyAwardSearchRateTable.put(i,strategyAwardSearchRateTable.get(i));
        }

        // 7.存储到Redis中
        repository.storeStrategyAwardSearchRateTable(key,shuffleStrategyAwardSearchRateTable.size(),shuffleStrategyAwardSearchRateTable);
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = repository.getRateRange(strategyId);
        return repository.getStrategyAwardAssemble(String.valueOf(strategyId),new SecureRandom().nextInt(rateRange));
    }

    @Override
    public Integer getRandomAwardId(Long strategyId, String ruleWeightValue) {
        String key = String.valueOf(strategyId).concat("_").concat(ruleWeightValue);
        int rateRange = repository.getRateRange(key);
        return repository.getStrategyAwardAssemble(key,new SecureRandom().nextInt(rateRange));
    }

    @Override
    public Boolean subtractionAwardStock(Long strategyId, Integer awardId) {
        String cacheKey = Constants.Rediskey.STRATEGY_AWARD_COUNT_KEY + strategyId + Constants.UNDERLINE + awardId;
        return repository.subtractionAwardStock(cacheKey);
    }
}
