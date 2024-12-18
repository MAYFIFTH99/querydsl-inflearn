package jpa.querydsl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hello {

    @Id @GeneratedValue
    private Long id;

    private String message;

    public Hello() {
    }

    public Hello(String message) {
        this.message = message;
    }
}
