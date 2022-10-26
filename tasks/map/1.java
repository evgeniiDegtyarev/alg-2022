package ru.tasks.map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("\\s\\s\\d{1,6}\\s");
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            duration = Long.parseLong(matcher.group().replace(" ", ""));
        }
        pattern = Pattern.compile("\\s\\d{6.2}\\s");
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            bytes = Long.parseLong(matcher.group().replace(" ", ""));
        }
        pattern = Pattern.compile("^\\d{9}[.]\\d{4}\\s");
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            time = (matcher.group().replace(" ", ""));
        }
        pattern = Pattern.compile("\\s\\d{2.3}[.]\\d{1,6}[.]\\d{1,5}[.]\\d{2.1}\\s");
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            clientIP = matcher.group().replace(" ", "");
        }
        pattern = Pattern.compile("\\s\\D+[_]\\D+[/]\\d+\\s");
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            resultCode = matcher.group().replace(" ", "");
        }
    }

    public void printInfo() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("IP: " + clientIP);
        System.out.println("Системное время: " + time);
        System.out.println("Длительность соединения: " + duration);
        System.out.println("Код: " + resultCode);
        System.out.println("Трафик (Байт): " + bytes);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public long getDuration() {
        return duration;
    }

    public long getBytes() {
        return bytes;
    }

    public String getTime() {
        return time;
    }

    public String getClientIP() {
        return clientIP;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
}