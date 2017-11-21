package com.zbq.base.jodatime;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author zhangboqing
 * @date 2017/10/23
 */
public class JodaTimeTest {

    /**
     *  1.创建时间对象
     */
    @Test
    public void calendarRun1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
    }
    @Test
    public void jodaTimeRun1() {
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
    }


    /**
     *  2.某一个瞬间加上 90 天并输出结果
     */
    @Test
    public void calendarRun2() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss.SSS");
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        System.out.println(sdf.format(calendar.getTime()));
    }
    @Test
    public void jodaTimeRun2() {
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.plusDays(90).toString("E MM/dd/yyyy HH:mm:ss.SSS"));
    }

    /***
     * 3.距离 Y2K 45 天之后的某天在下一个月的当前周的最后一天的日期
     *   Calendar 处理很麻烦，用Joda-Time相对很容易
     */
    @Test
    public void jodaTimeRun3() {
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek()
                .withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));
    }


    /***
     * 4.Joda-Time 与 JDK 互操作性
     */
    @Test
    public void jodaTimeRun4() {
        Calendar calendar = Calendar.getInstance();
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek()
                .withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));
        calendar.setTime(dateTime.toDate());

        System.out.println(new SimpleDateFormat(" MM/dd/yyyy HH:mm:ss.SSS").format(calendar.getTime()));
    }

    /**
     * 5.构建 DateTime 对象的多种方式
     *  DateTime：这是最常用的一个类。它以毫秒级的精度封装时间上的某个瞬间时刻。
     *  DateTime 始终与 DateTimeZone 相关，如果您不指定它的话，它将被默认设置为运行代码的机器所在的时区。
     *
     *  LocalDate：该类封装了一个年/月/日的组合。当地理位置（即时区）变得不重要时，使用它存储日期将非常方便
     *  LocalTime：这个类封装一天中的某个时间，当地理位置不重要的情况下，可以使用这个类来只存储一天当中的某个时间
     */
    @Test
    public void jodaTimeRun5() {
        //
        DateTime dateTime = new DateTime();
        //
        DateTime dateTime2 = new DateTime(
                2000, //year
                1,    // month
                1,    // day
                0,    // hour (midnight is zero)
                0,    // minute
                0,    // second
                0     // milliseconds
        );

        //
        LocalDate localDate = new LocalDate(2009, 9, 6);// September 6, 2009
        LocalTime localTime = new LocalTime(13, 30, 26, 0);// 1:30:26PM
    }


    /***
     * 6.日期计算
     */
    @Test
    public void jodaTimeRun6() {
        DateTime dateTime = new DateTime();
        DateTime electionDate = dateTime.monthOfYear()
                .setCopy(11)        // November
                .dayOfMonth()       // Access Day Of Month Property
                .withMinimumValue() // Get its minimum value
                .plusDays(6)        // Add 6 days
                .dayOfWeek()        // Access Day Of Week Property
                .setCopy("Monday")  // Set to Monday (it will round down)
                .plusDays(1);       // Gives us Tuesday
    }

    /**
     * 7.格式化时间输出
     */
    @Test
    public void jodaTimeRun7() {
//        SystemFactory.getClock().getDateTime();
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.toString("MM/dd/yyyy hh:mm:ss.SSSa"));
        System.out.println(dateTime.toString("dd-MM-yyyy HH:mm:ss"));
        System.out.println(dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa"));
        System.out.println(dateTime.toString("MM/dd/yyyy HH:mm ZZZZ"));
        System.out.println(dateTime.toString("MM/dd/yyyy HH:mm Z"));
    }

}
