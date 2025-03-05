package cn.xufeng.domain.activity.event;

import cn.xufeng.types.event.BaseEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author Xiexufeng @谢旭峰
 * @description 活动sku库存清空消息
 * @create 2025/3/5 16:58
 */
@Component
public class ActivitySkuStockZeroMessageEvent extends BaseEvent<Long>{
    @Value("${spring.rabbitmq.topic.activity_sku_stock_zero}")
    private String topic;

    @Override
    public EventMessage<Long> buildEventMessage(Long sku) {
        return EventMessage.<Long>builder()
                .id(RandomStringUtils.randomNumeric(11))
                .timestamp(new Date())
                .data(sku)
                .build();
    }

    @Override
    public String topic() {
        return topic;
    }
}
