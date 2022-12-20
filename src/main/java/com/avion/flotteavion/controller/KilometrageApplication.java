package com.avion.flotteavion.controller;

import com.avion.flotteavion.models.Kilometrage;
import com.avion.flotteavion.models.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@SpringBootApplication
@CrossOrigin
@RestController
public class KilometrageApplication {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @GetMapping("/kilometrages")
    public List<Kilometrage> getAvions() {
        return jdbcTemplate.query(
                "SELECT * FROM kilometrage",
                (rs, rowNum) -> new Kilometrage(rs.getString("idavion"), rs.getDate("daty").toString(), rs.getDouble("debut"), rs.getDouble("fin")));
    }

    @PostMapping("/" +
            "")
    public void insertKilometrage(@RequestParam String idAvion, @RequestParam String date, @RequestParam String debut, @RequestParam String fin) {
        String query = String.format("INSERT INTO kilometrage VALUES (%s,'%s', %s, %s)", idAvion, date, debut, fin);
        jdbcTemplate.batchUpdate(query);
    }

    @DeleteMapping("/kilometrages/{id}")
    @ResponseBody
    public Object deleteKilometrage(@PathVariable String id) {
        String query = String.format("DELETE FROM kilometrage WHERE idavion=%s",id);
        if (jdbcTemplate.batchUpdate(query)[0] !=0)
            return "Kilometrage " + id + " deleted  successfully";
        else
            return new Error(1312, "Kilometrage not deleted");
    }

}
