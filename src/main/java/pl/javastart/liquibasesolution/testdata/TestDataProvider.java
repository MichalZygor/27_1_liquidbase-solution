package pl.javastart.liquibasesolution.testdata;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.javastart.liquibasesolution.user.User;
import pl.javastart.liquibasesolution.user.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class TestDataProvider {
    private final UserRepository userRepository;

    public TestDataProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void insertTestData(){
        Random random = new Random();
        List<User> users = userRepository.findAll();
        int localDateMonthNow = LocalDate.now().getMonthValue();
        System.out.println(localDateMonthNow);
        for(User userInt : users){
            LocalDate randomDate = LocalDate.of(getRandomNumberUsingInts(1970,2015),
                    localDateMonthNow, getRandomNumberUsingInts(1,28));
            userInt.setBirthday(randomDate);
        }
        userRepository.saveAll(users);
    }

    private int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
