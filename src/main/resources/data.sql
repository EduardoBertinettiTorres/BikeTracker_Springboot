-- 1. Inserir Perfis
INSERT INTO perfis(id, nome) VALUES (1, 'ROLE_ADMIN');
INSERT INTO perfis(id, nome) VALUES (2, 'ROLE_USER');

-- 2. Inserir Usuário (A SENHA AQUI É '123456' Criptografada com BCrypt)
-- IMPORTANTE: O email deve bater com o setupTest do Passo 1
INSERT INTO usuarios(id, nome, email, senha) VALUES (1, 'Admin', 'admin@email.com', '$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe');

-- 3. Vincular perfil ao usuário
INSERT INTO usuarios_perfis(usuarios_id, perfis_id) VALUES(1, 1);

-- 4. Inserir Atividades de Exemplo (Vinculadas ao usuario_id 1)
INSERT INTO atividades (id, nome, descricao, distancia, tempo, data, tipo_bicicleta, publica, usuario_id) VALUES
                                                                                                              (1, 'Pedalada Matinal', 'Volta no parque', 10.5, 30.0, '2025-11-13 08:00:00', 'MTB', true, 1),
                                                                                                              (2, 'Treino Intenso', 'Subida da serra', 25.0, 90.0, '2025-11-14 07:00:00', 'Speed', false, 1),
                                                                                                              (3, 'Passeio Domingo', 'Com a familia', 5.0, 45.0, '2025-11-15 16:00:00', 'Urbana', true, 1);