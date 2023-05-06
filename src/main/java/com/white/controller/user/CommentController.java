package com.white.controller.user;


import com.white.controller.util.CommonResult;
import com.white.controller.util.StatusCode;
import com.white.controller.util.WebUtil;
import com.white.domain.Comment;
import com.white.domain.User;
import com.white.dto.CommentDTO;
import com.white.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/publishComment")
    public CommonResult<String> publishComment(@RequestBody CommentDTO commentDTO){
        CommonResult<String> commonResult = new CommonResult<>();

        User user = (User) WebUtil.getSession().getAttribute("loginUser");

        if (1 !=user.getState()){
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
            commonResult.setData("评价失败，请消费完成后在进行评价");
        }else {
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentDTO,comment);
            comment.setUserId(user.getId());

            commentService.save(comment);
            commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
            commonResult.setData("评价成功");
        }

        return commonResult;
    }


}
