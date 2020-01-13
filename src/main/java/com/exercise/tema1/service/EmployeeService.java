package com.exercise.tema1.service;

import com.exercise.tema1.domain.Employee;
import com.exercise.tema1.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(String name, String company, String function, Date hireDate) {
        Employee newEmployee = new Employee();
        newEmployee.setName(name);
        newEmployee.setCompany(company);
        newEmployee.setFunction(function);
        newEmployee.setHireDate(hireDate);
        employeeRepository.save(newEmployee);
        return newEmployee;
    }

    public Optional<Employee> findEmployee(Long id){
        return employeeRepository.findById(id);
    }

    public List<Employee> findAllEmployeesFromComany(String company) {
        return employeeRepository.findAllByCompany(company);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).ifPresent(employee -> {
            employeeRepository.delete(employee);
        });
    }

    public Employee updateEmployee(Employee employee, String name, String company, String function, Date hireDate){
        employee.setName(name);
        employee.setCompany(company);
        employee.setFunction(function);
        employee.setHireDate(hireDate);
        employeeRepository.save(employee);
        return employee;
    }
}
