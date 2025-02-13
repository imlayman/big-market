package cn.xufeng.domain.strategy.model.entity;

import cn.xufeng.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Xiexufeng @谢旭峰
 * @description 策略实体
 * @create 2025/2/7 19:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEntity {
    /** 抽奖策略ID*/
    private Long strategyId;
    /** 抽奖策略描述*/
    private String strategyDesc;
    /** 规则模型 rule_weight,rule_blacklist*/
    private String ruleModels;

    public String[] ruleModels(){
        if(StringUtils.isBlank(ruleModels)) return null;
        return ruleModels.split(Constants.SPLIT);
    }

    public String getRuleWeight(){
        String[] ruleModels = this.ruleModels();
        if(null == ruleModels) return null;
        for(String ruleModel : ruleModels){
            if("rule_weight".equals(ruleModel)) return ruleModel;
        }
        return null;
    }
}
