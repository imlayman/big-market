package cn.xufeng.domain.activity.service.rule;

import cn.xufeng.domain.activity.model.entity.ActivityCountEntity;
import cn.xufeng.domain.activity.model.entity.ActivityEntity;
import cn.xufeng.domain.activity.model.entity.ActivitySkuEntity;

/**
 * @author Xiexufeng @谢旭峰
 * @description 下单规则过滤接口
 * @create 2025/3/4 17:30
 */
public interface IActionChain extends IActionChainArmory{
    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
}
