package com.vadims.books.controller;

import com.vadims.books.domain.Book;
import com.vadims.books.service.BookService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    @MockBean
    private BookService bookService;

    @Test
    void getAllBooks() throws Exception {
        // Create a list of books
        List<Book> books = Arrays.asList(
                new Book(1L, "Book 1", "Author 1"),
                new Book(2L, "Book 2", "Author 2")
        );

        // Configure the mock bookService to return the list of books when the findAll method is called
        when(bookService.findAll()).thenReturn(books);

        // Send a GET request to the /books endpoint
        MvcResult result = mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andReturn();

        // Get the response body as a JSON array
        JSONArray jsonResponse = new JSONArray(result.getResponse().getContentAsString());

        // Verify that the response has the correct number of elements
        assertEquals(books.size(), jsonResponse.length());

        // Verify that each element in the response has the correct id, title, author, and publisher fields
        for (int i = 0; i < jsonResponse.length(); i++) {
            JSONObject jsonBook = jsonResponse.getJSONObject(i);
            Book book = books.get(i);
            assertEquals(book.getId(), jsonBook.getLong("id"));
            assertEquals(book.getTitle(), jsonBook.getString("title"));
            assertEquals(book.getAuthor(), jsonBook.getString("author"));
        }
    }
}
