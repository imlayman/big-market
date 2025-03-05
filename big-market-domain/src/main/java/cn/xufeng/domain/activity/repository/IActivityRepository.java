package cn.xufeng.domain.activity.repository;

import cn.xufeng.domain.activity.model.aggregate.CreateOrderAggregate;
import cn.xufeng.domain.activity.model.entity.ActivityCountEntity;
import cn.xufeng.domain.activity.model.entity.ActivityEntity;
import cn.xufeng.domain.activity.model.entity.ActivitySkuEntity;
import cn.xufeng.domain.activity.model.valobj.ActivitySkuStockKeyVO;

import java.util.Date;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 活动仓储接口
 * @create 2024-03-16 10:31
 */
public interface IActivityRepository {

    ActivitySkuEntity queryActivitySku(Long sku);

    ActivityEntity queryRaffleActivityByActivityId(Long activityId);

    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);

    void doSaveOrder(CreateOrderAggregate createOrderAggregate);

    void cacheActivitySkuStockCount(String cachekey, Integer stockCount);

    boolean subtractionActivitySkuStock(Long sku, String cachekey, Date endDateTime);

    void activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO build);

    ActivitySkuStockKeyVO takeQueueValue();

    void clearQueueValue();

    void updateActivitySkuStock(Long sku);

    void clearActivitySkuStock(Long sku);
}
