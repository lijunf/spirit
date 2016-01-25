package com.lucien.spirit.module.security.controller;

import java.util.ArrayList;
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
import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.module.security.model.Role;
import com.lucien.spirit.module.security.service.ResourceService;
import com.lucien.spirit.module.security.service.RoleService;

@Controller
@RequestMapping("/security/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    
    @Autowired
    ResourceService resourceService;
    
    @Autowired
    JpaRealm jpaRealm;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);
        return "/security/role/list";
    }

    @ModelAttribute
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String initCreate(Model model) {
        List<Resource> resources = resourceService.findAll();
        model.addAttribute("resources", resources);
        return "/security/role/list";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitCreate(Model model, HttpServletRequest request) {
        String name = request.getParameter("roleName");
        String desc = request.getParameter("roleDesc");
        String resourceStr = request.getParameter("resourceStr");
        Role role = new Role();
        role.setName(name);
        role.setDescription(desc);
        
        String[] resourceArray = resourceStr.split(",");
        List<Resource> resources = new ArrayList<Resource>();
        for (String resId : resourceArray) {
            Resource resource = new Resource(Long.parseLong(resId));
            resources.add(resource);
        }
        role.setResource(resources);
        roleService.save(role);
        jpaRealm.clearAllCachedAuthorizationInfo();
        return "redirect:/security/role/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String initEdit(@PathVariable("id") String id, Model model) {
        Role role = roleService.findOne(Long.parseLong(id));
        model.addAttribute("role", role);
        List<Resource> resources = resourceService.findAll();
        model.addAttribute("resources", resources);
        return "/security/role/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submitEdit(Model model, HttpServletRequest request) {

        String id = request.getParameter("id");
        String name = request.getParameter("roleName");
        String desc = request.getParameter("roleDesc");
        String resourceStr = request.getParameter("resourceStr");

        Role role = this.roleService.findOne(Long.parseLong(id));

        role.setName(name);
        role.setDescription(desc);
        String[] resourceArray = resourceStr.split(",");
        List<Resource> resources = new ArrayList<Resource>();
        for (String resId : resourceArray) {
            Resource resource = new Resource(Long.parseLong(resId));
            resources.add(resource);
        }
        role.setResource(resources);
        this.roleService.save(role);
        jpaRealm.clearAllCachedAuthorizationInfo();
        return "redirect:/security/role/list";
    }
}
