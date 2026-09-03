package com.junior.library.dto;

public class UserRankingDTO {
    private Long id;
    private String name;
    private Long totalLoans;

    public UserRankingDTO(Long id, String name, Long totalLoans) {
        this.id = id;
        this.name = name;
        this.totalLoans = totalLoans;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(Long totalLoans) {
        this.totalLoans = totalLoans;
    }
}
