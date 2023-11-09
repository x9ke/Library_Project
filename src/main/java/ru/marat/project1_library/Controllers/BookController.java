package ru.marat.project1_library.Controllers;

import com.sun.tools.javac.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.marat.project1_library.Dao.BookDAO;
import ru.marat.project1_library.Dao.PersonDAO;
import ru.marat.project1_library.Models.Book;
import ru.marat.project1_library.Models.Person;

import java.util.ArrayList;

@Controller
@RequestMapping("/books")
@PropertySource("classpath:database.properties")
public class BookController {

    public final BookDAO bookDAO;
    public final PersonDAO personDAO;


      @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
          this.personDAO = personDAO;
      }
    @GetMapping()
    public String index(Model model){
        //получим всех лучдей из DAO  и передадим и отобразим в представление
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int bookId, Model model,@ModelAttribute("person") Person person){

        model.addAttribute("book", bookDAO.show(bookId));
        model.addAttribute("people", personDAO.index());


        return "books/show";

    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book , BindingResult bindingResult){


        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";

    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";

    }

    @PatchMapping("/{id}")
    public  String update(@ModelAttribute("book") @Valid Book book ,BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }


}
