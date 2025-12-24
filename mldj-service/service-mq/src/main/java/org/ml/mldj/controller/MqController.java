package org.ml.mldj.controller;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.customer.SendNewOrderMessageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MqController {
//    @Autowired
    

    @PostMapping("/message/order/new/sendNewOrderMessageAsync")
    public Result<?> sendNewOrderMessageAsync(SendNewOrderMessageForm form) {

        return Result.success();
    }
}
