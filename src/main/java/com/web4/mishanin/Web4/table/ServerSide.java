package com.web4.mishanin.Web4.table;

import com.web4.mishanin.Web4.data.ServiceForData;
import com.web4.mishanin.Web4.data.UsersPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.regex.Pattern;

@RestController
public class ServerSide {
    private final List<String> xArea = Arrays.asList("-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3");
    private final List<String> rArea = Arrays.asList("1", "2", "3");
    private ArrayList<Point> points = new ArrayList<Point>();

    @Autowired
    ServiceForData service;

    @PostMapping("/table")
    public String post(@RequestBody Point point) {
        long time = System.currentTimeMillis();
        if (setAns(point.getX(), point.getY(), point.getR()).equals("Invalid data"))
            return "Invalid Data";
        point.setAnswer(setAns(point.getX(), point.getY(), point.getR()));
        System.out.println(point.getAnswer());
        point.setTime(System.currentTimeMillis() - time);
        service.addEntity(point);
        return "{" +
                "\"x\": " + "\""+point.getX()+"\"" + "," +
                "\"y\": " + "\""+point.getY()+"\"" + "," +
                "\"r\": " + "\""+point.getR()+"\""+"," +
                "\"date\": " + "\""+point.getDate()+"\""+"," +
                "\"time\": " + "\""+point.getTime()+"\""+"," +
                "\"answer\": " + "\""+point.getAnswer()+"\"" +
                "}";
    }
    @GetMapping("/table")
    public ArrayList<Point> get(@RequestParam("flag") String flag) {
        System.out.println("GET");
        System.out.println(flag);
        if (flag.equals("1")) {
            service.delete();
            points.clear();
            return points;
        }
        for (UsersPoint point : service.getEntity()) {
            Point p = new Point();
            p.setX(point.getX());
            p.setY(point.getY());
            p.setR(point.getR());
            p.setAnswer(point.getAnswer());
            p.setDate(point.getDate());
            p.setTime(point.getWorkTime());
            points.add(p);
        }
        return points;
    }

    public String setAns(double x, String y, double r){
        if (isData(x,y,r)) {
            double y1 = Double.parseDouble(y);
            return check(x, y1, r) ? "YES" : "NO";
        }
        return "Invalid data";
    }

    public boolean isData(double x, String y, double r) {
        try {
            boolean checkX = xArea.contains(Integer.toString((int) x));
            boolean checkR = rArea.contains(Integer.toString((int) r));
            isCorrectY(y);
            System.out.println(x + "??" + y + "??" + r);
            if (!(checkR && checkX))
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
        return true;
    }

    public boolean check(double x, double y, double r){
        System.out.println(x + "**" + y + "**" + r);
        if (x > 0 && y > 0)
            return false;
        else if (x <= 0 && y <= 0 && (-2 * x - y - r <= 0))
            return true;
        else if (x <= 0 && y >= 0 && (x * x + y * y <= (r / 2  * r / 2)))
            return true;
        else return x >= 0 && y <= 0 && (y >= -r) && (x <= (r / 2));
    }

    public void isCorrectY(String y) {
        System.out.println(y);
        double current = Double.parseDouble(y);
        String[] args = new String[128];
        if (y.contains(".")) {
            args = y.split("\\.");
            //log(args[0] + args[1]);
            Pattern pattern = Pattern.compile("[1-9]");
            Pattern patternAlpha = Pattern.compile("[a-zA-Z]");
            //System.out.println(decimalFormat.format(Boolean.parseBoolean(y)));
            if (Math.abs(Integer.parseInt(args[0])) >= 5 && ((pattern.matcher(args[1]).find())||(patternAlpha.matcher(args[1]).find()))) {
                //|| !rArea.contains(decimalFormat.format(Boolean.parseBoolean(r)))
                //|| !yArea.contains(decimalFormat.format(Boolean.parseBoolean(y)))) {
                throw new NumberFormatException();
            }
        } else if (Integer.parseInt(y) > 5) {
            throw new NumberFormatException();
        }
    }
}
