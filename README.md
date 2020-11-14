# PatientHealthApp
This something I made for a University Project but was version controlled on the University SVN, but I've added here for a personal copy. Many aspects of the idea were not completed, as it was a big undertaking for one person (it being the first large project that I have managed on my own). 
Though the idea was not fully realised, everything done was sufficient to demonstrate skill in the related technologies and software development required to do well in the university module/class.

As this project was for university, it is online for the purpose of being on display as part of a potfolio of my work, but cannot be forked or used for any purpose beyond to be observed as evidence of my work and epxerience.

## Tables of Contents
* [Background] (#background)
* [Technologies] (#technologies)
* [Requirements to run the project]  (#requirements-to-run-the-project)
* [Setup] (#setup)
* [Running the project] (#running-the-project)
* [Project Shortcommings] (#shortcommings)

## Background
My project was to create a system that would allow patients with chronic illness to arrange the ordering of their repeat prescriptions online rather than in person as well as to book appointments online. The purpose of this is to save them time that could be put to better use.



## Technologies
- Java 
- Gradle
- Spring MVC Framework
- Hibernate
- MySQL

## Requirements to run the project
You will need to have java 11 or higher installed and on your classpath in order to run the project.

Optionally you can use a local installation of gradle to build and run this, but there


## Setup
In order to run the project, you will need to configure the MySQL database settings. You can configure the database to be either a local or remote MySQL database. To do this you will need to fill in the application.properties file with your database configuration.

For example you can download MySQL Server and create and local MySQL database instance, or (if you are comfortable using ssh tunnelling and have a raspberry pi) you can download MariaDB server to the pi, run a server instance and then using ssh tunnelling can run the application with the remote settings.

## Running the Project
From the terminal, cd the project directory. Once inside the directory: 

On Mac and Linux
```
./gradlew bootRun
```

On Windows
```
./gradlew.bat bootRun
```

Once the boot process is complete, you can then view the website from https://localhost:8090/ (if you are using default configurations). 

You can login to the application using these credentials:
|username | password|
|Admin1  | password|
|Patient1 | password|
|Doctor1 | password|
There for each type their are 3 users (i.e. {usertype}{number} - e.g. Admin2). 

The admin functionality is the most complete of the 3 system user types, though there remains still a lot of work to 

## Project Shortcommings
The project does not have testing in place, mainly because I was focussed on trying to get as much of the idea realised as possible within time constraints. 

Besides this, as mentioned, many aspect would still require some work to be complete and though the assignment/assessment is done, I would like to add some testing and finish off project as intented just for myself for the sake of completeness and finshing up what was started.