package org.skyline.example.testing.repository;

import org.skyline.example.testing.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGameRepository extends JpaRepository<Game, Long> {}
