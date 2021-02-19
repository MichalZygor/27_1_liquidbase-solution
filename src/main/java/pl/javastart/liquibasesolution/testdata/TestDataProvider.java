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
import java.util.concurrent.ThreadLocalRandom;

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
        for(User userInt : users){
//            long minDay = LocalDate.of(1970, LocalDate.now().getMonthValue(), 1).toEpochDay();
//            long maxDay = LocalDate.of(2015, LocalDate.now().getMonthValue(), 31).toEpochDay();
            long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
            long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
            userInt.setBirthday(randomDate);
        }
        userRepository.saveAll(users);
    }
}
