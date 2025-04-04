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
