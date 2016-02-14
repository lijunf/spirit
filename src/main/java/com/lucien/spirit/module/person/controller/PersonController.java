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
    PersonService personService;

    protected static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) Integer page, Model model, Person person) {
        int pageNumber = page != null ? page : PageConstants.DEFAULT_PAGE_NUM;
        Page<Person> paging = personService.findAllForPagination(pageNumber, PageConstants.DEFAULT_PAGE_SIZE, person);
        model.addAttribute("paging", paging);
        model.addAttribute("person", person);
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
        logger.info("create person {}", person);
        if (bindingResult.hasErrors()) {
            logger.info("Error:{}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            return "/person/form";
        }
        personService.save(person);
        return "redirect:/person/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") String id, Model model) {
        Person person = personService.findOne(id);
        model.addAttribute(person);
        return "/person/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@Valid Person person, BindingResult bindingResult, Model model) {
        logger.debug("edit person={}", person);
        if (bindingResult.hasErrors()) {
            logger.warn("validation error={}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            return "/person/form";
        }
        this.personService.save(person);
        return "redirect:/person/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) {

        this.personService.delete(id);
        return "redirect:/person/list";
    }

}
