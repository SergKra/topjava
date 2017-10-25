package ru.javawebinar.topjava;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by j on 23.10.2017.
 */
public class CustomTest extends TestWatcher {
    private static final Logger log = LoggerFactory.getLogger(CustomTest.class);
    private  static Map<String, Long> list = new HashMap<>();
    private Date startDate;

    /*
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                Date startDate = new Date();
                base.evaluate();
                Date endDate = new Date();
                Long diff = endDate.getTime()-startDate.getTime();
                list.put(description.toString(), diff);
                log.info("Difference is " + diff);

            }
        };
    }
    */

    @Override
    protected void starting(Description description) {
        startDate = new Date();

    }

    @Override
    protected void finished(Description description) {
        Date finishDate = new Date();
        long diff = finishDate.getTime() - startDate.getTime();
        log.info(description.getMethodName() + " is finished. " + "Difference is " + diff);
        list.put(description.getMethodName(), diff);
    }


    public static void printResults()
    {
        System.out.println();
        System.out.println();
        System.out.println();
        for (Map.Entry<String, Long> pair:list.entrySet())
        {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }


}
