package com.example.demo;

import com.example.demo.repository.CorporationRepository;
import com.example.demo.services.CorporationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CorporationApplicationTest {

    @LocalServerPort
    private int localPort;

    private String BASE_URL;

    @Autowired
    CorporationRepository corporationRepository;

    @Autowired
    CorporationService corporationService;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    void setup() {
        BASE_URL = "http://127.0.0.1:" + localPort + "/corporation";
    }

    @Test
    void testGetAllCorporations() {

        CorporationRepository[] actual = restTemplate.getForObject(BASE_URL, CorporationRepository[].class);
        assertThat(actual.length).as("Should return list")
                .isEqualTo(corporationRepository.findAll().size());

    }
/*

    @Test
    void testAllBooksOfAndrzejPilipiuk() {
        final int noRows = 3;
        int elements = bookRepository.findBooksByAuthor("Andrzej Pilipiuk").size();

        assertThat(elements).as("Should have %d elements in DB", noRows).isEqualTo(noRows);
    }

    @Test
    void testGetAllBooksWithRest() {
        Book[] actual = restTemplate.getForObject(BASE_URL, Book[].class);
        assertThat(actual.length).as("Should return list")
                .isEqualTo(bookRepository.findAll().size());
    }

    @Test
    void testPostBookWithRest() {
        Book book = new Book();
        book.setTitle("Test title");
        book.setAuthor("Test author");
        Book actual = restTemplate.postForObject(BASE_URL, book, Book.class);
        Optional<Long> maxBookId  = bookRepository.findAll().stream()
                .filter(x -> x.getId().isPresent())
                .map(b -> b.getId().get())
                .reduce(Long::max);

        if(maxBookId.isPresent()) {
            Book lastSavedBook = bookRepository.findById(maxBookId.get());

            assertThat(actual.getBookInfo()).as("Recently saved book differs from last read")
                    .isEqualTo(lastSavedBook.getBookInfo());
        } else {
            fail("No book found in repository");
        }
    }

    @Test
    void testPutBookWithRest() {
        Book updatedBook = findInRepositoryByFirstName("Andrzej");
        String newName = "Jędruś";
        updatedBook.setAuthor(updatedBook.getAuthor().replace("Andrzej", newName));
        restTemplate.put(BASE_URL, updatedBook);

        assertThat(updatedBook).as("Shoud return 'Jędruś' as first name")
                .isEqualTo(findInRepositoryByFirstName(newName));
    }

    private Book findInRepositoryByFirstName(String searchName) {
        return bookRepository.findAll()
                .stream()
                .filter(x -> x.getAuthor().startsWith(searchName))
                .findFirst()
                .get();
    }
*/

}
