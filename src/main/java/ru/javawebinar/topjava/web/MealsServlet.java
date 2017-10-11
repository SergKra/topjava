package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by j on 04.10.2017.
 */
public class MealsServlet extends HttpServlet {

    private static final Logger log = getLogger(MealsServlet.class);
    private MealDaoImpl mealDao = new MealDaoImpl();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setCharacterEncoding( "UTF-8" );
        request.setAttribute("local", formatter);

        if (action != null && action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            mealDao.delete(id);


        }
        else if (action != null && action.equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = mealDao.searchById(id);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealsEdit.jsp").forward(request,response);
            return;
        }
        else if (action != null && action.equalsIgnoreCase("add"))
        {
            request.getRequestDispatcher("mealsEdit.jsp").forward(request,response);
            return;
        }
        List<MealWithExceed> list = mealDao.getMealWithExceedsList();
        request.setAttribute("mealList", list);
        request.getRequestDispatcher("meals.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("local", formatter);
        req.setCharacterEncoding( "UTF-8" );
        LocalDateTime date = null;
        Meal meal = null;

                String description = req.getParameter("description");
            int calories = Integer.parseInt(req.getParameter("calories"));
        try {
            date = LocalDateTime.parse(req.getParameter("date"), formatter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String id = req.getParameter("id");
            if (id==null || id.isEmpty()) {
                meal = new Meal(date, description, calories);
                mealDao.add(meal);
            }
            else {
                meal = new Meal(date, description, calories, Integer.parseInt(id));
                mealDao.update(meal);
            }



        List<MealWithExceed> list = mealDao.getMealWithExceedsList();
        req.setAttribute("mealList", list);
        req.getRequestDispatcher("meals.jsp").forward(req,resp);


    }
}
