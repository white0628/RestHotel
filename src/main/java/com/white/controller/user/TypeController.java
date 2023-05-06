package com.white.controller.user;


import com.white.controller.util.CommonResult;
import com.white.controller.util.StatusCode;
import com.white.domain.Type;
import com.white.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/typelist")
    public CommonResult<List<Type>> typelist(){
        CommonResult<List<Type>> commonResult = new CommonResult<>();
        List<Type> list = typeService.list();
        commonResult.setData(list);
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        return commonResult;
    }

}
