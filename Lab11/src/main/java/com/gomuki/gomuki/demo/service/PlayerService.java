package com.gomuki.gomuki.demo.service;

import com.github.javafaker.Faker;
import com.gomuki.gomuki.demo.models.Player;
import com.gomuki.gomuki.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        if (players.size() > 0) {
            return players;
        } else
            return new ArrayList<>();
    }

    public Player createPlayer(Player player){
        player = playerRepository.save(player);
        return player;
    }

    public Player findByName(String name){
        List<Player> players = playerRepository.findAll();
        for( Player p : players ){
            if( name.equals(p.getName() ) ){
                return p;
            }
        }
        return null;
    }

    public void changePlayer(Player pOld, Player pNew ){
        playerRepository.delete(pOld);
        playerRepository.save(pNew);
    }

    public void deletePlayer(Player p ){
        playerRepository.delete(p);
    }
}
