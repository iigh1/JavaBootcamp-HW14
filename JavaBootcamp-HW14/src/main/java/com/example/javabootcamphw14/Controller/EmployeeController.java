package com.example.javabootcamphw14.Controller;

import com.example.javabootcamphw14.ApiResponse.ApiResponse;
import com.example.javabootcamphw14.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/emp")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    // get all employees

    @GetMapping("/get")
    public ArrayList getEmployees() {
        return employees;
    }

    // add new employee

    @PostMapping("/add")
    public ResponseEntity addEmployees(@Valid @RequestBody Employee employee, Errors errors) {

        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massage));
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("employee added"));
    }

    // update employee

    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployees(@Valid @RequestBody @PathVariable int index, Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body((new ApiResponse(massage)));
        }
        employees.set(index, employee);
        return ResponseEntity.status(200).body(new ApiResponse("employee updated"));
    }

    // delete employee

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployees(@PathVariable int index) {
        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("employee deleted"));
    }

    // apply leave

    @PutMapping("/leave/{id}")
    public ResponseEntity applyLeave(@PathVariable String id,Employee employee) {

            if (employee.isOnLeave()) {
                return ResponseEntity.badRequest().body("Employee is already on leave");
            }
            if (employee.getAnnualLeave() <= 0) {
                return ResponseEntity.badRequest().body("Employee has no more annual leave days");
            }
            employee.setOnLeave(true);
            employee.setAnnualLeave(employee.getAnnualLeave() - 1);

        return new ResponseEntity("Annual leave applied successfully",HttpStatus.OK);


        }
    }




