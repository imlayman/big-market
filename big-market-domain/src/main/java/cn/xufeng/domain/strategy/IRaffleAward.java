package cn.xufeng.domain.strategy;

import cn.xufeng.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * @author Xiexufeng @谢旭峰
 * @description 策略奖品接口
 * @create 2025/2/24 16:26
 */
public interface IRaffleAward {
    /**
     * 根据策略ID查询抽奖奖品列表配置
     *
     * @param strategyId 策略ID
     * @return 奖品列表
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);
}
