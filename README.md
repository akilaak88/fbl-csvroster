## FBL-CSVRoster

# Overview
CSV Roster Application has been developed to implement a classroom roster file (.csv file), provided via a URL, into a relational database. 
The database contains three tables:
*	Student (information about students)
*	Teacher (information about teachers)
*	Enrollment (information about which students are in which teachers' classes)

# Context
There are 4 types of CSV files handled in this application
1. Straight forward CSV file which has the correct data to be updated in the database. 
2. A CSV file with longer student ID will be rejected by the program. 
3. A CSV file that has characters not present in the normal ASCII character set for student first and last name will be rejected.
4. A delta CSV file with a column titled "Action”, containing either "A" for 'Add' or "D" for 'Delete' will either add or remove records as specified.

# Objectives
This Application will take the URL of a CSV file as a command-line argument, along with a flag to indicate whether this is a Delta import(Y/N). Then the following operations will be performed.
1.	Connect to the CSV file's URL
2.	Read the CSV file
3.	Insert records into the database
4.	Report how many records were modified
5.	Report a list of each teacher in the database, and a count of how many students are in a class held by that teacher
6.	Then exit.
7.	In addition - if any records are malformed, the program will provide an exception log of any records that is rejected.

# Technical Details
This application is developed using 

* JDK: Java 8

* Framework: Spring Boot

* Build: Apache Maven

* Database: MySQL

* CSV Reader: Apache Commons CSV library

* JDBC: For Database Connectivity

* Testing Framework: JUnit

# Assumptions
-	The URL for CSV file should be public. If the CSV resides in a private URL or resides in a local directory, the file cannot be accessed through this application.
-	The records will be rejected only if ASCII values are present in the first name or last name of the students. The application will also reject records having longer student ID’s.

# Other Design Ideas Considered and Rejected
Considered using a JSP for front end (i.e, to get the file name in a text box instead of a command line argument). The reason for considering this approach is to make the project more presentable to the user. 

* Reason for Rejection:
Though this approach does not require any command line to provide the input, it will not give a good looking output, since, there are lot a values to be displayed as the output. Also, for huge number of records, the user should stay in the same page without refreshing or hitting the back button. If by any chance, the user closes the screen, the session will ended and the program will be exited.

# Enhancement:
To process roster files of over 100K records or 1 million records:
BatchUpdate method can be used instead of iterating through every record as all values of 100K/1M records will be sent at once, instead of sending each and every record row by row.
