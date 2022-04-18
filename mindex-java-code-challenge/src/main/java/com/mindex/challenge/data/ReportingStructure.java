/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mindex.challenge.data;

/**
 *
 * @author Liam
 */
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;
    
    public ReportingStructure(Employee employee, int numberOfReports){
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }
    
    public Employee getEmployee(){
        return this.employee;
    }
    
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public int getNumberOfReports(){
        return this.numberOfReports;
    }
    
    public void setNumberOfReports(int numberOfReports){
        this.numberOfReports = numberOfReports;
    }
    
}
