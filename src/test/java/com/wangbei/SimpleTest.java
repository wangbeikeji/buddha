package com.wangbei;

import com.wangbei.util.GenderEnum;
import org.hibernate.bytecode.instrumentation.spi.LazyPropertyInitializer;
import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Created by yuyidi on 2017/7/4.
 * @desc
 */
public class SimpleTest {

    @Test
    public void testLazy() {
        System.out.println(LazyPropertyInitializer.UNFETCHED_PROPERTY);
    }

    @Test
    public void testSub() {
        BigDecimal b1 = new BigDecimal(-1);
        BigDecimal b2 = new BigDecimal(10);
        System.out.println(b1.divide(b2).doubleValue());
    }

    @Test
    public void testEnum() {
        System.out.println(GenderEnum.FEMALE.ordinal());
    }

    @Test
    public void testJodaTime() throws ParseException {
        String date = DateTime.now().toLocalDateTime().toString();
        System.out.println(date);
    }
}
