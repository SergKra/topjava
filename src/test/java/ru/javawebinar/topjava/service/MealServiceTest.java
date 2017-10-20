package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }
    @Autowired
    private MealService mealService;

    @Autowired
    private DbPopulator dbPopulator;


    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = mealService.get(MEAL_USERID, 100000);
        MealTestData.MATCHER.assertEquals(MEAL_USER,meal);
    }
    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        Meal meal = mealService.get(MEAL_USERID, 100001);
        MealTestData.MATCHER.assertEquals(MEAL_USER,meal);
    }

    @Test
    public void testDelete() throws Exception {
        mealService.delete(MEAL_USERID, 100000);
       MealTestData.MATCHER.assertCollectionEquals(Collections.singletonList(MEAL_USER1), mealService.getAll(100000));
    }
    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        mealService.delete(MEAL_USERID, 100001);
        MealTestData.MATCHER.assertCollectionEquals(Collections.singletonList(MEAL_USER1), mealService.getAll(100000));
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        List<Meal> meal = mealService.getBetweenDates(LocalDate.MIN, LocalDate.parse("2017-10-19"), 100000);
        MealTestData.MATCHER.assertCollectionEquals(Collections.singletonList(MEAL_USER1),meal);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        List<Meal> meal = mealService.getBetweenDateTimes(LocalDateTime.MIN, LocalDateTime.parse("2017-10-19T13:00:00"), 100000);
        MealTestData.MATCHER.assertCollectionEquals(Collections.singletonList(MEAL_USER1),meal);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Meal> mealList = mealService.getAll(100000);
        MealTestData.MATCHER.assertCollectionEquals(mealList, Arrays.asList(MEAL_USER1,MEAL_USER));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal meal = new Meal(MEAL_USER.getId(), MEAL_USER.getDateTime(), MEAL_USER.getDescription(),MEAL_USER.getCalories());
        meal.setCalories(4000);
        mealService.update(meal,100000);
        MealTestData.MATCHER.assertEquals(meal,mealService.get(MEAL_USERID,100000));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        Meal meal = new Meal(MEAL_USER.getId(), MEAL_USER.getDateTime(), MEAL_USER.getDescription(),MEAL_USER.getCalories());
        meal.setCalories(4000);
        mealService.update(meal,100001);
        MealTestData.MATCHER.assertEquals(meal,mealService.get(MEAL_USERID,100000));
    }

    @Test
    public void testSave() throws Exception {
            Meal meal = new Meal(null,LocalDateTime.now(),"testest", 1500 );
        mealService.save(meal,100000);
        MealTestData.MATCHER.assertCollectionEquals(mealService.getAll(100000), Arrays.asList(meal,MEAL_USER1,MEAL_USER));
    }
    @Test(expected = DataAccessException.class)
    public void testSaveDuplicate() throws Exception {
        Meal meal = new Meal(null,LocalDateTime.parse("2017-10-19T13:00:00"),"testest", 1500 );
        mealService.save(meal,100000);
        MealTestData.MATCHER.assertCollectionEquals(mealService.getAll(100000), Arrays.asList(meal,MEAL_USER1,MEAL_USER));
    }

}