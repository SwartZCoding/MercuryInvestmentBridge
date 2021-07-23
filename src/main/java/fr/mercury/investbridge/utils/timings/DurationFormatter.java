package fr.mercury.investbridge.utils.timings;

import org.apache.commons.lang.time.DurationFormatUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DurationFormatter
{
    private static final long MINUTE = TimeUnit.MINUTES.toMillis(1L);
    private static final long HOUR = TimeUnit.HOURS.toMillis(1L);
    private static final long DAY = TimeUnit.DAYS.toMillis(1L);
    private static SimpleDateFormat FRENCH_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static String getRemaining(long millis, boolean milliseconds)
    {
        return getRemaining(millis, milliseconds, true);
    }

    public static String getRemaining(long duration, boolean milliseconds, boolean trail)
    {
        if ((milliseconds) && (duration < MINUTE)) {
            return
                    ((DecimalFormat)(trail ? REMAINING_SECONDS_TRAILING : REMAINING_SECONDS).get()).format(duration * 0.001D) + 's';
        }
        if (duration >= DAY) {
            return DurationFormatUtils.formatDuration(duration, "dd-HH:mm:ss");
        }
        return DurationFormatUtils.formatDuration(duration, (duration >= HOUR ? "HH:" : "") + "mm:ss");
    }

    public static String getDurationWords(long duration)
    {
        return DurationFormatUtils.formatDuration(duration, "d' jours 'H' heures 'm' minutes 's' secondes'");
    }

    public static String getDurationDate(long duration)
    {
        return FRENCH_DATE_FORMAT.format(new Date(duration));
    }

    public static String getCurrentDate()
    {
        return FRENCH_DATE_FORMAT.format(new Date());
    }

    public static final ThreadLocal<DecimalFormat> REMAINING_SECONDS = new ThreadLocal()
    {
        protected DecimalFormat initialValue()
        {
            return new DecimalFormat("0.#");
        }
    };
    public static final ThreadLocal<DecimalFormat> REMAINING_SECONDS_TRAILING = new ThreadLocal()
    {
        protected DecimalFormat initialValue()
        {
            return new DecimalFormat("0.0");
        }
    };
}
