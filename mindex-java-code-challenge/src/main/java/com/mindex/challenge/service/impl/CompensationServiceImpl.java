package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import java.text.ParseException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 *
 * @author Liam
 */
@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
    
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    @Autowired
    private CompensationRepository compensationRepository;
      
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        Employee employee = employeeServiceImpl.read(compensation.getEmployee().getEmployeeId());
        
        validateEffectiveDate(compensation.getEffectiveDate());
        
        //validate employee already exists before creating a compensation record
        if(employeeServiceImpl.read(employee.getEmployeeId()) != null){
            compensation.setEmployee(employee);  //ensures Compensation's Employee property matches Employee repository's Employee props
            compensationRepository.insert(compensation);
        }
       
        return compensation;
    }    
    
    @Override
    public List<Compensation> read(String id) {
        LOG.debug("Creating Compensation for employee with id [{}]", id);
        
        Employee employee = employeeServiceImpl.read(id);
        List <Compensation> compensationHistory = compensationRepository.findByEmployeeEmployeeId(employee.getEmployeeId());
        
        if (compensationHistory == null){
            throw new RuntimeException("Could not find any compensation history for employee: " + employee.getEmployeeId());
        }

        return compensationHistory;
    }
 
    //Ensures date provided is a valid calendar date (MM/dd/yyyy format)
    private void validateEffectiveDate(String effectiveDateString){    
        try {
            String[] dateParts = effectiveDateString.split("-");
            int month = Integer.parseInt(dateParts[0]) - 1;
            int day = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
        }
        catch(NumberFormatException exception){
            LOG.debug("There was a problem with your effectiveDate. Ensure each month, day and year value is a valid number, and is formatted correctly (MM-dd-yyyy)");
            throw new RuntimeException("There was a problem with your effectiveDate. Ensure each month, day and year value is a valid number, and is formatted correctly (MM-dd-yyyy)");
        }
        
        try{
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate date = LocalDate.parse(effectiveDateString, dateFormat);
        }
        catch(DateTimeException exception){
            LOG.debug("The date provided is not a valid date. Please ensure the date is a valid calendar date");
            throw new RuntimeException("The date provided is not a valid date. Please ensure the date is a valid calendar date");        
        }  
    }
}



