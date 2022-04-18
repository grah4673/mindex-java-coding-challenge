package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.ReportingStructureService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Liam
 * 
 * getEmployeesReportingStructure recursively determines the current 
 * employee's direct report information.  The numberOfReports that 
 * gets passed ultimately represents the number of direct reports for the
 * employee that initially gets evaluated when the method is first called.
 * 
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{
    
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
    
    @Autowired
    private EmployeeServiceImpl EmployeeServiceImpl = new EmployeeServiceImpl();
    
    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating Reporting Structure based on employee with id [{}]", id);
        return getEmployeesReportingStructure(id, 0);
    }
    
    private ReportingStructure getEmployeesReportingStructure(String id, int numberOfReports){
        
        Employee employee = EmployeeServiceImpl.read(id);
        ReportingStructure structure = new ReportingStructure(employee, numberOfReports);
        List<Employee> directReports = employee.getDirectReports();
        
        //base case: stop recursion when no direct reports found for employee
        if (directReports == null || directReports.isEmpty()){
            return structure;
        }
        else {
            for (Employee directReport : directReports){
                ReportingStructure structureOfDirectReport = getEmployeesReportingStructure(directReport.getEmployeeId(), numberOfReports + directReports.size());             
                directReport.setFirstName(structureOfDirectReport.getEmployee().getFirstName());
                directReport.setLastName(structureOfDirectReport.getEmployee().getLastName());
                directReport.setPosition(structureOfDirectReport.getEmployee().getPosition());
                directReport.setDepartment(structureOfDirectReport.getEmployee().getDepartment());
                directReport.setDirectReports(structureOfDirectReport.getEmployee().getDirectReports());
                structure.setNumberOfReports(structureOfDirectReport.getNumberOfReports());
            }
        }
        return structure;
    }
}
