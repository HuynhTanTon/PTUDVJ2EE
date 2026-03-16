CREATE DATABASE IF NOT EXISTS course_registration
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE course_registration;

CREATE TABLE IF NOT EXISTS roles (
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS students (
    student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS student_role (
    student_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (student_id, role_id),
    CONSTRAINT fk_student_role_student FOREIGN KEY (student_id) REFERENCES students(student_id),
    CONSTRAINT fk_student_role_role FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    image VARCHAR(255),
    credits INT NOT NULL,
    lecturer VARCHAR(100) NOT NULL,
    category_id BIGINT,
    CONSTRAINT fk_course_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS enrollments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    enroll_date DATETIME NOT NULL,
    CONSTRAINT fk_enroll_student FOREIGN KEY (student_id) REFERENCES students(student_id),
    CONSTRAINT fk_enroll_course FOREIGN KEY (course_id) REFERENCES courses(id)
);

