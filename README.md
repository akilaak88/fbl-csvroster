## fbl-csvroster

The CSV Roster Application has been developed to import a classroom roster file(.csv file) provided through URL into a relational database. 

## The database contains three tables:

* Student (information about students)
* Teacher (information about teachers)
* Enrollment (information about which students are in which teachers' classes)


This Application will take the URL of CSV file as a command-line argument, along with a flag to indicate whether it is a Delta import(Y/N). Then the following operations will be performed.
* 1.	connect to the CSV file's URL
* 2.	read the CSV file
* 3.	insert records into the database
* 4.	report how many records were modified
* 5.	report a list of each teacher in the database, and a count of how many students are in a class held by that teacher
* 6.	and then exit.
* 7.	In addition - if any records are malformed, the program will provide an exception log of any records that it rejected.


For complete details of applicaiton check the [Design document](https://github.com/akilaak88/fbl-csvroster/blob/master/Design%20Document.docx)
