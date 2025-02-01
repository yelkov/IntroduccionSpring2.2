package edu.badpals.cambio_moneda.controller;

import edu.badpals.cambio_moneda.model.CambioDTO;
import edu.badpals.cambio_moneda.service.CambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CambioController {

    private List<String> origen;
    private List<String> destino;
    private CambioService cambioService;

    @Autowired
    public CambioController(CambioService cambioService) {
        this.cambioService = cambioService;
        origen = cambioService.getNombresMonedas();
        destino = cambioService.getNombresMonedas();
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("origen", origen);
        model.addAttribute("destino", destino);
        return "index";
    }

    @PostMapping("/cambiar")
    public String cambiar(@RequestParam String base, @RequestParam String destino, @RequestParam String amount, RedirectAttributes redirectAttributes) {
        CambioDTO cambioDTO = cambioService.cambiarMoneda(base, destino, amount);
        redirectAttributes.addFlashAttribute("cambioDTO", cambioDTO);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String result(Model model) {
        return "result";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }
}
