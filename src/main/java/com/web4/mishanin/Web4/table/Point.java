package com.web4.mishanin.Web4.table;

public class Point {
    private double x;
    private String y;
    private double r;
    private long time;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    private String date;
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getR() {
        return r;
    }

    public long getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public double getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x: " + this.x + " y: " + this.y + " r: " + this.r + " ans: " + this.answer;
    }
}
