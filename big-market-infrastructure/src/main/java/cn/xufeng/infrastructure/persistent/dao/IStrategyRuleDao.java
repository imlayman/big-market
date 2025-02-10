package cn.xufeng.infrastructure.persistent.dao;

import cn.xufeng.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xiexufeng @谢旭峰
 * @description 策略规则 Dao
 * @create 2025/2/6 16:00
 */
@Mapper
public interface IStrategyRuleDao {

    List<StrategyRule> queryStrategyRuleList();

    StrategyRule queryStrategyRule(StrategyRule strategyRuleReq);

    String queryStrategyRuleValue(StrategyRule strategyRule);
}
