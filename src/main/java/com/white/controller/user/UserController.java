package com.white.controller.user;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.white.controller.util.CommonResult;
import com.white.controller.util.StatusCode;
import com.white.controller.util.WebUtil;
import com.white.domain.User;
import com.white.dto.LoginDTO;
import com.white.dto.RegisterDTO;
import com.white.dto.ReturnUserDTO;
import com.white.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    /*
    * 注册功能
    * */
    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody RegisterDTO registerDTO){
        CommonResult<String> commonResult = new CommonResult<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        User user = new User();
        BeanUtils.copyProperties(registerDTO,user);
        String userName = registerDTO.getUserName();
        String password = registerDTO.getPassword();
        queryWrapper.eq("user_name",userName);

        if (userName==null||password==null){
            commonResult.setData("用户名和密码不能为空");
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
        }else if(userService.getBaseMapper().selectOne(queryWrapper)!=null){
            commonResult.setData("用户名已经存在");
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
        }else {
            user.setPassword(SecureUtil.md5(registerDTO.getPassword()));
            userService.save(user);
            commonResult.setData("注册成功");
            commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
            commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());

        }
        return commonResult;
    }

    /*
    * 登录功能
    * */
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody LoginDTO loginDTO){
        String userName = loginDTO.getUserName();
        String password = loginDTO.getPassword();

        CommonResult<String> commonResult = new CommonResult<>();
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("user_name",loginDTO.getUserName());
        String md5Password = SecureUtil.md5(loginDTO.getPassword());
        queryWrapper1.eq("password",md5Password);
        User user = userService.getBaseMapper().selectOne(queryWrapper1);

        if (userName==null||password==null){
            commonResult.setData("用户名和密码不能为空");
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
        }else if (user != null){
            WebUtil.getSession().setAttribute("loginUser",user);
            commonResult.setData("登录成功");
            commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
            commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        }else {
            commonResult.setData("用户名或密码错误");
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
        }
        return commonResult;

    }
    /*
    登出功能
     */
    @GetMapping("/logout")
    public CommonResult<String> logout(){
        CommonResult<String> commonResult = new CommonResult<>();
        WebUtil.getSession().removeAttribute("loginUser");
        commonResult.setData("登出成功");
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        return commonResult;
    }

    @GetMapping("/userDetail")
    public CommonResult<ReturnUserDTO> userDetail(){
        CommonResult<ReturnUserDTO> commonResult = new CommonResult<>();
        ReturnUserDTO returnUser = new ReturnUserDTO();
        User user = (User) WebUtil.getSession().getAttribute("loginUser");
        User user1 = userService.getById(user.getId());
        BeanUtils.copyProperties(user1,returnUser);

        commonResult.setData(returnUser);
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());

        return commonResult;

    }

}
