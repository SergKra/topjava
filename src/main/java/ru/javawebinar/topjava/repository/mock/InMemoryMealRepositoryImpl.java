package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(AuthorizedUser.id());

        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {
        if (repository.get(id).getUserId() == AuthorizedUser.id())
            return repository.remove(id)!=null;
        else return false;

    }


    @Override
    public Meal get(int id) {
        if (repository.get(id).getUserId() == AuthorizedUser.id())
        return repository.get(id);
        else return null;
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> mealList = repository.entrySet().stream().filter(pair -> pair.getValue().getUserId() == AuthorizedUser.id()).map(Map.Entry::getValue).collect(Collectors.toList());
        mealList.sort((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));
        return mealList;
    }
}

