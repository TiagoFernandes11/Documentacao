package uservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping()
@SpringBootApplication
public class Application {
    @Bean
    @GetMapping()
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("assets/table.html");
        return modelAndView;
    }

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

    @Bean
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("assets/login.html");
        return modelAndView;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}