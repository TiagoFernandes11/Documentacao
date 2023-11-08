package uservices.domain.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CadastroController {
    @Bean
    @GetMapping("/cadastroalarme")
    public ModelAndView cadastroAlarm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("assets/cadastroAlarme.html");
        return modelAndView;
    }

    @Bean
    @GetMapping("/cadastrocliente")
    public ModelAndView cadastroClient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("assets/cadastroCliente.html");
        return modelAndView;
    }
}
