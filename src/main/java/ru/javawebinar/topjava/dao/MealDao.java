package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;


/**
 * Created by j on 07.10.2017.
 */
public interface MealDao {

    public void add(Meal meal);
    public void delete(Integer id);
    public void update (Meal meal);
    public Meal searchById(Integer id);

}
