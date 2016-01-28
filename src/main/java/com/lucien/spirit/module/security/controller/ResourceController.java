package com.lucien.spirit.module.security.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.module.security.service.ResourceService;

@Controller
@RequestMapping("/security/resource")
public class ResourceController {
    
    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);
    
    @Autowired
    ResourceService resourceService;

	@RequestMapping("/list")
    public String list(Model model) {
        return "/security/resource/list";
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model, Long pId) {
		Resource parent = null;
		Resource resource = new Resource();
		if (pId == null) {
			parent = new Resource();
			resource.setResType(Resource.TYPE_MENU);
		} else {
			parent = resourceService.findOne(pId);
			if (parent.getHref().equals("/")) {
				resource.setResType(Resource.TYPE_MENU);
			} else {
				resource.setResType(Resource.TYPE_BTN);
			}
		}
        model.addAttribute(resource);
        model.addAttribute("parent", parent);
        return "/security/resource/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@Valid Resource resource, Long pId, BindingResult bindingResult, Model model) {
        log.info("create resource {}", resource);
        if (bindingResult.hasErrors()) {
            log.info("Error:{}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            return "/security/resource/form";
        }
        Resource parent = new Resource(pId);
        resource.setParent(parent);
        resourceService.save(resource);
        resourceService.refreshResourceCache();
        return "redirect:/security/resource/list";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
    	Resource resource = resourceService.findOne(id);
    	Resource parent = resource.getParent();
        model.addAttribute(resource);
        model.addAttribute("parent", parent);
        return "/security/resource/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@Valid Resource resource, Long pId, BindingResult bindingResult, Model model) {
        log.debug("edit resource={}", resource);
        if (bindingResult.hasErrors()) {
            log.warn("validation error={}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            return "security/resource/form";
        }
        Resource parent = new Resource(pId);
        resource.setParent(parent);
        resourceService.save(resource);
        resourceService.refreshResourceCache();
        return "redirect:/security/resource/list";
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model model) {
		log.info("delete resource");
        resourceService.delete(id);
        resourceService.refreshResourceCache();
        return "redirect:/security/resource/list";
    }
}
