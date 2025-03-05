package cn.xufeng.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiexufeng @谢旭峰
 * @description
 * @create 2025/3/5 16:48
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySkuStockKeyVO {
    private Long sku;
    private Long activityId;
}
