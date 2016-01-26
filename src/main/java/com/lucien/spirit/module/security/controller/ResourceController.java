package com.lucien.spirit.module.security.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lucien.spirit.module.security.model.Resource;
import com.lucien.spirit.module.security.service.ResourceService;

@Controller
@RequestMapping("/security/resource")
public class ResourceController {
    
    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);
    
    @Autowired
    ResourceService resourceService;

	@RequestMapping(value = "/tree", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
	public String tree(HttpServletRequest request) {
		// List<Resource> topResourceList = (List<Resource>) request.getSession().getServletContext().getAttribute("topResourceList");
		
		return null;
	}
	
	@RequestMapping("/list")
    public String list(Model model) {
        List<Resource> resourceList = resourceService.findAll();
        model.addAttribute("resourceList", resourceList);
        return "/security/resource/list";
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable("id") Long id, Model model) {
        log.info("delete successful!");
        resourceService.delete(id);
    }
}
