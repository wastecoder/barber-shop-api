# Sistema de Agendamento para Barbearia

## Visão Geral
Este projeto consiste em um sistema de agendamento para uma barbearia, composto por duas partes:
uma aplicação front-end desenvolvida em Angular e uma API back-end implementada com Spring Boot.
O sistema permite o cadastro e gerenciamento de clientes, bem como a realização de agendamentos, garantindo que cada horário de serviço tenha uma duração fixa de uma hora.

Os repositórios do front-end e do back-end são separados, sendo necessário configurar ambos para o funcionamento completo da aplicação.

## Funcionalidades
- __Cadastro de Clientes:__ Possibilidade de adicionar, editar e excluir clientes.
- __Agendamento de Horários:__ O usuário seleciona uma data e um horário inicial; o término do serviço é automaticamente definido para uma hora depois.
- __Restrição em Agendamentos:__ Os agendamentos criados podem apenas ser excluídos, não editados.
- __Interface Amigável:__ Tema escuro, notificações visuais para informações de sucesso e erro.


## Tecnologias Utilizadas
- __Spring Boot:__ Para desenvolvimento da API REST.
- __PostgreSQL:__ Banco de dados relacional.
- __Flyway:__ Gerenciamento de migrations do banco de dados.
- __Swagger:__ Documentação da API para facilitar testes e integração.
- __DTOs e Validação:__ Para garantir segurança e consistência dos dados.


## Configuração
O back-end deve estar rodando na porta __8080__ para que o front-end consiga se conectar.

Repositório do front-end: [Barber-Shop-UI](https://github.com/wastecoder/barber-shop-ui)


## Instalação
1. Clone o repositório na pasta desejada.
2. Configure o banco de dados:
  - Para facilitar, use o banco de dados PostgreSQL.
  - No seu SGBD, crie um banco de dados chamado __"barber-shop"__ sem tabelas.
  - Em [application-dev](https://github.com/wastecoder/barber-shop-api/blob/main/src/main/resources/application-dev.yml), coloque o _username_ e a _password_ adequado ao seu SGBD.
3. __(opcional)__ Caso queira usar outro banco de dados, altere:
  - As [migrations](https://github.com/wastecoder/barber-shop-api/tree/main/src/main/resources/db/migration) para não dar erro de sintaxe.
  - A [conexão](https://github.com/wastecoder/barber-shop-api/blob/main/src/main/resources/application-dev.yml): _url, username, password, driver-class-name e dialect_.
  - Adicione o driver de conexão no [pom.xml](https://github.com/wastecoder/barber-shop-api/blob/main/pom.xml).
4. Execute o arquivo __BarberShopApiApplication__.
