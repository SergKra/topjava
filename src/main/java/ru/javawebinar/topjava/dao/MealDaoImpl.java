package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by j on 07.10.2017.
 */
public class MealDaoImpl implements MealDao {

    private static AtomicInteger index = new AtomicInteger(6);
    private static Map<Integer, Meal> mealList = new ConcurrentHashMap<>();


    static {

        mealList.putIfAbsent(1, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, 1));
        mealList.putIfAbsent(2, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, 2));
        mealList.putIfAbsent(3, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, 3));
        mealList.putIfAbsent(4, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, 4));
        mealList.putIfAbsent(5, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, 5));
        mealList.putIfAbsent(6, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, 6));
    }

    public Map<Integer, Meal> getMealList() {
        return mealList;
    }

    @Override
    public void add(Meal meal) {
        Integer id = index.incrementAndGet();
        meal.setId(id);
        mealList.putIfAbsent(id, meal);


    }

    @Override
    public void delete(Integer id) {
        mealList.remove(id);
    }

    @Override
    public void update(Meal meal) {
        mealList.replace(meal.getId(), meal);
    }

    @Override
    public Meal get(Integer id) {
        return mealList.getOrDefault(id, null);
    }

    public List<Meal> getAll() {
        return mealList.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

    }
}
