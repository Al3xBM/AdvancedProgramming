package com.gomuki.gomuki.demo.controller;

import com.gomuki.gomuki.demo.models.Player;
import com.gomuki.gomuki.demo.service.PlayerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/players")
public class PlayerController {
    @Autowired
    private PlayerService service;

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers(){
        List<Player> players = service.getAllPlayers();
        return new ResponseEntity<List<Player>>(players, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player){
        Player p = service.createPlayer(player);
        return new ResponseEntity<Player>(p, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{oldName}")
    public ResponseEntity<String> changeName(@PathVariable String oldName, @RequestBody String newName){
        Player p = service.findByName(oldName);
        if( p == null ){
            return new ResponseEntity<String>("Player not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
        } else {
            Player p2 = p;
            p.setName(newName);
            service.changePlayer(p2, p);
            return new ResponseEntity<String>("Player name changed", new HttpHeaders(), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<String> deletePlayer(@PathVariable String name){
        Player p = service.findByName(name);
        if( p == null ){
            return new ResponseEntity<String>("Player not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
        } else {
            service.deletePlayer(p);
            return new ResponseEntity<String>("Player has beem deleted", new HttpHeaders(), HttpStatus.OK);
        }
    }

}
