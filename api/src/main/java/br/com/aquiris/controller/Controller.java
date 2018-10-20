package br.com.aquiris.controller;

import br.com.aquiris.core.Database;
import br.com.aquiris.handler.ResultsHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"*"})
public class Controller {

    @GetMapping(value = {"/get/{key}"}, name = "GET")
    public ResponseEntity<String> get(@PathVariable("key") final String key) {
        final String value = Database.get(key);
        return ResponseEntity.ok(ResultsHandler.handlerResult(value));
    }

    // Em virtude do tempo hábil para realização do desafio, optei por criar apenas o endpoint para apenas uma inserção
    @PutMapping(value = {"/zadd/{key}/{score}/{value}"}, name = "ZADD")
    public ResponseEntity<String> zadd(@PathVariable("key") final String key, @PathVariable("score") final int score,
                                       @PathVariable("value") final String value) {
        Database.zadd(key, score, value);
        return ResponseEntity.ok("1");
    }

    @GetMapping(value = {"/dbsize"}, name = "DBSIZE")
    public ResponseEntity<Integer> dbsize() {
        return ResponseEntity.ok(Database.getDBSIZE());
    }

    @PutMapping(value = {"/set/{key}/{value}"}, name = "SET")
    public ResponseEntity<String> set(@PathVariable("key") String key, @PathVariable("value") String value) {
        Database.set(key, value);
        return ResponseEntity.ok("Ok");
    }

    @PutMapping(value = {"/setEX/{key}/{value}/{expiration}"}, name = "SETEX")
    public ResponseEntity<String> setEX(@PathVariable("key") String key,
                                            @PathVariable("value") String value,
                                            @PathVariable("expiration") String expiration) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not Implemented");
    }

    @DeleteMapping(value = {"/del/{key}/"}, name = "DEL")
    public ResponseEntity<Integer> del(@PathVariable("key") String... key) {
        final Integer count = Database.delCount(key);
        return ResponseEntity.ok(count);
    }

    @PutMapping(value = {"/incr/{key}/"}, name = "INCR")
    public ResponseEntity<Integer> incr(@PathVariable("key") String key) {
        final Integer result = Integer.valueOf(Database.incr(key));
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = {"/zcard/{key}/"}, name = "ZCARD")
    public ResponseEntity<Integer> zcard(@PathVariable("key") String key) {
        final Integer result = Database.getZCARD(key);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = {"/zrank/{key}/{value}"}, name = "ZRANK")
    public ResponseEntity<String> zrank(@PathVariable("key") String key, @PathVariable("value") final String value) {
        final Integer result = Database.getZRANK(key, value);
        return ResponseEntity.ok(ResultsHandler.handlerResult(result));
    }

    @GetMapping(value = {"/zrange/{key}/{start}/{stop}"}, name = "ZRANGE")
    public ResponseEntity<List<String>> zrange(@PathVariable("key") String key,
                                               @PathVariable("start") final int start,
                                               @PathVariable("stop") final int stop) {
        final List<String> result = Database.getZRANGE(key, start, stop);
        return ResponseEntity.ok(result);
    }

}
