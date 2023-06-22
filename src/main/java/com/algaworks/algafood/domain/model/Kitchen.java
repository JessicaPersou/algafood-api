package com.algaworks.algafood.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Kitchen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kitchen)) return false;
        Kitchen ketchen = (Kitchen) o;
        return Objects.equals(getId(), ketchen.getId()) && Objects.equals(getName(), ketchen.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
