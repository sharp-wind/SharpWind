package com.example.guestbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    public void upateContent(String content) {
        this.content = content;
    }
}
