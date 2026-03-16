INSERT IGNORE INTO roles(name) VALUES ('ADMIN'), ('STUDENT');

-- admin: username=admin, password=123456 (BCrypt)
INSERT IGNORE INTO students(username, password, email)
VALUES (
  'admin',
  '$2a$10$7EqJtq98hPqEX7fNZaFWoO5n9A6zGyF/F7fs/3Gzdh0dX8G26OD9u',
  'admin@example.com'
);

INSERT IGNORE INTO student_role(student_id, role_id)
SELECT s.student_id, r.role_id
FROM students s, roles r
WHERE s.username='admin' AND r.name='ADMIN';

