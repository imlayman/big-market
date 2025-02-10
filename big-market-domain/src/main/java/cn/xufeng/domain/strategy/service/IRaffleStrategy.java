package cn.xufeng.domain.strategy.service;

import cn.xufeng.domain.strategy.model.entity.RaffleAwardEntity;
import cn.xufeng.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @author Xiexufeng @谢旭峰
 * @description 抽奖策略接口
 * @create 2025/2/8 15:23
 */
public interface IRaffleStrategy {

    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);
}
