package cn.xufeng.domain.activity.service.rule.impl;

import cn.xufeng.domain.activity.model.entity.ActivityCountEntity;
import cn.xufeng.domain.activity.model.entity.ActivityEntity;
import cn.xufeng.domain.activity.model.entity.ActivitySkuEntity;
import cn.xufeng.domain.activity.model.valobj.ActivitySkuStockKeyVO;
import cn.xufeng.domain.activity.repository.IActivityRepository;
import cn.xufeng.domain.activity.service.armory.IActivityDispatch;
import cn.xufeng.domain.activity.service.rule.AbstractActionChain;
import cn.xufeng.types.enums.ResponseCode;
import cn.xufeng.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 商品库存规则节点
 * @create 2024-03-23 10:25
 */
@Slf4j
@Component("activity_sku_stock_action")
public class ActivitySkuStockActionChain extends AbstractActionChain {
    @Resource
    private IActivityDispatch activityDispatch;
    @Resource
    private IActivityRepository activityRepository;

    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        log.info("活动责任链-商品库存处理【校验&扣减】开始。");
        // 扣减库存
        boolean status = activityDispatch.subtractionActivitySkuStock(activitySkuEntity.getSku(),activityEntity.getEndDateTime());

        if (status) {
            log.info("活动责任链-商品库存处理【有效期、状态、库存(sku)】成功。sku:{} activityId:{}", activitySkuEntity.getSku(), activityEntity.getActivityId());
            // 写入延迟队列，延迟消费更新库存记录
            activityRepository.activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO.builder()
                    .sku(activitySkuEntity.getSku())
                    .activityId(activityEntity.getActivityId())
                    .build());

            return true;
        }
        throw new AppException(ResponseCode.ACTIVITY_SKU_STOCK_ERROR.getCode(), ResponseCode.ACTIVITY_SKU_STOCK_ERROR.getInfo());
    }

}
