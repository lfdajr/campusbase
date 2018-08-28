# CampusBase
Repositório de aulas na web

## Tecnologias envolvidas
* Java/Spring (backend)
* Angular 6 (frontend)
* Ionic (frontend - mobile)

## Funcionalidades
* Busca de turmas do usuário logado
* Detalhamento da turma (em construção)


## Criar arquivo application.yml

server.port: 9966
spring.profiles: default
br.com.campusbase.security.jwt:
  tokenExpirationTime: 60 # Number of minutes
  refreshTokenExpTime: 60 # Minutes
  tokenIssuer: http://campusbase.com.br
  tokenSigningKey: XXXX
  
br.com.campusbase.fs:
    uploadDir: /home/campusbase/upload
  
spring.datasource:
  url: jdbc:mysql://localhost/ABCC
  username: root
  password: root
  driverClassName: com.mysql.jdbc.Driver
spring.jpa.properties.hibernate.dialect: org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto: none
spring.jpa.hibernate.show-sql: true
spring.jpa.hibernate.naming.physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
logging.level.org.hibernate.SQL: DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder: TRACE
spring.jackson.serialization.FAIL_ON_EMPTY_BEANS: false

