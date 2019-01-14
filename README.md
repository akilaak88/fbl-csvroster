# fbl-csvroster
Load and update records from a CSV File into the database using spring boot

The CSV Roster Application is developed to import a classroom roster file(.csv file), provided via a URL, into a relational database. The database contains three tables:

•	student (information about students)
•	teacher (information about teachers)
•	enrollment (information about which students are in which teachers' classes)


This Application will take the URL of a CSV file as a command-line argument, along with a flag to indicate whether this is a Delta import(Y/N). Then the following operations will be performed.
1.	connect to the CSV file's URL
2.	read the CSV file
3.	insert records into the database
4.	report how many records were modified
5.	report a list of each teacher in the database, and a count of how many students are in a class held by that teacher
6.	and then exit.
7.	In addition - if any records are malformed, the program will provide an exception log of any records that it rejected.
