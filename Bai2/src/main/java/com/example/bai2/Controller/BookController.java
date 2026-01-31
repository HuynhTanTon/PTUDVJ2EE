package com.example.bai2.Controller;

import com.example.bai2.model.Book;
import com.example.bai2.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET /books → danh sách (books.html)
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    // GET /books/add → form thêm (add-book.html)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    // POST /books/add → xử lý thêm, redirect về /books
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-book";
        }
        bookService.addBook(book);
        redirectAttributes.addFlashAttribute("message", "Đã thêm sách thành công.");
        return "redirect:/books";
    }

    // GET /books/edit/{id} → form sửa (edit-book.html)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sách.");
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        return "edit-book";
    }

    // POST /books/edit/{id} → xử lý cập nhật, redirect về /books
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable int id,
                             @Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            book.setId(id);
            return "edit-book";
        }
        bookService.updateBook(id, book);
        redirectAttributes.addFlashAttribute("message", "Đã cập nhật sách thành công.");
        return "redirect:/books";
    }

    // GET /books/delete/{id} → xóa, redirect về /books
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("message", "Đã xóa sách thành công.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sách để xóa.");
        }
        return "redirect:/books";
    }
}
