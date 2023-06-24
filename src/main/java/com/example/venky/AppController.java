package com.example.venky;

import com.example.venky.serviceinterface.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class AppController {

    @Autowired
      EmployeeRepo employeeRepo;
@Autowired
    AppService service;

    @GetMapping("/")
    public String gene(){
        return "Venky";
    }

    @GetMapping("/getAll")
    public List<Employee> getEmployee(@RequestParam(name="id",required=false) Integer id,@RequestParam(name="status",required = false) Integer status){

       log.info("Hey venky its coming here");
        List<Employee> l=employeeRepo.findAll();
       // l.stream().map(n->n.getId()>6).collect(Collectors.toList());
        if(id!=null&&status!=null){
            l=employeeRepo.getEmployeesByIdAndStatus(id,status);
        }
        else{
            l=employeeRepo.findAll();
        }
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
    public ResponseEntity<Employee> updateDetails(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto){
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

    @GetMapping("/getNum")
    public ResponseEntity<List<Integer>> findNumbers(
            @RequestParam Integer fromNum,
            @RequestParam Integer toNum,
            @RequestParam Integer ch
    ) {
        List<Integer> numbers = service.findNumbers(fromNum, toNum, ch);
        return ResponseEntity.ok(numbers);}

    @GetMapping("/getNumber/{n}")
    public List<String> getNumbers(@PathVariable Integer n){
        List<String>l = new ArrayList<>();
        l=service.getNumbers(n);
        return l;
    }

    @GetMapping("/fibonacii/{count}")
    public List<Integer> getFibonacii(@PathVariable Integer count){
        List<Integer> l =service.getFibonacii(count);
        return l;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNumbers(@RequestParam Integer a,@RequestParam Integer b){
        Integer i=a+b;
        String s="the result of sum is "+ i;
        return ResponseEntity.ok(s);

    }


}
