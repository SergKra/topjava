package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_USERID = START_SEQ+2;
    public static final int MEAL_ADMINID = START_SEQ+3;
    public static final int MEAL_USERID1 = START_SEQ+4;

    public static final LocalDateTime MEAL_DATE = LocalDateTime.parse("2017-10-20T10:00:00");
    public static final LocalDateTime MEAL_DATE1 = LocalDateTime.parse("2017-10-19T13:00:00");

    public static final Meal MEAL_USER = new Meal(MEAL_USERID, MEAL_DATE, "test", 1000);
    public static final Meal MEAL_USER1 = new Meal(MEAL_USERID1, MEAL_DATE1, "test", 1000);
    public static final Meal MEAL_ADMIN = new Meal(MEAL_ADMINID, LocalDateTime.now(), "test", 500);

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>();

}
