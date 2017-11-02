package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

/**
 * Created by j on 02.11.2017.
 */
@Controller
@RequestMapping(value = "meals")
public class MealController {

    @Autowired
    private MealService mealService;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String showAllMeal(Model model) {
        List<MealWithExceed> mealList = MealsUtil.getWithExceeded(mealService.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay());
        model.addAttribute("mealList", mealList);

        return "meals";

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String save(Model model) {
        model.addAttribute("meal", new Meal());

        return "mealForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(HttpServletRequest request) {

        String description= request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        Meal meal = new Meal(dateTime,description,calories);
        mealService.create(meal, AuthorizedUser.id());

        return "redirect:/meals/all";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete (@PathVariable("id") int id)
    {
        mealService.delete(id, AuthorizedUser.id());

        return "redirect:/meals/all";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit (@PathVariable("id") int id,  Model model)
    {
        Meal meal = mealService.get(id, AuthorizedUser.id());

        model.addAttribute("meal", meal);


        return "mealFormEdit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest request)
    {
        int id = Integer.parseInt(request.getParameter("id"));
        String description= request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        Meal meal = new Meal(id,dateTime,description,calories);
        mealService.update(meal, AuthorizedUser.id());

        return "redirect:/meals/all";
    }

    @RequestMapping(value = "/getBd", method = RequestMethod.POST)
    public String getMealBetweenDates(HttpServletRequest request, Model model)
    {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        List<MealWithExceed>mealList = MealsUtil.getFilteredWithExceeded(
                mealService.getBetweenDates(
                        startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                        endDate != null ? endDate : DateTimeUtil.MAX_DATE, AuthorizedUser.id()),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay());

        model.addAttribute("mealList", mealList);

        return "meals";
    }


}
