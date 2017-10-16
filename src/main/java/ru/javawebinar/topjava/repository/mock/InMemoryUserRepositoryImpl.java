package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new User(0, "User1", "user1@gmail.com", "user111", Role.ROLE_USER));
        save(new User(0, "User2", "user2@gmail.com", "user211", Role.ROLE_USER));
        save(new User(0, "User3", "user3@gmail.com", "user311", Role.ROLE_USER));
        save(new User(0, "User4", "user4@gmail.com", "user411", Role.ROLE_USER));
        save(new User(0, "User5", "user5@gmail.com", "user511", Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if(user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return null;

    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> users = repository.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

        users.sort((o1, o2)
                -> o1.getName().compareTo(o2.getName()));

        return users;

    }


    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        User userToReturn = null;
        List<User> users = getAll();
        for (User user: users)
        {
            if (user.getEmail().equalsIgnoreCase(email))
                userToReturn=user;

        }
        return userToReturn;
    }
}
