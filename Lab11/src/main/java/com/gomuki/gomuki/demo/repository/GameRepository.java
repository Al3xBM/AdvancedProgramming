package com.gomuki.gomuki.demo.repository;

import com.gomuki.gomuki.demo.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
