package br.com.aquiris.controller;

import br.com.aquiris.core.Database;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/redis")
@CrossOrigin(origins = {"*"})
public class Controller {

    @GetMapping(value = {"/get/{key}"}, name = "GET")
    public ResponseEntity<String> get(@PathVariable("key") final String key) {
        return ResponseEntity.ok(Database.get(key));
    }

    @PutMapping(value = {"/zadd/{key}/{score}/{value}"}, name = "ZADD")
    public ResponseEntity<String> zadd(@PathVariable("key") final String key, @PathVariable("score") final int score,
                                       @PathVariable("value") final String value) {
        Database.zadd(key, score, value);
        return ResponseEntity.ok("1");
    }

    @GetMapping(value = {"/dbsize"}, name = "DBSIZE")
    public ResponseEntity<Integer> dbsize() {
        return ResponseEntity.ok(Database.getDBSize());
    }

    @PutMapping(value = {"/set/{key}/{value}"}, name = "SET")
    public ResponseEntity<String> set(@PathVariable("key") String key, @PathVariable("value") String value) {
        Database.set(key, value);
        return ResponseEntity.ok("Ok");
    }

    @DeleteMapping(value = {"/del/{key}/"}, name = "DEL")
    public ResponseEntity<Integer> del(@PathVariable("key") String... key) {
        final Integer count = Arrays.asList(key)
                .stream()
                .map(Database::del)
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .size();
        return ResponseEntity.ok(count);
    }

    @PutMapping(value = {"/incr/{key}/"}, name = "INCR")
    public ResponseEntity<Integer> incr(@PathVariable("key") String key) {
        final Integer result = Integer.valueOf(Database.incr(key));
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = {"/zcard/{key}/"}, name = "INCR")
    public ResponseEntity<String> zcard(@PathVariable("key") String key) {
        //TODO
        return ResponseEntity.ok(null);
    }

}
