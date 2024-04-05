package com.example.springboot_project_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringbootProject1Controller {

    @Autowired
    private LinkkiRepository linkkiRepo;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/maintenance")
    public String getMaintenance() {
        return "maintenance";
    }

    @PostMapping("/addLinkit")
    public String addLinkit(@RequestParam String linkki, @RequestParam String otsikko, @RequestParam String kuvaus,
            @RequestParam String avainsana) {
        Linkki newLinkki = new Linkki();
        newLinkki.setLinkki(linkki.trim());
        newLinkki.setOtsikko(otsikko.trim());
        newLinkki.setKuvaus(kuvaus.trim());
        newLinkki.setAvainsana(avainsana.trim());
        linkkiRepo.save(newLinkki);
        return "redirect:/listing";
    }

    @GetMapping("/listing")
    public String getLinkit(Model model) {
        model.addAttribute("linkit", linkkiRepo.findAll());
        return "listing";
    }

}