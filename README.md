# API REST

![Status: Concluído](https://img.shields.io/static/v1?label=Status&message=Concluído&color=GREEN&style=for-the-badge%22)

**Projeto realizado durante o curso [Java e Spring Boot](https://cursos.alura.com.br/formacao-spring-boot-3) que ensina a criar uma API REST.**

O contexto da aplicação é uma API de gestão de consultas para uma clínica médica.

## Funcionalidades

- [x] Autenticação de usuários com JWT
- [x] CRUD de médicos com DTOs e validações
- [x] CRUD de pacientes com DTOs e validações
- [x] Agendamento e cancelamento de consultas

## Como executar em modo de desenvolvimento

### Pré-Requisitos:

- JDK 17+
- Maven 3.8.6
- MySQL

### Comandos:

Na pasta [resources](src/main/resources) crie o arquivo application-mysql.properties com as propriedades do banco MySQL conforme exemplificado no arquivo [application-mysql.properties.example](src/main/resources/application-mysql.properties.example).

Certifique-se de que uma instância do MySQL está sendo executada.

Na *raiz* do projeto execute o comando:

```shell
./mvnw spring-boot:run
```

### Documentação

Para encontrar a documentação da API acesse http://localhost:8080/swagger-ui/index.html.

## Tecnologias

- [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Spring Boot 3](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Flyway](https://flywaydb.org)
- [MySQL](https://www.mysql.com)
- [Swagger](https://swagger.io)
- [Lombok](https://projectlombok.org)
- [Maven](https://maven.apache.org)
- [IntelliJ IDEA](https://www.jetbrains.com/idea)
