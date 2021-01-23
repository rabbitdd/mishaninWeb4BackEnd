package com.web4.mishanin.Web4.table;

import com.web4.mishanin.Web4.data.ServiceForData;
import com.web4.mishanin.Web4.data.UsersPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ServerSide {
    private final List<String> xAndRArea = Arrays.asList("-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3");
    private ArrayList<Point> points = new ArrayList<Point>();

    @Autowired
    ServiceForData service;

    @PostMapping("/table")
    public String post(@RequestBody Point point) {
        long time = System.currentTimeMillis();
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

    public String setAns(double x, double y, double r){
        if (isData(x,y,r)) {
            return check(x, y, r) ? "YES" : "NO";
        }
        return "Invalid data";
    }

    public boolean isData(double x, double y, double r) {
        try {
            boolean checkX = xAndRArea.contains(Integer.toString((int) x));
            boolean checkR = xAndRArea.contains(Integer.toString((int) r));
            boolean checkY = y >= -5 && y <= 5;
            System.out.println(x + "??" + y + "??" + r);
            if (!(checkR && checkX && checkY))
                return false;
        } catch (Exception e) {
            e.printStackTrace();
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
}
