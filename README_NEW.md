# mindex-java-coding-challenge

I've added additional classes and interfaces to the existing mindex-jaba-coding-challenge to address the 2 challenges from the original README file. 

I've also listed some tests I used to verify the newly added endpoints satisfy the requirements:


Test 1:  Creating a compensation

URL: http://localhost:8080/compensation

Request Body:
{
    "employee": {
        "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3"
    },
    "salary": 180000,
    "effectiveDate": "07-12-2022"
}


Expected Result:  Successfully adding compensation to in memory MongoDB.



Test 2:  Failing date validation when creating compensation

URL: http://localhost:8080/compensation

Request Body:
{
    "employee": {
        "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3"
    },
    "salary": 180000,
    "effectiveDate": "07-BADDAY-2022"
}


Expected Result:  Thows NumberFormatException and does not add compensation to in memory MongoDB.



Test 3:  Failing date validation by providing an invalid date when creating compensation

URL: http://localhost:8080/compensation

Request Body:
{
    "employee": {
        "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3"
    },
    "salary": 180000,
    "effectiveDate": "13-13-2022"
}


Expected Result:  Thows DateTimeException and does not add compensation to in memory MongoDB.




Test 4:  Seeing correct number of Compensations in List:


step 1: Run URL with following request body (same as step 1)

URL: http://localhost:8080/compensation

Request Body:
{
    "employee": {
        "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3"
    },
    "salary": 180000,
    "effectiveDate": "07-12-2022"
}

Expected Result:  Successfully adding compensation to in memory MongoDB.


step 2: Run URL with following request body 

URL: http://localhost:8080/compensation

Request Body:
{
    "employee": {
        "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3"
    },
    "salary": 190000,
    "effectiveDate": "07-13-2022"
}

Expected Result:  Successfully adding compensation to in memory MongoDB.



step 3: Run URL with following request body 

URL: http://localhost:8080/compensation

Request Body:
{
    "employee": {
        "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3"
    },
    "salary": 194000,
    "effectiveDate": "07-14-2022"
}

Expected Result:  Successfully adding compensation to in memory MongoDB.


step 4: Run URL with following request body 

URL: http://localhost:8080/compensation

Request Body:
{
    "employee": {
        "employeeId": "c0c2293d-16bd-4603-8e08-638a9d18b22c"
    },
    "salary": 24000,
    "effectiveDate": "07-14-2022"
}

Expected Result:  Successfully adding compensation to in memory MongoDB.



step 5: Run URL with following request body 

URL: http://localhost:8080/compensation

Request Body:
{
    "employee": {
        "employeeId": "c0c2293d-16bd-4603-8e08-638a9d18b22c"
    },
    "salary": 25000,
    "effectiveDate": "07-16-2022"
}

Expected Result:  Successfully adding compensation to in memory MongoDB.



step 6: Run URL (GET request):  http://localhost:8080/compensation/c0c2293d-16bd-4603-8e08-638a9d18b22c

Expected Result:  A JSON Array of 2 Compensations.



step 7: Run URL (GET request):  http://localhost:8080/compensation/b7839309-3348-463b-a7e3-5de1c168beb3

Expected Result:  A JSON Array of 3 Compensations.




Test 5: Generating a ReportStructure 

URL:  http://localhost:8080/reportingStructure/16a596ae-edd3-4847-99fe-c4518e82c86f

Expected Result: A Reporting Structure object for Employee John Lennon and a numberOfReports of 4 (assuming we're using the same employee_database.json file when bootstrapping data) 




