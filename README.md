# Chronic Patient's Health WebApp
This something I made for a University Project but was version controlled on the University SVN, but I've added here for a personal copy. 

Many aspects of the idea were not completed, as it was a big undertaking for one person (it being the first project of this size that I have managed on my own) and due to complications in working with Google API's [see problems](#project-shortcomings). Everything done mainly in the Admin side of the project was sufficient to demonstrate the required level of skill and capability in use of the related technologies and software development to do well in the university module/class [see project supervisor's comments in linkedin recommendation](https://www.linkedin.com/in/z-ishmael/).

As this project was for university, it is online for the purpose of being on display as part of a portfolio of my work, but cannot be forked or used for any purpose beyond to be observed as evidence of my work and experience.

Also be sure to see 
## [YOUTUBE WALKTHROUGH](https://www.youtube.com/watch?v=2LTY80dYC0g&t=81s)

## Tables of Contents
* [Background](#background)
* [Images](#images)
* [Technologies](#technologies)
* [Requirements to run the project](#requirements-to-run-the-project)
* [Setup](#setup)
* [Running the project](#running-the-project)
* [Project Shortcomings](#shortcomings)
* [Modifications](#modifications)
* [Trello Board](https://trello.com/b/e8hwRTpR/patientapp)
* [***Video Walkthrough - Youtube***](https://www.youtube.com/watch?v=2LTY80dYC0g&t=81s)

## Background
My project was to create a system that would allow patients with chronic illness to arrange the ordering of their repeat prescriptions online rather than in person as well as to book appointments online. The purpose of this is to save them time that could be put to better use.

## Images
![Login](./images/Login.png)
![Google Sign In](./images/GSignIn1.png)
![Google Sign In](./images/GSignIn4.png)
![Admin's Doctor-Patient dashboard](./images/image034.png)


## Technologies
- [Java](#java)
- [Gradle](#gradle)
- [Spring Framework](#spring-framework)
- [Hibernate](#hibernate)
- [MySQL Server](#mysql-server) (Originally - now uses H2Database for portability)
- [Thymeleaf Templating Engine](#thymeleaf-templating-engine)
- [Javascript](#javascript)
- [jQuery](#jquery)
- [HTML](#html)
- [CSS](#css)
- [Bootstrap](#bootstrap)
    ### External API's and Services

    - Google Sign
    - Google Calendar
    - Google Console
    ### Explanation of Each Technologies Role

    #### Java
    Is used to write/create the server side of the application. This include main data structures for manipulating database information as POJO objects on ther server side.

    #### Gradle
    A build tool technology (i.e. like Maven) used to provide easily portable/shareable dependency management.

    #### Spring Framework
    Spring is a framework for developing Java Applications. In particular I used the Spring MVC, Spring Security by way of Spring Boot in order to build the backend in a secure MVC type architecture. Spring Framework also provides a good means of creating and working with REST in order to create APIs for any AJAX related calls. Spring's Web depndency also provides and embedded Apache Tomcat Server for running the project.

    #### Hibernate
    Hibernate is the implementation of JPA (Java Persistence API) that is being used to model the database and carryout SQL queries directly through [POJO's]() instead of through MySQL statements.

    #### MySQL Server
    The Database used to persist application information.

    #### Thymleaf Templating Engine
    An alternative to using JSP pages, Thymeleaf allows you to create regular HTML pages that can be rendered by the browser, but with some additional syntax that allows Thymleaf to insert java objects and values from the server side into the HTML DOM elements. This is great for design without runnings the entire server instance.

    #### Javascript
    It's the main language used on the client side for crreating dynamic and interactive content.

    #### jQuery
    A javascript library good for working with AJAX among many other useful things [see website](https://jquery.com/).
    
    #### HTML
    The mark up langauge of the web.

    #### CSS
    The styling language of the web.

    #### Bootstrap
    HTML,CSS and Javascript library for creating dynamic web content[see website](https://getbootstrap.com/).

## Requirements to run the project
You will need to have java 11 or higher installed and on your classpath in order to run the project.

Optionally you can use a local installation of gradle to build and run this, but there is a gradle wrapper (gradlew) which can be used instead.


## Setup

### Now
Once downloaded no further setup is required. (though you should wasit for a moment after running before interacting with the application - to make sure server has run all it's processes.)

### Originally
Originalled, in order to run the project, you needed to configure it (in the application.properties) to work with a MySQL database instance, which could be either a local instance or remote instance using ssh tunnelling.

In my case it was configured to run with a University MySQL server instance, sometimes a local MySQL database instance, and later on a raspberry pi instance of MariaDB.

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

<!-- There for each type their are 3 users (i.e. {usertype}{number} - e.g. Admin2). -->

You can login to the application using these credentials:
|username | password|
|:---:|:---|
|Admin1  | password|
<!-- |Admin2  | password|
|Admin3  | password|
|Patient1 | password|
|Patient2 | password|
|Patient3 | password|
|Doctor1 | password|
|Doctor2 | password|
|Doctor3 | password| -->
 

To access the H2 console to view the database go to https://localhost:8090/h2-console/ and enter:

Saved Settings: Generic H2 (Embedded)

Settings Name: Generic H2 (Embedded)

Driver Class: org.h2.Driver

JDBC URL: jdbc:h2:file:./data/db

User Name: admin

Password: admin

as shown in the image below:
![h2console](./images/h2console.png)

The admin functionality is the most complete of the 3 system user types, though there still remains a lot left undone.

## Project Shortcomings
 ### Main
Google Sign In successful retrieves the authentication token from the Google Sign In API but I was unable to complete the storage and retrival of that token from the database for Sign In. A lot of the problems faced came from usinga lot to time work try to understand google's vast API and ecosystem. 

In the end how it became clear that my difficulty in integrating Google Calendar came from not understanding earlier on that significant portions of user functionality are only avaiable throught setting up as "serivce account" which is very diffierent from a regualr google account. For service accounts to function as intended one must register a payment account and have it linked to an organisation.

What I have made in the end turned out to be as far as I could make it trying to work with what is free on the Google Developer console without registering an account and organisation for payment.

There's still a lot I have to learn on regarding the Google Developer Console, but I was able to learn about some of the setup required and considerations involved in Enterprise Software development as well as learning about token authentication for the first time from the view point of a developer. 

### Aside
Apart from this, some of the difficulty lay in knowing just how to integrate this with Spring's existing framework for authentication in the way which I intended to implement it, with the user already having been signed up in the system (as the system should only allow registration of users by the admin confirmed as chronic health patients or doctor's of that surgery), so that not just anyone could sign up. 

This meant I had to try to make a slightly more custom solution for Google Sign In, which brought with it, it's own complications on top of those previously mentioned. This being besdies that fact that I wanted Google Sign in to be an option that could be selected and unselected by users, as more an appendage to the regular account, which doesn't seem to be a consideration in Spring Framework, which is shown to support third party sign up and sign in, but not for having an existing account on the platform and then to be signed in via third party authentication (or at least I was unable to find any guidance on this approach from any existing documentation of the time).

The project also does not have testing in place, mainly because I was focussed on trying to get as much of the idea realised as possible within time constraints with working on front and backends in tandem.

### Summary
Overall, as mentioned, many aspect would still require some work to be complete and though the assignment/assessment is done, I would like to add some testing and finish off project as intented just for myself for the sake of completeness and finshing up what was started.

For now, however, it serves to show my understanding of Java, HTML, CSS, Javascipt, Gradle, Spring MVC and Hibernate at the time and in future maybe updated as I am able to in between working on other personal projects.

## Modifications
I would like to go back and complete aspects of it, but for now there are some other projects I would like to work on that might prove a better learning experience and of more value currently.
<!-- In future once done: I have since gone back and have worked on completing aspects of the project not related to Google Cloud Console [see here for explaination](#main). -->

##Youtube Walkthrough

https://www.youtube.com/watch?v=2LTY80dYC0g&t=81s
