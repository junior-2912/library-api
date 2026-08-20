package com.junior.library.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "library_user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;



    @JsonIgnore
    @OneToMany(mappedBy = "user")
    // ORM feito usando a coluna user da classe Loan, pois uma lista não consegue guardar uma PK
    private List<Loan> loans = new ArrayList<>();

    // Contrutor não recebe a lista de Loans, loans é adicionado através de addLoan()
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public int getLoanQuantity() {
        return loans.size();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public boolean addLoan(Loan loan) {
        return loans.add(loan);
    }

    public boolean removeLoan(Loan loan) {
        return loans.remove(loan);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
