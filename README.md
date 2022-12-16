# ALoan-Banks Backend Server
> Outline a brief description of your project.

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact)
<!-- * [License](#license) -->


## General Information
- Provide general information about your project here.
- What problem does it (intend to) solve?
- What is the purpose of your project?
- Why did you undertake it?
<!-- You don't have to answer all the questions - just the ones relevant to your project. -->


## Technologies Used
- Java 8
- Maven
- Spring Boot
-- Spring Boot Actuator
-- Spring Boot Data JPA
-- Spring Boot Starter Web
-- Spring Boot Devtools
-- Spring Boot Starter Test
- PostgreSQL
-- DBeaver
-- PGAdmin
- Lombok
- JUnit 5
- Mockito
- IntelliJ IDE
- SonarCloud
- Docker


## Features
The purpose of this application is to run the backend of the ALoan Bank. 
- User is able to register an account, which is POSTed into the PostreSQL database.
- User is able to login to the application, which is checked with database.
- User is able to create an account, which is POSTed into the database.
- User is able to view their account, including income and expenses. 
- User is able to view their user profile, which is retrieved from the database


## Screenshots
![Example screenshot](./img/screenshot.png)
<!-- If you have screenshots you'd like to share, include them here. -->


## Setup
SonarCloud:
  - setup account with SonarCloud with Github
  - import your organization and repository within SonarCloud
  - click branch and setup CI
  - setup sonarcloud secret
  - add properties tag to the .pom file in the backend, don't forget to push the changes
  - setup new github action workflow, paste code given by sonarcloud into /.github/workflows/build.yml


## Usage
Once the environment is setup and you run the program on the IDE of your choice (Recommend IntelliJ is it was the native IDE for this project), go to BankingApplication and run the application from the main method. Postman can be used for verification purposes, to confirm that the backend is running properly before the frontend of the application is launched. 


## Project Status
Project is: _complete_


## Room for Improvement
Include areas you believe need improvement / could be improved. Also add TODOs for future development.

Room for improvement:
- Improvement to be done 1
- Improvement to be done 2

To do:
- Feature to be added 1
- Feature to be added 2


## Acknowledgements
Give credit here.
- This project was inspired by...
- This project was based on [this tutorial](https://www.example.com).
- Many thanks to...


## Contact
Created by [@flynerdpl](https://www.flynerd.pl/) - feel free to contact me!


<!-- Optional -->
<!-- ## License -->
<!-- This project is open source and available under the [... License](). -->

<!-- You don't have to include all sections - just the one's relevant to your project -->
