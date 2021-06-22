package com.luizlacerda.libraryapi.api.resource;

import com.luizlacerda.libraryapi.api.dto.BookDTO;
import com.luizlacerda.libraryapi.model.entity.Book;
import com.luizlacerda.libraryapi.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody BookDTO dto){
        Book entity = Book.builder().author(dto.getAuthor()).title(dto.getTitle()).isbn(dto.getIsbn()).build();
        entity = this.service.save(entity);
        return BookDTO.builder()
                .author(entity.getAuthor())
                .title(entity.getTitle())
                .isbn(entity.getIsbn())
                .id(entity.getId())
                .build();
    }
}
