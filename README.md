🚴‍♂️ Projeto TDS — Controlador de Atividades Ciclísticas

Este repositório foi desenvolvido como parte da disciplina Tópicos em Desenvolvimento de Software (TDS).
O projeto consiste em uma aplicação Java utilizando o framework Spring, voltada ao gerenciamento de atividades ciclísticas, aplicando conceitos de arquitetura, segurança e boas práticas de desenvolvimento.

🧩 Tecnologias Utilizadas

Java 17+

Spring Boot

Spring Data JPA

Spring Security

JWT (JSON Web Token)

Padrão DTO

Maven

🗂️ Estrutura do Projeto
src/
 └── main/
      ├── java/cstsi_tads_eduardo/
      │     ├── Atividade/       → CRUD de atividades ciclísticas
      │     ├── Grupo/           → CRUD de grupos de usuários
      │     ├── Rota/            → CRUD de rotas de ciclismo
      │     ├── Usuario/         → CRUD e autenticação de usuários
      │     ├── autenticacao/    → Lógica de autenticação e JWT
      │     ├── infra/           → Configurações e utilitários
      │     └── TadsEduardoApplication.java
      └── resources/             → Arquivos de configuração da aplicação

⚙️ Funcionalidades Principais

Registro, listagem, atualização e exclusão de atividades ciclísticas

Gerenciamento de usuários e atividades

Autenticação e autorização via JWT

Camada DTO para transferência segura de dados

Integração com banco de dados relacional via Spring Data JPA

🔒 Autenticação JWT

A aplicação utiliza autenticação baseada em JSON Web Token (JWT) para controle de acesso às rotas.
Após o login, o usuário recebe um token que deve ser enviado no cabeçalho das requisições às rotas protegidas.

🧠 Conceitos Aplicados

Arquitetura em camadas (Controller, Service, Repository)

Padrão DTO

Boas práticas RESTful

Autenticação e segurança com JWT

Utilização do ecossistema Spring

👨‍💻 Autor

Eduardo Torres
Disciplina: Tópicos em Desenvolvimento de Software (TDS)
Instituição: IFSUL – Campus Pelotas
