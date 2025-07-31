-- Inserindo na tabela de usuarios
INSERT INTO users (username, password, role, data_response, created_at) VALUES ('master', '$2a$10$EgaEA06/7BOmfVSvTOq8K.cEIO89U0AKMmQ0vwKvcT3xNZvqpdyZm', 'ADMIN', null, NOW());
INSERT INTO users (username, password, role, data_response, created_at) VALUES ('jeguelson', '$2a$10$EgaEA06/7BOmfVSvTOq8K.cEIO89U0AKMmQ0vwKvcT3xNZvqpdyZm', 'USER', null, NOW());

INSERT INTO users_entries (input, output, label, user_id) VALUES ('calma calabreso', 'calma o krl', 1.0, 2);