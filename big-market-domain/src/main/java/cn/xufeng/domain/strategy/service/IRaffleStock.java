package cn.xufeng.domain.strategy.service;

import cn.xufeng.domain.strategy.model.valobj.StrategyAwardStockKeyVO;

/**
 * @author Xiexufeng @谢旭峰
 * @description 抽奖库存相关服务，获取库存消耗队列
 * @create 2025/2/20 20:16
 */
public interface IRaffleStock {
    /**
     * 获取奖品库存消耗队列
     *
     * @return 奖品库存Key信息
     * @throws InterruptedException 异常
     */
    StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException;

    /**
     * 更新奖品库存消耗记录
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     */
    void updateStrategyAwardStock(Long strategyId, Integer awardId);

}
