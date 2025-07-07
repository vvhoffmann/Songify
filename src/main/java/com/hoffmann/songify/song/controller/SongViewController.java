package com.hoffmann.songify.song.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SongViewController {

    private Map<Integer, String> database = new HashMap<>(Map.of(
            0, "Shawa Song",
            1, "Arian Song",
            2, "Nicky Song",
            3, "Sabri Song",
            4, "Shakira Song"
    ));

    @GetMapping("/")
    public String home() {
        return "home.html";
    }

    @GetMapping("/view/songs")
    public String songs(Model model) {

        model.addAttribute("database", database);
        return "songs.html";
    }


}