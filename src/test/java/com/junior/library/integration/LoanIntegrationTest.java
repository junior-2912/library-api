package com.junior.library.integration;

import com.junior.library.dto.LoanRequestDTO;
import com.junior.library.entities.Book;
import com.junior.library.entities.Loan;
import com.junior.library.entities.User;
import com.junior.library.repositories.BookRepository;
import com.junior.library.repositories.LoanRepository;
import com.junior.library.repositories.UserRepository;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.net.UnknownServiceException;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class LoanIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save_shouldPersistLoanWhenRequestIsValid() throws Exception {
        User user = userRepository.save(new User("User Test", "testing@gmail.com"));
        Book book = bookRepository.save(new Book("98700000123", "Book for Test", "Test Author"));

        LoanRequestDTO loanRequestDTO = new LoanRequestDTO(user.getId(), book.getId(), LocalDate.of(2027, 12, 25));
        String json = objectMapper.writeValueAsString(loanRequestDTO);

        mockMvc.perform(post("/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());

            assertThat(loanRepository.count()).isEqualTo(1);
    }
}