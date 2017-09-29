package org.fan.web.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhenglt on 2017/9/22.
 */
@Controller
@RequestMapping("user")
public class UserAction {

    /**
     * 登录验证
     *
     * @return
     */
    @RequestMapping("/login")
    public @ResponseBody
    String login(Model model) {
        System.out.println("sssssssss");
        return "ssss";
    }
}
