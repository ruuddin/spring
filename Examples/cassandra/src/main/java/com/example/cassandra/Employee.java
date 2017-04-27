package com.example.cassandra;

public class Employee extends Person {

    private long employeeId;
    private String companyName;

    public Employee(){
        super();
    }

    public Employee(
            long employeeId, String companyName){
        this.employeeId = employeeId;
        this.companyName = companyName;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", companyName=" + companyName + "]";
    }
}

