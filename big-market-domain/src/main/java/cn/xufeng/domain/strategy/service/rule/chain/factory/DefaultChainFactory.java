package cn.xufeng.domain.strategy.service.rule.chain.factory;

import cn.xufeng.domain.strategy.model.entity.StrategyEntity;
import cn.xufeng.domain.strategy.repository.IStrategyRepository;
import cn.xufeng.domain.strategy.service.rule.chain.ILogicChain;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Xiexufeng @谢旭峰
 * @description 工厂
 * @create 2025/2/13 17:29
 */
@Service
public class DefaultChainFactory {
    private final Map<String, ILogicChain> logicChainGroup;
    private final IStrategyRepository repository;

    public DefaultChainFactory(Map<String, ILogicChain> logicChainGroup, IStrategyRepository repository) {
        this.logicChainGroup = logicChainGroup;
        this.repository = repository;
    }

    public ILogicChain openLogicChain(Long strategyId){
        StrategyEntity strategy = repository.queryStrategyEntityByStrategyId(strategyId);
        String[] ruleModels = strategy.ruleModels();
        if(null == ruleModels || 0 == ruleModels.length) return logicChainGroup.get("default");
        ILogicChain logicChain = logicChainGroup.get(ruleModels[0]);
        ILogicChain current = logicChain;
        for(int i = 1;i < ruleModels.length;i++){
            ILogicChain nextChain = logicChainGroup.get(ruleModels[i]);
            current = current.appendNext(nextChain);
        }
        current.appendNext(logicChainGroup.get("default"));
        return logicChain;
    }
}
