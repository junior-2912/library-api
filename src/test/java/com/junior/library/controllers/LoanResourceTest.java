package com.junior.library.controllers;


import com.junior.library.dto.LoanRequestDTO;
import com.junior.library.entities.Loan;
import com.junior.library.exceptions.ResourceNotFoundException;
import com.junior.library.services.LoanService;
import com.junior.library.util.LoanCreator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanResource.class)
class LoanResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoanService loanService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save_shouldSaveWhenLoanIsValid() throws Exception {
        Loan loan = LoanCreator.createLoan();

        loan.getUser().setId(100L);
        loan.getBook().setId(100L);

        LoanRequestDTO expectedDTO = new LoanRequestDTO(loan.getUser().getId(), loan.getBook().getId(), loan.getReturnDate());

        String json = objectMapper.writeValueAsString(expectedDTO);
        ArgumentCaptor<LoanRequestDTO> captor = ArgumentCaptor.forClass(LoanRequestDTO.class);
        Mockito.when(loanService.save(Mockito.any(LoanRequestDTO.class))).thenReturn(loan);

        mockMvc.perform(post("/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        Mockito.verify(loanService).save(captor.capture());
        LoanRequestDTO capturedDto = captor.getValue();

        assertEquals(expectedDTO.getUserId(), capturedDto.getUserId());
        assertEquals(expectedDTO.getBookId(), capturedDto.getBookId());
        assertEquals(expectedDTO.getReturnDate(), capturedDto.getReturnDate());
    }



    @Test
    void findById_shouldReturnLoanWhenIdExists() throws Exception {
        Loan loan = LoanCreator.createLoan();
        loan.setId(100L);

        Mockito.when(loanService.findById(100L)).thenReturn(loan);

        mockMvc.perform(get("/loans/{id}", 100L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(100));
    }

    @Test
    void findById_shouldThrowExceptionWhenIdNotExists() throws Exception {
        Loan loan = LoanCreator.createLoan();
        loan.setId(100L);

        Mockito.when(loanService.findById(101L)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/loans/{id}", 101L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}