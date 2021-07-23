package fr.mercury.investbridge.utils.timings;

import java.util.HashMap;

public enum TimeUnit {

    SECONDE("Seconde(s) ", "sec", 1),
    MINUTE("Minute(s)", "min", 60),
    HEURE("Heure(s)", "h", 60 * 60),
    ;

    private String name;
    private String shortcut;
    private long toSecond;

    private static HashMap<String, TimeUnit> ID_SHORTCUT = new HashMap<>();

    private TimeUnit(String name, String shortcut, long toSecond) {
        this.name = name;
        this.shortcut = shortcut;
        this.toSecond = toSecond;
    }

    static {
        for(TimeUnit units : values()){
            ID_SHORTCUT.put(units.shortcut, units);
        }
    }

    public static TimeUnit getFromShortcut(String shortcut){
        return ID_SHORTCUT.get(shortcut);
    }

    public String getName(){
        return name;
    }

    public String getShortcut(){
        return shortcut;
    }

    public long getToSecond() {
        return toSecond;
    }

}
