package com.netcracker.controllers;

import com.netcracker.exceptions.ResourceNotFoundException;
import com.netcracker.models.Book;
import com.netcracker.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    @GetMapping("/books/id")
    public ResponseEntity<Book> getBookById(@RequestParam("id") Long bookId
                                            ) throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/books")
    public Book createBook(@ModelAttribute Book book) {
        return bookRepository.save(book);
    }


    @PutMapping("/books/id")
    public ResponseEntity<Book> updateBook(@RequestParam("id") Long bookID,
                                           @ModelAttribute Book bookDetails) throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookID)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookID));

        book.setStorage(bookDetails.getStorage());
        book.setName(bookDetails.getName());
        book.setCount(bookDetails.getCount());
        book.setCost(bookDetails.getCost());

        final Book updateBook = bookRepository.save(book);
        return ResponseEntity.ok(updateBook);
    }

    @PutMapping("/books/id/name")
    public ResponseEntity<Book> updateNameBook(@RequestParam("id") Long bookID,
                                               @RequestParam("name") String nameBook) throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookID)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookID));
        book.setName(nameBook);
        final Book updateBook = bookRepository.save(book);
        return ResponseEntity.ok(updateBook);
    }

    @PutMapping("/books/id/storage")
    public ResponseEntity<Book> updateStorageBook(@RequestParam("id") Long bookID,
                                               @RequestParam("storage") String storageBook) throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookID)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookID));
        book.setStorage(storageBook);
        final Book updateBook = bookRepository.save(book);
        return ResponseEntity.ok(updateBook);
    }

    @PutMapping("/books/id/cost")
    public ResponseEntity<Book> updateStorageBook(@RequestParam(value = "id") Long bookID,
                                                  @RequestParam(value = "cost") Integer costBook) throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookID)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookID));
        book.setCost(costBook);
        final Book updateBook = bookRepository.save(book);
        return ResponseEntity.ok(updateBook);
    }


    @DeleteMapping("/books/id")
    public Map<String, Boolean> deleteBook(@RequestParam("id") Long bookID)
            throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookID)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookID));

        bookRepository.delete(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("books/distinctName")
    public List<String> getDistinctName(){
        return bookRepository.getDistinctName();
    }

    @GetMapping("books/distinctCost")
    public List<Integer> getDistinctCost(){
        return bookRepository.getDistinctCost();
    }

    @GetMapping("books/getNameAndCost")
    public List<String> getNameAndCost(){
        bookRepository.save(new Book(10,"WINDOWS or Linux",100,"Moscow",10));
        bookRepository.save(new Book(7,"Linux",300000,"Moscow",10));
        return bookRepository.getBooksByNameAndCost();
    }
}

