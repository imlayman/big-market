package cn.xufeng.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiexufeng @谢旭峰
 * @description 抽奖应答结果
 * @create 2025/2/24 16:14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleResponseDTO {
    // 奖品ID
    private Integer awardId;
    // 排序编号
    private Integer awardIndex;
}
