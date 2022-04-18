package com.mindex.challenge.data;
//import java.util.Calendar;


/**
 *
 * @author Liam
 */
public class Compensation {

    private Employee employee;
    private double salary;
    private String effectiveDate;

    public Compensation (Employee employee, double salary, String effectiveDate){
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }
    
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    
    public Employee getEmployee(){
        return this.employee;
    }
    
    public void setSalary(double salary){
        this.salary = salary;
    }
    
    public double getSalary(){
        return this.salary;
    }

    public void setEffectiveDate(String effectiveDate){
        this.effectiveDate = effectiveDate;
    }
    
    public String getEffectiveDate(){
        return this.effectiveDate;
    } 

}
