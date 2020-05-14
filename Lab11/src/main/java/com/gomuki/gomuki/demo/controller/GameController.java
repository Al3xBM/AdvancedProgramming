package com.gomuki.gomuki.demo.controller;

import com.gomuki.gomuki.demo.models.Game;
import com.gomuki.gomuki.demo.models.Player;
import com.gomuki.gomuki.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GameController {
    @Autowired
    private GameService service;

    @GetMapping
    public ResponseEntity<List<Game>> getGames(){
        List<Game> games = service.getAllGames();
        return new ResponseEntity<List<Game>>(games, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game){
        Game g = service.createPlayer(game);
        return new ResponseEntity<Game>(g, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{name}")
    public ResponseEntity<String> deleteGame(@PathVariable String name){
        Game g = service.findByPlayer(name);
        if( g == null ){
            return new ResponseEntity<String>("Game not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
        } else {
            service.deleteGame(g);
            return new ResponseEntity<String>("Game has been deleted", new HttpHeaders(), HttpStatus.OK);
        }
    }
}
