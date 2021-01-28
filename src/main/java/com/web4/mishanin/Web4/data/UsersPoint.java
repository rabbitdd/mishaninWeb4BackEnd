package com.web4.mishanin.Web4.data;


import javax.persistence.*;

@Entity
@Table(name = "POINTS")
public class UsersPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "CUSTOMER_SEQ")
    @Column(name="id")
    private int id;

    @Column(name = "x")
    private double x;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "y")
    private String y;
    @Column(name = "r")
    private double r;
    @Column(name = "dte")
    private String date;
    @Column(name = "WORKTIME")
    private long workTime;
    @Column(name = "answer")
    private String answer;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(long workTime) {
        this.workTime = workTime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
}
