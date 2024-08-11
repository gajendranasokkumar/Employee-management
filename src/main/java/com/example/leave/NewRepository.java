package com.example.leave;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class NewRepository {
    private final JdbcClient jdbcClient;

    public NewRepository(JdbcTemplate template, JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Employee> getAllEmployees() {
        return jdbcClient.sql("SELECT * FROM employees").query(
                (rs, rowNum) -> new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getLong("taken_days"))
        ).list();
    }

    public void addEmployee(Employee newEmployee) {
        String sql = "INSERT INTO employees ( name ,  email, taken_days,  department) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .params( newEmployee.getName(), newEmployee.getEmail(),newEmployee.getTaken_days(),newEmployee.getDepartment())
                .update(keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            newEmployee.setId(key.intValue());
        }
    }

    public Employee getEmployeeById(Long id) {
        return jdbcClient.sql("SELECT * FROM employees WHERE id = :id")
                .params(Map.of("id", id))
                .query(
                        (rs, rowNum) -> new Employee(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("department"),
                                rs.getLong("taken_days"))
                ).single();
    }


    public Employee getEmployeeByEmail(String email) {
        return jdbcClient.sql("SELECT * FROM employees WHERE email = :email")
                .params(Map.of("email", email))
                .query(
                        (rs, rowNum) -> new Employee(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("department"),
                                rs.getLong("taken_days"))
                ).single();
    }

    public void removeEmployee(Integer id) {
        try {
            String sql = "DELETE FROM employees WHERE id = ?";

            jdbcClient.sql(sql)
                    .params(id)
                    .update();

            // Optionally, log a message indicating successful deletion
            System.out.println("Employee with ID " + id + " deleted successfully.");
        } catch (Exception e) {
            // Log the error message or handle it as needed
            System.err.println("Error deleting employee with ID " + id + ": " + e.getMessage());
            // You can re-throw the exception if you want to propagate it further
            // throw e;
        }
    }


    public void addLeaveForm(Leave newLeave)
    {
        String sql = "INSERT INTO `leave` ( name , id, from_date, to_date, subject, reason) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .params( newLeave.getName(), newLeave.getId(),newLeave.getFrom_date(),newLeave.getTo_date(), newLeave.getSubject(), newLeave.getReason())
                .update(keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            newLeave.setId(key.intValue());
        }
    }

    public String checkUser(Login newLogin) {
        System.out.println("Comming into the repository");
        System.out.println(newLogin.getEmail());
        try {
            Employee returnedLogin = jdbcClient.sql("SELECT * FROM employees WHERE email = ?")
                    .params(newLogin.getEmail())
                    .query(
                            (rs, rowNum) -> new Employee(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("email"),
                                    rs.getString("department"),
                                    rs.getLong("taken_days"))
                    ).single();

            if (returnedLogin != null) {
                return returnedLogin.getName();
            } else {
                // Handle the case where no matching employee is found
                return "Unknown";
            }
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where no matching employee is found
            return "Unknown";
        }
    }



    public List<Leave> getAllLeave()
    {
        String allLeveQuery = "SELECT * FROM `leave` WHERE statuss = 0";
        return jdbcClient.sql(allLeveQuery).query(
                (rs, rowNum) -> new Leave(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("from_date"),
                        rs.getString("to_date"),
                        rs.getString("subject"),
                        rs.getString("reason"),
                        rs.getInt("statuss"),
                        rs.getInt("leave_id")
                        )
        ).list();
    }


    public List<Employee> getAllEmployeeList()
    {
        String sql = "SELECT name, id FROM employees";
        return jdbcClient.sql(sql).query(
                (rs, rn) -> new Employee(
                        rs.getString("name"),
                        rs.getInt("id")
                )
        ).list();
    }

    public  Leave getOneLeaveForm(Integer id)
    {
        String sql = "SELECT * FROM `leave` WHERE leave_id = ?";
        return jdbcClient.sql(sql).params(id).query(
                (rs,rn)-> new Leave(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("from_date"),
                        rs.getString("to_date"),
                        rs.getString("subject"),
                        rs.getString("reason"),
                        rs.getInt("statuss"),
                        rs.getInt("leave_id")
                )
        ).single();
    }



    public void approveForm(Integer id)
    {
        String sql = "UPDATE `leave` SET statuss = 1 WHERE leave_id = ?";

        jdbcClient.sql(sql).params(id).update();
    }


    public void denyForm(Integer id)
    {
        String sql = "UPDATE `leave` SET statuss =2  WHERE leave_id = ?";

        jdbcClient.sql(sql).params(id).update();
    }


    public List<Leave> getAllPast(Integer empId)
    {
        String sql = "SELECT * FROM `leave` WHERE id = ? AND statuss != 0";
        return jdbcClient.sql(sql).params(empId).query(
                (rs,rn)->new Leave(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("from_date"),
                        rs.getString("to_date"),
                        rs.getString("subject"),
                        rs.getString("reason"),
                        rs.getInt("statuss"),
                        rs.getInt("leave_id")
                )
        ).list();
    }


    public List<Leave> getAllPastForManager()
    {
        String sql = "SELECT * FROM `leave` WHERE statuss != 0";
        return jdbcClient.sql(sql).query(
                (rs,rn)->new Leave(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("from_date"),
                        rs.getString("to_date"),
                        rs.getString("subject"),
                        rs.getString("reason"),
                        rs.getInt("statuss"),
                        rs.getInt("leave_id")
                )
        ).list();
    }


    public Leave getLatestLeaveForm(Integer id)
    {
//        String sql = "SELECT * FROM `leave` WHERE id = ? ORDER BY id DESC LIMIT 1";
//        return  jdbcClient.sql(sql).params(id).query(
//                (rs,rn)->new Leave(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("from_date"),
//                        rs.getString("to_date"),
//                        rs.getString("subject"),
//                        rs.getString("reason"),
//                        rs.getInt("statuss")
//                )
//        ).single();
        return new Leave(
                1,"Gajendran A","6/4/2024","6/4/2024","Going to native","As i need to go for a festival i am in need of leave.",0
        );
    }

}