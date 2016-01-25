package com.lucien.spirit.module.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/security/resource")
public class ResourceController {

	@RequestMapping(value = "/tree", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
	public String tree(HttpServletRequest request) {
		// List<Resource> topResourceList = (List<Resource>) request.getSession().getServletContext().getAttribute("topResourceList");
		
		return null;
	}
}
