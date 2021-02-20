package pl.javastart.liquibasesolution;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("user", new User());
        model.addAttribute("users", users);
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addTask(User user) {
        userRepository.save(user);
        return "redirect:/";
    }
}
