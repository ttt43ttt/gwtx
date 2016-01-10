package java.util;

import java.util.Date;

public class Calendar {
    public final static int ERA = 0;
    public final static int YEAR = 1;
    public final static int MONTH = 2;
    public final static int WEEK_OF_YEAR = 3;
    public final static int WEEK_OF_MONTH = 4;
    public final static int DATE = 5;
    public final static int DAY_OF_MONTH = 5;
    public final static int DAY_OF_YEAR = 6;
    public final static int DAY_OF_WEEK = 7;
    public final static int DAY_OF_WEEK_IN_MONTH = 8;
    public final static int AM_PM = 9;
    public final static int HOUR = 10;
    public final static int HOUR_OF_DAY = 11;
    public final static int MINUTE = 12;
    public final static int SECOND = 13;
    public final static int MILLISECOND = 14;
    public final static int ZONE_OFFSET = 15;
    public final static int DST_OFFSET = 16;
    public final static int FIELD_COUNT = 17;
    public final static int SUNDAY = 1;
    public final static int MONDAY = 2;
    public final static int TUESDAY = 3;
    public final static int WEDNESDAY = 4;
    public final static int THURSDAY = 5;
    public final static int FRIDAY = 6;
    public final static int SATURDAY = 7;
    public final static int JANUARY = 0;
    public final static int FEBRUARY = 1;
    public final static int MARCH = 2;
    public final static int APRIL = 3;
    public final static int MAY = 4;
    public final static int JUNE = 5;
    public final static int JULY = 6;
    public final static int AUGUST = 7;
    public final static int SEPTEMBER = 8;
    public final static int OCTOBER = 9;
    public final static int NOVEMBER = 10;
    public final static int DECEMBER = 11;
    public final static int UNDECIMBER = 12;
    public final static int AM = 0;
    public final static int PM = 1;
    public static final int ALL_STYLES = 0;
    static final int STANDALONE_MASK = 0x8000;
    public static final int SHORT = 1;
    public static final int LONG = 2;
    public static final int NARROW_FORMAT = 4;
    public static final int NARROW_STANDALONE = NARROW_FORMAT | STANDALONE_MASK;
    public static final int SHORT_FORMAT = 1;
    public static final int LONG_FORMAT = 2;
    public static final int SHORT_STANDALONE = SHORT | STANDALONE_MASK;
    public static final int LONG_STANDALONE = LONG | STANDALONE_MASK;

    public static Calendar getInstance() {
        return new Calendar();
    }

    public static Calendar getInstance(TimeZone zone) {
        return new Calendar();
    }

    private Date date;

    private Calendar() {
        this.date = new Date();
    }

    public int get(int field) {
        return 0;
    }

    public void set(int field, int value) {
    }

    public final Date getTime() {
        return date;
    }

    public final void setTime(Date date) {
        this.date = date;
    }

    public TimeZone getTimeZone() {
        return null;
    }

    public void setTimeZone(TimeZone value) {

    }
}
