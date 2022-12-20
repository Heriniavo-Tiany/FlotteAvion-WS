package com.avion.flotteavion.controller;

import com.avion.flotteavion.models.User;
import com.avion.flotteavion.models.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

//@SpringBootApplication
@CrossOrigin
@RestController
public class UserApplication {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @GetMapping("/users")
    public Object getUser(@RequestParam String email, @RequestParam String pwd) {
        String query = String.format("SELECT * FROM utilisateur WHERE email = '%s' and pwd = md5('%s')::varchar", email, pwd);

        try {
            return jdbcTemplate.query(
                    query,
                    (rs, rowNum) -> new User(rs.getString("id"), rs.getString("name"), rs.getString("email"))
            ).get(0);
        } catch (IndexOutOfBoundsException e) {
            return new Error(404, "Email & Mot de passe non correspondant");
        }


    }

    @PostMapping("/users")
    public void insertUser(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "pwd") String pwd) {
        String query = String.format("INSERT INTO utilisateur(name, email, pwd) VALUES ('%s','%s', sha224('%s'))", name, email, pwd);
        jdbcTemplate.batchUpdate(query);
    }

    @DeleteMapping("/users/{id}")
    @ResponseBody
    public Object deleteUser(@PathVariable String id) {
        String query = String.format("DELETE FROM utilisateur WHERE id='%s'", id);

        if (jdbcTemplate.batchUpdate(query)[0] !=0)
            return "User " + id + " deleted  successfully";
        else
            return new Error(1312, "User not deleted");
    }

}
