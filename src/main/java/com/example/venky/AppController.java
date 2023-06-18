package com.example.venky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AppController {

    @Autowired
      EmployeeRepo employeeRepo;


    @GetMapping("/")
    public String gene(){
        return "Venky";
    }

    @GetMapping("/getAll")
    public List<Employee> getEmployee(@RequestParam(name="id",required=false) Integer id,@RequestParam(name="status",required = false) Integer status){

        System.out.println("Hey venky its coming here");
        List<Employee> l=employeeRepo.findAll();
        l.stream().map(n->n.getId()>6).collect(Collectors.toList());
        if(id!=null&status!=null){
            l=employeeRepo.getEmployeesByIdAndStatus(id,status);
        }
        else{
            l=employeeRepo.findAll();
        }
       // l=employeeRepo.findAll();
       // System.out.println(l);
        return l;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getEmployeebyId(@PathVariable Integer id){
        Optional<Employee> l=employeeRepo.findById(id);
        if(l.isPresent()){
            Employee e=l.get();
            return ResponseEntity.ok(e);

        }
        else{
            return ResponseEntity.notFound().build();
        }
        }

    @GetMapping("/getById")
    public ResponseEntity<Employee> getEmployeesbyId(@RequestParam Integer id){
        Optional<Employee> l=employeeRepo.findById(id);
        if(l.isPresent()){
            Employee e=l.get();
            return ResponseEntity.ok(e);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/savedata")
    public ResponseEntity<Employee>saveEmployeeData(@RequestBody Employee employee){
        if (employee == null) {

            return ResponseEntity.notFound().build();
        }
         Employee d=employeeRepo.save(employee);

        return ResponseEntity.ok(d);
    }

    @PutMapping("/updateDetails/{id}")
    public ResponseEntity<Employee> UpdateDetails(@PathVariable Integer id,@RequestBody EmployeeDto employeeDto){
        System.out.println("hi");
        System.out.println(employeeDto);
        Optional<Employee> e=employeeRepo.findById(id);
        if(e.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Employee existingEmployee=e.get();
        existingEmployee.setName(employeeDto.getName());
        existingEmployee.setDept(employeeDto.getDept());

        Employee em=employeeRepo.save(existingEmployee);
        return ResponseEntity.ok(em);
    }


}
