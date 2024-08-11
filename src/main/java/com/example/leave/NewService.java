package com.example.leave;

import com.example.leave.Employee;
import com.example.leave.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewService {
    private final NewRepository newRepository;

    @Autowired
    public NewService(NewRepository newRepository)
    {
        this.newRepository = newRepository;
    }

    public List<Employee> getAllEmployees() {
        return newRepository.getAllEmployees();
    }

    public void removeEmployee(Integer id)
    {
        newRepository.removeEmployee(id);
    }

    public void addEmployees(Employee newEmployee)
    {
        newRepository.addEmployee(newEmployee);
    }

    public Employee getEmployeeById(Long id) {
        // validation
        return newRepository.getEmployeeById(id);
    }

    public Employee getEmployeeByEmail(String email) {
        return newRepository.getEmployeeByEmail(email);
    }

    public  List<Leave> getAllLeave()
    {
        return  this.newRepository.getAllLeave();
    }

    public String checkUser(Login newLogin)
    {
        System.out.println("Comming into the service");
        return newRepository.checkUser(newLogin);
    }
    public void addLeaveForm(Leave newLeave)
    {
        newRepository.addLeaveForm(newLeave);
    }

    public List<Employee> getAllEmployeeList(){
        return this.newRepository.getAllEmployeeList();
    }

    public Leave getOneLeaveForm(Integer id)
    {
        return this.newRepository.getOneLeaveForm(id);
    }

    public void approveForm(Integer id)
    {
        this.newRepository.approveForm(id);
    }


    public void denyForm(Integer id)
    {
        this.newRepository.approveForm(id);
    }


    public List<Leave> getAllPast(Integer empId)
    {
        return this.newRepository.getAllPast(empId);
    }

    public List<Leave> getAllPastForManager()
    {
        return this.newRepository.getAllPastForManager();
    }

    public Leave getLatestLeaveForm(Integer id)
    {
        return  this.newRepository.getLatestLeaveForm(id);
    }
}