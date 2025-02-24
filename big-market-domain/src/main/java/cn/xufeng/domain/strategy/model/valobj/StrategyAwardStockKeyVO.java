package cn.xufeng.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiexufeng @谢旭峰
 * @description 策略奖品库存Key标识值对象
 * @create 2025/2/20 19:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {
    //策略ID
    private Long strategyId;
    //奖品ID
    private Integer awardId;
}
