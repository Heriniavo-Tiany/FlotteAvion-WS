package com.avion.flotteavion.controller;

import com.avion.flotteavion.models.*;

import com.avion.flotteavion.models.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SpringBootApplication
@RestController
@CrossOrigin("*")
public class AvionApplication {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin
    @GetMapping("/assurances/{months}")
    public List<Avion> getAssurances(@PathVariable String months) {
        String query = String.format("SELECT * FROM v_assurance WHERE months = %s", months);

        return jdbcTemplate.query(
                query,
                (rs, rowNum) -> new Avion(rs.getString("id"), rs.getInt("model"), rs.getString("number"), rs.getString("photo")));
    }

    @CrossOrigin
    @GetMapping("/avions/{id}")
    public List<Avion> getAvionById(@PathVariable String id) {
        String query = String.format("SELECT * FROM avion WHERE id = '%s'", id);

        return jdbcTemplate.query(
                query,
                (rs, rowNum) -> new Avion(rs.getString("id"), rs.getInt("model"), rs.getString("number"), rs.getString("photo")));
    }

    @CrossOrigin
    @GetMapping("/avions")
    public List<Avion> getAvions() {
        return jdbcTemplate.query(
                "SELECT * FROM avion",
                (rs, rowNum) -> new Avion(rs.getString("id"), rs.getInt("model"), rs.getString("number"), rs.getString("photo")));
    }

    @PostMapping("/avions")
    public void insertAvions(@RequestParam(value = "model") String model, @RequestParam(value = "number") String number, @RequestParam(value = "photo") String photo) {
        String query = String.format("INSERT INTO avion VALUES ( concat('AV', nextval('s_avion')), %s,'%s','%s')", model, number, photo);
        jdbcTemplate.batchUpdate(query);
    }

    @DeleteMapping("/avions/{id}")
    @ResponseBody
    public Object deleteAvion(@PathVariable String id) {
        String query = String.format("DELETE FROM avion WHERE id='%s'", id);
        if (jdbcTemplate.batchUpdate(query)[0] != 0)
            return "avions " + id + " deleted  successfully";
        else
            return new Error(1312, "avions not deleted");
    }

}
