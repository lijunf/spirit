package com.lucien.spirit.module.security.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.lucien.spirit.core.shiro.realm.JpaRealm;
import com.lucien.spirit.module.security.model.Role;
import com.lucien.spirit.module.security.model.User;
import com.lucien.spirit.module.security.service.RoleService;
import com.lucien.spirit.module.security.service.UserService;

@Controller
@RequestMapping("/security/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    JpaRealm jpaRealm;

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
    	String message = null;
    	Map<String, String> map = new HashMap<>();
    	User temp = userService.findUserByName(user.getName());
    	if (temp != null && temp.getId() != null) {
    		message = "用户 " + user.getName() + " 已经存在!";
    	} else {
    		user = PasswordHelper.generatePassword(user);
            userService.save(user);
            message = "用户 " + user.getName() + " 创建成功!";
    	}
        map.put("message", message);
        return new ModelAndView(new RedirectView("list"), map);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String edit(@PathVariable("id") Long id) {
        User user = userService.findOne(id);
        String json = JSON.toJSONString(user);
        log.info("{}", json);
        return json;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid User user) {
    	String message = null;
    	Map<String, String> map = new HashMap<>();
    	User temp = userService.findUserByName(user.getName());
    	if (temp != null && temp.getId() != user.getId()) {
    		message = "用户 " + user.getName() + " 已经存在!";
    	} else {
    		userService.update(user);
    		message = "用户 " + user.getName() + " 编辑成功!";
    	}
        map.put("message", message);
        return new ModelAndView(new RedirectView("list"), map);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model model) {
        userService.delete(id);
        return "redirect:/security/user/list";
    }
    
    @RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
    public String grant(Model model, @PathVariable("id") Long id) {
        User user = userService.findOne(id);
        model.addAttribute(user);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "/security/user/grant";
    }

    @RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
    public String grant(Model model, Long[] roleId, @PathVariable("id") Long id) {
    	if (roleId != null && roleId.length > 0) {
    		Set<Role> roles = new HashSet<Role>();
    		for (Long rId : roleId) {
    			Role role = new Role();
    			role.setId(rId);
    			roles.add(role);
    		}
    		User user = userService.findOne(id);
    		user.setRoles(roles);
    		userService.save(user);
    		jpaRealm.clearAllCachedAuthorizationInfo();
    	}
    	return "redirect:/security/user/list";
    }
}
