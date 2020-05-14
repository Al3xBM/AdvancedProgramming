package com.gomuki.gomuki.demo.service;

import com.gomuki.gomuki.demo.models.Game;
import com.gomuki.gomuki.demo.models.Player;
import com.gomuki.gomuki.demo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        List<Game> games = gameRepository.findAll();
        if (games.size() > 0) {
            return games;
        } else
            return new ArrayList<>();
    }

    public Game createPlayer(Game game){
        game = gameRepository.save(game);
        return game;
    }

    public Game findByPlayer(String name){
        List<Game> games = gameRepository.findAll();
        for( Game g : games){
            if( name.equals( g.getPlayer1() ) || name.equals( g.getPlayer2() ) ){
                return g;
            }
        }
        return null;
    }


    public void deleteGame(Game g){
        gameRepository.delete(g);
    }
}
