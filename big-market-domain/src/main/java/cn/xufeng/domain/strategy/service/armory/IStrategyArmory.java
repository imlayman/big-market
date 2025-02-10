package cn.xufeng.domain.strategy.service.armory;

/**
 * @author Xiexufeng @谢旭峰
 * @description 策略装配库（兵工厂），负责初始化策略计算
 * @create 2025/2/7 16:18
 */
public interface IStrategyArmory {

    boolean assembleLotteryStrategy(Long strategyId);

    Integer getRandomAwardId(Long strategyId);

}
