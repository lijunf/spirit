package com.lucien.spirit.module.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lucien.spirit.core.constants.PageConstants;
import com.lucien.spirit.module.system.model.Log;
import com.lucien.spirit.module.system.service.LogService;

@Controller
@RequestMapping("/system/log")
public class LogController {

    @Autowired
    LogService logService;

    protected static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) Integer page, Model model) {
        int pageNumber = page != null ? page : PageConstants.DEFAULT_PAGE_NUM;
        Page<Log> paging = logService.findAllForPagination(pageNumber, PageConstants.DEFAULT_PAGE_SIZE);
        model.addAttribute("paging", paging);
        return "/system/log/list";
    }

}
