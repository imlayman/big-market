package cn.xufeng.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiexufeng @谢旭峰
 * @description  抽奖因子
 * @create 2025/2/8 15:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleFactorEntity {
    private String userId;
    private Long strategyId;
}
