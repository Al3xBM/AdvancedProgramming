package com.gomuki.gomuki.demo.repository;

import com.gomuki.gomuki.demo.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
