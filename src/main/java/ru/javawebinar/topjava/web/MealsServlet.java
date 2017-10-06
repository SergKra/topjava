package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by j on 04.10.2017.
 */
public class MealsServlet extends HttpServlet {

    private static final Logger log = getLogger(MealsServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<MealWithExceed> list = MealsUtil.getMealExceeded();
        request.setAttribute("mealList", list);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        request.setAttribute("local", formatter);
        request.getRequestDispatcher("meals.jsp").forward(request,response);
    }
}
