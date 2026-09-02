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
    private String email;

    private int activeLoansQuantity;


    // Construtor não recebe a lista de Loans, loans é adicionado através de addLoan()
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActiveLoansQuantity() {
        return activeLoansQuantity;
    }

    public void setActiveLoansQuantity(int activeLoansQuantity) {
        this.activeLoansQuantity = activeLoansQuantity;
    }

    public void addActiveLoan() {
        this.activeLoansQuantity++;
    }

    public void removeActiveLoan() {
        this.activeLoansQuantity--;
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
