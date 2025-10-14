# 🍽️ ZéComanda

Sistema de Gestão Colaborativo para Restaurantes
# 👨‍💻 Equipe

Nome	RM
Mychel Renno	RM365496
Brenda Souza	RM365765
Thiago Albuquerque	RM366086
Isis Santana	RM366169

# 🧩 Introdução

## 🧠 Descrição do Problema

Na região, diversos restaurantes enfrentam dificuldades para arcar com os altos custos de sistemas de gestão individuais.
Para resolver esse problema, eles decidiram unir esforços e desenvolver um sistema único e compartilhado, reduzindo custos e otimizando suas operações.

A proposta é criar uma plataforma centralizada que permita o gerenciamento dos estabelecimentos e ofereça aos clientes funcionalidades como consultas, avaliações e pedidos online, de forma integrada e acessível.

## 🎯 Objetivo do Projeto

Desenvolver um backend completo utilizando Java + Spring Boot, com foco na gestão de usuários, donos de restaurante e clientes, incluindo funcionalidades de:

1. Cadastro
2. Atualização de dados
3. Troca de senha

A aplicação roda em ambiente Docker, com orquestração via Docker Compose e integração com um banco PostgreSQL, garantindo escalabilidade, replicabilidade e facilidade de implantação em diferentes ambientes.

# 🏗️ Arquitetura do Sistema
## 🧱 Padrão Adotado

O sistema foi construído seguindo o padrão Clean Architecture, assegurando baixo acoplamento, alta coesão e independência de frameworks.

As camadas principais são:

- Camada	Descrição
- Domain (Core)	Contém as entidades e regras de negócio puras, independentes de frameworks.
- Use Cases (Core)	Implementa a lógica de aplicação e orquestra o fluxo entre domínio e interfaces externas.
- Interface (API Layer)	Contém os controllers, DTOs e mappers que tratam requisições HTTP.
- Infrastructure	Responsável pela persistência (Spring Data JPA + PostgreSQL) e integrações externas.

O projeto inclui:

- Testes unitários e de integração
- Relatório de cobertura de testes com JaCoCo
- Execução via containers Docker, orquestrados por Docker Compose

# 🌐 Endpoints da API

| Endpoint                | Método     | Descrição                        |
| ----------------------- | ---------- | -------------------------------- |
| `/api/type-user`        | **POST**   | Cadastra um tipo de usuário      |
| `/api/type-user`        | **GET**    | Lista todos os tipos de usuário  |
| `/api/type-user`        | **PUT**    | Atualiza um tipo de usuário      |
| `/api/type-user/{id}`   | **DELETE** | Deleta um tipo de usuário        |
| `/api/type-user/{id}`   | **GET**    | Busca um tipo de usuário por ID  |
| `/api/users`            | **POST**   | Cadastra um novo usuário         |
| `/api/users`            | **PATCH**  | Atualiza a senha de um usuário   |
| `/api/users/{id}`       | **DELETE** | Deleta (modo lógico) um usuário  |
| `/api/users/{id}`       | **PUT**    | Atualiza um usuário existente    |
| `/api/users`            | **GET**    | Lista todos os usuários ativos   |
| `/api/customers`        | **POST**   | Cadastra um novo cliente         |
| `/api/customers`        | **DELETE** | Deleta (modo lógico) um cliente  |
| `/api/customers`        | **GET**    | Lista todos os clientes ativos   |
| `/api/customers`        | **PUT**    | Atualiza um cliente existente    |
| `/api/owners`           | **POST**   | Cadastra um novo dono            |
| `/api/owners`           | **DELETE** | Deleta um dono existente         |
| `/api/owners`           | **GET**    | Lista todos os donos ativos      |
| `/api/owners`           | **PUT**    | Atualiza um dono existente       |
| `/api/menu-item`        | **POST**   | Cadastra um novo item no menu    |
| `/api/menu-item/{id}`   | **DELETE** | Exclui um item do menu           |
| `/api/menu-item`        | **GET**    | Lista todos os itens do menu     |
| `/api/menu-item/{id}`   | **GET**    | Retorna um item por ID           |
| `/api/menu-item/{id}`   | **PUT**    | Atualiza um item do menu         |
| `/api/restaurants`      | **POST**   | Cadastra um novo restaurante     |
| `/api/restaurants/{id}` | **DELETE** | Exclui um restaurante            |
| `/api/restaurants`      | **GET**    | Lista todos os restaurantes      |
| `/api/restaurants/{id}` | **GET**    | Retorna um restaurante por ID    |
| `/api/restaurants/{id}` | **PUT**    | Atualiza dados de um restaurante |


# ⚙️ Configuração do Projeto
## 🐳 Docker Compose

O ambiente é composto por dois containers:

1️⃣ Banco de Dados — PostgreSQL

Imagem: postgres:17.5

Porta exposta: 5433 (host) → 5432 (container)

Persistência via volume Docker

Variáveis de ambiente: usuário, senha e nome do banco

2️⃣ Aplicação — Java + Spring Boot

Construída com multi-stage build:

Stage 1: maven:3.9.10-eclipse-temurin-21-alpine → compila o projeto

Stage 2: eclipse-temurin:21 → executa o .jar otimizado

Porta exposta: 8081 (host) → 8080 (container)

Dependência configurada: depends_on: db

Variáveis de ambiente para configuração de conexão com o banco

▶️ Execução Local

Certifique-se de ter o Docker e Docker Compose instalados

Clone o repositório

Renomeie o arquivo .env-sample para .env

Preencha as variáveis de ambiente

Execute o comando:

docker-compose up --build


A aplicação estará disponível em:
👉 http://localhost:8081

# ✅ Qualidade do Código

O projeto adota boas práticas de engenharia de software:

Princípios SOLID e DRY (Don't Repeat Yourself)

Clean Architecture com camadas bem definidas

Uso correto de anotações Spring (@RestController, @Repository, @Entity)

Bean Validation (@Valid, @NotBlank, @NotNull)

Testes unitários e de integração com cobertura JaCoCo

Essas práticas garantem baixo acoplamento, alta manutenibilidade e confiabilidade no sistema.

# 🧪 Testes
## 🧰 Collection do Postman

A collection para testes manuais está localizada em:

collection/tech-challenge-phase-two.postman_collection.json

# 🔍 Execução dos Testes Manuais

Os testes podem ser realizados via Postman, respeitando a ordem de execução dos endpoints — pois alguns dependem de dados criados em chamadas anteriores.

# 🔗 Endereços Externos

Vídeo de Apresentação: (inserir link aqui)

# 📄 Licença

Este projeto foi desenvolvido como parte do Tech Challenge da FIAP — fase 2.
Uso acadêmico e demonstrativo.
