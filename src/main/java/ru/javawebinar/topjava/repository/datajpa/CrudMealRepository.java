package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {




    @Transactional
    @Modifying
    @Query(name = Meal.DELETE)
    int delete(int id, int userId);


    //@Transactional
  //  Meal save(Meal meal, int userId);


    @Query(name = Meal.BY_MEALID)
    Meal findOne(int id, int userId);


    //@Query(name = Meal.ALL_SORTED)
    List<Meal> findByUserIdOrderByDateTimeDesc( int userId);


    //@Query(name = Meal.GET_BETWEEN)
    List<Meal> findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(Integer userId,LocalDateTime startDate, LocalDateTime endDate );


    @Query(name = Meal.GET_USER)
    //@EntityGraph(value = "user.details", type = EntityGraph.EntityGraphType.FETCH)
    Meal findOneAndFetchUserEagerly (int id, int userId);

}
