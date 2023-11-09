package ru.marat.project1_library.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.marat.project1_library.Models.Book;

import java.util.List;
@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<Book> index(){

        return  jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));

    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public void save(Book book){

        jdbcTemplate.update("INSERT INTO Book(book_name, book_author,book_year) VALUES(?, ?,?)", book.getBookName(), book.getBookAuthor(), book.getBookYear());

    }

    public void update(int id, Book updateBook){
        jdbcTemplate.update("UPDATE Book SET book_name=?,book_author=?,book_year=? WHERE book_id=?",updateBook.getBookName(),updateBook.getBookAuthor(),updateBook.getBookYear(),id);

    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);

    }
}
