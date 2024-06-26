package org.skyline.example.testing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.skyline.example.testing.annotations.UniqueValue;

@Entity
@Table(name = "games")
@Getter @Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private String platform;
}
