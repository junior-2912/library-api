package com.junior.library.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
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

    private int loansQuantity;


    // Construtor não recebe a lista de Loans, loans é adicionado através de addLoan()
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public int getLoansQuantity() {
        return loansQuantity;
    }

    public void setLoansQuantity(int loansQuantity) {
        this.loansQuantity = loansQuantity;
    }

    public void addActiveLoan() {
        this.loansQuantity++;
    }

    public void removeActiveLoan() {
        this.loansQuantity--;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
