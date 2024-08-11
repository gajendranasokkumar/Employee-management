package com.example.leave;

    public class Employee {
    private String name;
    private Integer id;
    private Long taken_days;
    private String department;
    private String email;

    public Employee(){}


        public Employee(Integer id, String name , String email, String department, Long taken_days) {
        this.name = name;
        this.id = id;
        this.taken_days = taken_days;
        this.department = department;
        this.email = email;
    }

        public Employee( String name ,Integer id, String email, String department, Long taken_days) {
            this.name = name;
            this.id = id;
            this.taken_days = taken_days;
            this.department = department;
            this.email = email;
        }

        public Employee(String name, Integer id) {
            this.name = name;
            this.id = id;
        }

        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTaken_days(Long taken_days) {
        this.taken_days = taken_days;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Long getTaken_days() {
        return taken_days;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", taken_days=" + taken_days +
                ", department='" + department + '\'' +
                '}';
    }
}