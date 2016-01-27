package com.lucien.spirit.module.person.controller;

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

import com.lucien.spirit.core.constants.PageConstants;
import com.lucien.spirit.module.person.model.Person;
import com.lucien.spirit.module.person.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService psersonService;

    protected static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) Integer page, Model model) {
        int pageNumber = page != null ? page : PageConstants.DEFAULT_PAGE_NUM;
        Page<Person> paging = psersonService.findAllForPagination(pageNumber, PageConstants.DEFAULT_PAGE_SIZE);
        model.addAttribute("paging", paging);
        return "/person/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        Person person = new Person();
        model.addAttribute(person);
        return "/person/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@Valid Person person, BindingResult bindingResult, Model model) {
        log.info("create person {}", person);
        if (bindingResult.hasErrors()) {
            log.info("Error:{}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            return "/person/form";
        }
        psersonService.save(person);
        return "redirect:/person/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") String id, Model model) {
        Person person = psersonService.findOne(id);
        model.addAttribute(person);
        return "/person/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@Valid Person person, BindingResult bindingResult, Model model) {
        log.debug("edit person={}", person);
        if (bindingResult.hasErrors()) {
            log.warn("validation error={}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            return "person/form";
        }
        this.psersonService.save(person);
        return "redirect:/person/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) {

        this.psersonService.delete(id);
        return "redirect:/person/list";
    }

}
