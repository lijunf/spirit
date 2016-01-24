package com.lucien.spirit.module.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lucien.spirit.core.shiro.realm.JpaRealm;
import com.lucien.spirit.module.security.model.Role;
import com.lucien.spirit.module.security.model.enums.Permission;
import com.lucien.spirit.module.security.service.RoleService;

@Controller
@RequestMapping("/security/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    
    @Autowired
    JpaRealm jpaRealm;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);
        List<String> permissionList = roleService.queryAllPermissions();
        model.addAttribute("permissionList", permissionList);
        return "/security/role/list";
    }

    @ModelAttribute
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String initCreate(Model model) {
        List<Permission> permissions = new ArrayList<Permission>();
        for (Permission permission : Permission.values()) {
            permissions.add(permission);
        }
        Collections.sort(permissions);
        model.addAttribute("permissions", permissions);
        return "/security/role/list";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitCreate(Model model, HttpServletRequest request) {
        String name = request.getParameter("roleName");
        String desc = request.getParameter("roleDesc");
        String permissionStr = request.getParameter("permissionStr");
        Role role = new Role();
        role.setName(name);
        role.setDescription(desc);
        String[] permissionArray = permissionStr.split(",");
        // TODO role.setPermissions(Arrays.asList(permissionArray));
        roleService.save(role);
        jpaRealm.clearAllCachedAuthorizationInfo();
        return "redirect:/security/role/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String initEdit(@PathVariable("id") String id, Model model) {
        Role role = roleService.findOne(Long.parseLong(id));
        model.addAttribute("role", role);
        List<Permission> permissions = new ArrayList<Permission>();
        for (Permission permission : Permission.values()) {
            permissions.add(permission);
        }
        Collections.sort(permissions);
        model.addAttribute("permissions", permissions);
        return "/security/role/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submitEdit(Model model, HttpServletRequest request) {

        String id = request.getParameter("id");
        String name = request.getParameter("roleName");
        String desc = request.getParameter("roleDesc");
        String permissionStr = request.getParameter("permissionStr");

        Role role = this.roleService.findOne(Long.parseLong(id));

        role.setName(name);
        role.setDescription(desc);
        String[] permissionArray = permissionStr.split(",");
        List<String> list = Arrays.asList(permissionArray);

        // TODO role.setPermissions(new ArrayList<>(list));
        this.roleService.save(role);
        jpaRealm.clearAllCachedAuthorizationInfo();
        return "redirect:/security/role/list";
    }
}
