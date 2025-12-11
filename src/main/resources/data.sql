-- 1. Criação dos Perfis de Acesso (Roles)
INSERT INTO perfis (id, nome) VALUES (1, 'ROLE_ADMIN');
INSERT INTO perfis (id, nome) VALUES (2, 'ROLE_USER');

-- 2. Criação do Usuário ADMIN
-- Senha descriptografada: 123456
-- O hash abaixo é compatível com BCrypt
INSERT INTO usuarios (id, nome, sobrenome, email, senha, is_confirmado)
VALUES (1, 'Admin', 'Sistema', 'admin@email.com', '$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe', true);

-- 3. Criação de um Usuário Comum (Opcional, para testes)
INSERT INTO usuarios (id, nome, sobrenome, email, senha, is_confirmado)
VALUES (2, 'Ciclista', 'Amador', 'user@email.com', '$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe', true);

-- 4. Vínculo entre Usuários e Perfis
-- Admin ganha perfil de ADMIN
INSERT INTO usuarios_perfis (usuarios_id, perfis_id) VALUES (1, 1);
-- User ganha perfil de USER
INSERT INTO usuarios_perfis (usuarios_id, perfis_id) VALUES (2, 2);

-- 5. Inserção de uma Atividade de Exemplo (Para o GET não vir vazio)
-- Vinculada ao usuário Admin (ID 1)
INSERT INTO atividades (id, nome, descricao, distancia, tempo, data, tipo_bicicleta, publica, usuario_id)
VALUES (1, 'Primeira Pedalada Heroku', 'Teste de deploy bem sucedido', 15.5, 45.0, '2025-12-12 08:00:00', 'Mountain Bike', true, 1);