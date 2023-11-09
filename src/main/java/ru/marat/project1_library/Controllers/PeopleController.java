package ru.marat.project1_library.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.marat.project1_library.Dao.PersonDAO;
import ru.marat.project1_library.Models.Person;
import ru.marat.project1_library.Util.PersonValidator;

@Controller
@RequestMapping("/people")
@PropertySource("classpath:database.properties")
public class PeopleController {

    private PersonDAO personDAO;
    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        //получим всех лучдей из DAO  и передадим и отобразим в представление
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id")int personId, Model model){

        model.addAttribute("person", personDAO.show(personId));

        return "people/show";

    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person , BindingResult bindingResult){

        personValidator.validate(person,bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";

    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";

    }

    @PatchMapping("/{id}")
    public  String update(@ModelAttribute("person") @Valid Person person ,BindingResult bindingResult, @PathVariable("id") int id){
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }



}
