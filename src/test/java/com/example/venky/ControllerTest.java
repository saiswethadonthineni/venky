package com.example.venky;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @InjectMocks
    private AppController appController;

    @Mock
    private EmployeeRepo employeeRepo;

/*
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }*/
    @Test
    void updateDetailsTest() {
        Employee existingEmployee = new Employee();
        existingEmployee.setId(3);
        existingEmployee.setName("sai");
        existingEmployee.setDept("IT");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("sai");
        employeeDto.setDept("cs");

        Mockito.when(employeeRepo.findById(4)).thenReturn(Optional.of(existingEmployee));
        Mockito.when(employeeRepo.save(any(Employee.class))).thenReturn(existingEmployee);

        ResponseEntity<Employee> responseEntity = appController.updateDetails(4, employeeDto);

        Assertions.assertEquals(employeeDto.getName(), responseEntity.getBody().getName());
        Assertions.assertEquals(employeeDto.getDept(), responseEntity.getBody().getDept());
    }

    @Test
    void updateDetailsFailureTest() {
        Employee existingEmployee = new Employee();
        Integer id =5;
        existingEmployee.setId(3);
        existingEmployee.setName("sai");
        existingEmployee.setDept("IT");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("sai");
        employeeDto.setDept("cs");

        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        ResponseEntity<Employee> responseEntity = appController.updateDetails(id, employeeDto);
        Assertions.assertEquals(ResponseEntity.notFound().build(),responseEntity);


    }




}