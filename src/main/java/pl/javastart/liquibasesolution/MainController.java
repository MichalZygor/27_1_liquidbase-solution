package pl.javastart.liquibasesolution;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.liquibasesolution.user.User;
import pl.javastart.liquibasesolution.user.UserRepository;

import java.util.List;

@Controller
public class MainController {
    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "home";
    }

}
