package com.web4.mishanin.Web4.data;

import com.web4.mishanin.Web4.table.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ServiceForData {
    @Autowired
    private CustomerRepository employeesCrudRepository;

    @Transactional
    public Iterable<UsersPoint> getEntity() {
        Iterable<UsersPoint> employeesOptional = employeesCrudRepository.findAll();
        System.out.println(employeesOptional);
        employeesOptional.forEach(e -> System.out.println(e.getAnswer()));
        return employeesOptional;
    }

    @Transactional
    public void addEntity(Point usersPoint) {
        UsersPoint point = new UsersPoint();
        point.setX(usersPoint.getX());
        point.setY(usersPoint.getY());
        point.setR(usersPoint.getR());
        point.setDate(usersPoint.getDate());
        point.setWorkTime(usersPoint.getTime());
        point.setAnswer(usersPoint.getAnswer());
        point.setOwner(usersPoint.getOwner());
        System.out.println(point.getWorkTime());
        employeesCrudRepository.save(point);
    }
    @Transactional
    public void delete(String username) {
        employeesCrudRepository.deleteUsersPointsByOwner(username);
    }

}
