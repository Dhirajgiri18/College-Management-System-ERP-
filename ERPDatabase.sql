CREATE DATABASE erp;
USE erp;

-- Admin Table
CREATE TABLE `Admin` (
    `Sr_No` INT NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(45),
    `User_Id` VARCHAR(45) UNIQUE,
    `Password` VARCHAR(255),
    PRIMARY KEY (`Sr_No`)
);

-- Insert Sample Admin Data
INSERT INTO Admin (Name, User_Id, Password) 
VALUES ('Dhiraj', 'Admin1', 'Admin1');

INSERT INTO Admin (Name, User_Id, Password) 
VALUES ('Pranav', 'Admin2', 'Admin2');

-- Student Table
CREATE TABLE `Student` (
    `Sr_No` INT NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(45) DEFAULT NULL,
    `Course` VARCHAR(45) DEFAULT NULL,
    `Roll_No` VARCHAR(45) DEFAULT NULL,
    `DateOfBirth` DATE,
    `Contact` VARCHAR(15) DEFAULT NULL,
    `EmailID` VARCHAR(45) DEFAULT NULL UNIQUE,
    `Student_Id` VARCHAR(45) DEFAULT NULL,
    `Password` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`Sr_No`)
);

-- Courses Table
CREATE TABLE `Courses` (
    `Sr_No` INT NOT NULL AUTO_INCREMENT,
    `CourseCode` VARCHAR(45) DEFAULT NULL UNIQUE,
    `CourseName` VARCHAR(45) DEFAULT NULL,
    `SemesterorYear` VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (`Sr_No`)
);

-- Feedback Table
CREATE TABLE `Feedback` (
    `Sr_No` INT NOT NULL AUTO_INCREMENT,
    `CourseName` VARCHAR(45) DEFAULT NULL,
    `Comment` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`Sr_No`)
);

-- Insert Sample Student Data
INSERT INTO Student (Name, Course, Roll_No, DateOfBirth, Contact, EmailID, Student_Id, Password) 
VALUES ('Omkar', 'CS', 'SYCOC316', '2000-01-01', '12344556', 'omkar@gmail.com', 'Stud1', 'Stud1');

-- Create Course Table with Corrected Names
CREATE TABLE `Course` (
    `Sr_No` INT NOT NULL AUTO_INCREMENT,
    `Course_No` INT,
    `Course_Name` VARCHAR(45),
    `Semester` INT,
    PRIMARY KEY (`Sr_No`)
);

-- Example of a Select Query
SELECT Name, Course, Roll_No, DateOfBirth, Contact, EmailID FROM Student;
