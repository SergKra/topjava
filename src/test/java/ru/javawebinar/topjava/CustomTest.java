package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by j on 23.10.2017.
 */
public class CustomTest implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(CustomTest.class);
    public static Map<String, Long> list = new HashMap<>();
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

    public Map<String, Long> getList() {
        return list;
    }
}
