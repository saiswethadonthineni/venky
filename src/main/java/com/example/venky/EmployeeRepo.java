package com.example.venky;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

    @Query("SELECT em FROM Employee em where em.id=:id and em.status=:status")
    List<Employee> getEmployeesByIdAndStatus(Integer id, Integer status);
 Optional<Employee>findById(Integer id);
    List<Employee> findByIdAndName(Integer id,String Name);
}

