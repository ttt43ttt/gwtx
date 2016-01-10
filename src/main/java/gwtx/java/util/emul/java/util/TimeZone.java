package java.util;

public class TimeZone {

    public static synchronized TimeZone getTimeZone(String ID) {
        return new TimeZone();
    }

    public static TimeZone getDefault() {
        return new TimeZone();
    }

    private TimeZone() {
    }

    public int getRawOffset() {
        return 0;
    }
}
