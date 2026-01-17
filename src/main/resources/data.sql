INSERT INTO roles (id, name) VALUES
                                 (1, 'ADMIN'),
                                 (2, 'STAFF'),
                                 (3, 'CUSTOMER');
INSERT INTO users (id, full_name, email, password, active) VALUES
                                                               (1, 'Admin User', 'admin@mail.com', 'admin123', true),
                                                               (2, 'Dr. Sharma', 'staff1@mail.com', 'staff123', true),
                                                               (3, 'Dr. Mehta', 'staff2@mail.com', 'staff123', true),
                                                               (4, 'Anil Kumar', 'customer1@mail.com', 'cust123', true),
                                                               (5, 'Rohit Singh', 'customer2@mail.com', 'cust123', true);
INSERT INTO user_roles (user_id, role_id) VALUES
                                              (1, 1), -- Admin
                                              (2, 2), -- Staff
                                              (3, 2), -- Staff
                                              (4, 3), -- Customer
                                              (5, 3); -- Customer
INSERT INTO user_profiles (id, phone_number, address, user_id) VALUES
                                                                   (1, '9876543210', 'Bangalore', 1),
                                                                   (2, '9876543220', 'Delhi', 2),
                                                                   (3, '9876543230', 'Mumbai', 3),
                                                                   (4, '9876543240', 'Chennai', 4),
                                                                   (5, '9876543250', 'Hyderabad', 5);
INSERT INTO branches (id, name, address, active) VALUES
                                                     (1, 'City Hospital - Main Branch', 'MG Road, Bangalore', true),
                                                     (2, 'City Hospital - North Wing', 'Yelahanka, Bangalore', true);
INSERT INTO services (id, name, duration_in_minutes, active, branch_id) VALUES
                                                                            (1, 'Doctor Consultation', 15, true, 1),
                                                                            (2, 'Blood Test', 10, true, 1),
                                                                            (3, 'X-Ray', 20, true, 2);
INSERT INTO staff_services (service_id, staff_id) VALUES
                                                      (1, 2), -- Dr. Sharma → Consultation
                                                      (1, 3), -- Dr. Mehta → Consultation
                                                      (2, 2), -- Dr. Sharma → Blood Test
                                                      (3, 3); -- Dr. Mehta → X-Ray
INSERT INTO appointments
(id, appointment_time, current_status, service_id, user_id)
VALUES
    (1, '2026-01-15 10:00:00', 'SCHEDULED', 1, 4),
    (2, '2026-01-15 10:15:00', 'SCHEDULED', 1, 5),
    (3, '2026-01-15 11:00:00', 'SCHEDULED', 2, 4);
INSERT INTO queue_tokens
(id, token_number, queue_date, active, appointment_id, service_id)
VALUES
    (1, 1, '2026-01-15', true, 1, 1),
    (2, 2, '2026-01-15', true, 2, 1),
    (3, 1, '2026-01-15', true, 3, 2);
INSERT INTO status_history
(id, status, changed_at, appointment_id)
VALUES
    (1, 'SCHEDULED', '2026-01-15 09:50:00', 1),
    (2, 'SCHEDULED', '2026-01-15 10:05:00', 2),
    (3, 'SCHEDULED', '2026-01-15 10:45:00', 3);

