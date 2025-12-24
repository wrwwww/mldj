package org.ml.mldj.rules.service;

import jakarta.validation.Valid;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.ml.mldj.model.dto.CalOrderFeeForm;
import org.ml.mldj.model.dto.CalculateSplitForm;
import org.ml.mldj.model.vo.CalOrderFeeVO;
import org.ml.mldj.model.vo.CalculateSplitVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RulesService {

    @Autowired
    KieContainer kieContainer;

    public CalOrderFeeVO calculateOrderFee(@Valid CalOrderFeeForm form) {
        CalOrderFeeVO result = new CalOrderFeeVO();
        KieSession kieSession = kieContainer.newKieSession("rules-session");
        try {
            kieSession.insert(form);
            kieSession.fireAllRules();
            BeanUtils.copyProperties(form, result);
            // 清除工作内存中的事实
            kieSession.delete(kieSession.getFactHandle(form));
        } finally {
            kieSession.dispose();
        }

        return result;
    }

    public CalculateSplitVO calculateSplit(@Valid CalculateSplitForm form) {
        // todo
        return null;
    }
}
