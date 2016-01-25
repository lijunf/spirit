package com.lucien.spirit.module.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSON;
import com.lucien.spirit.core.shiro.authc.PasswordHelper;
import com.lucien.spirit.module.security.model.User;
import com.lucien.spirit.module.security.service.UserService;

@Controller
@RequestMapping("/security/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "message", required = false) String message, Model model) {
        int pageNumber = page != null ? page : 0;
        Page<User> pageUser = userService.findAllForPagination(pageNumber, 10);
        model.addAttribute("pageUser", pageUser);
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "/security/user/list";
    }

    @RequestMapping("/create")
    public ModelAndView create(@Valid User user, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
            log.info("Error:{}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            // TODO 处理异常
        }
    	user = PasswordHelper.generatePassword(user);
        userService.save(user);
        Map<String, String> map = new HashMap<>();
        String message = "用户 " + user.getName() + " 创建成功!";
        map.put("message", message);
        return new ModelAndView(new RedirectView("list"), map);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String edit(@PathVariable("id") String id) {
        User user = userService.findOne(Long.parseLong(id));
        String json = JSON.toJSONString(user);
        log.info("{}", json);
        return json;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid User user) {
        log.info("edit user!");
        userService.update(user);
        Map<String, String> map = new HashMap<>();
        String message = "用户 " + user.getName() + " 编辑成功!";
        map.put("message", message);
        return new ModelAndView(new RedirectView("list"), map);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable("id") Long id, Model model) {
        log.info("delete successful!");
        userService.delete(id);
    }

}
