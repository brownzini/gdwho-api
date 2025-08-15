-- Inserindo na tabela de usuarios
INSERT INTO users (username, password, role, data_response, created_at) VALUES ('master', '$2a$10$EgaEA06/7BOmfVSvTOq8K.cEIO89U0AKMmQ0vwKvcT3xNZvqpdyZm', 'ADMIN', NULL, NOW());
INSERT INTO users (username, password, role, data_response, created_at) VALUES ('jeguelson', '$2a$10$EgaEA06/7BOmfVSvTOq8K.cEIO89U0AKMmQ0vwKvcT3xNZvqpdyZm', 'USER', NULL, NOW());

-- INSERT INTO users_data (value, user_id) VALUES ('conteudo teste de db', 1);
INSERT INTO users_data (value, user_id) VALUES ('teste de data para user 2', 2);

-- INSERT INTO users_entries (input, output, label, user_id) VALUES ('calma calabreso', 'calma o nada', 1.0, 1);
INSERT INTO users_entries (input, output, label, user_id) VALUES ('testando um teste', 'é um teste testado', 1.0, 2);