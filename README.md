# Desafio Dryve - Cadastro de Veículos

Este é um projeto desenvolvido como parte do desafio proposto pela empresa Dryve. O objetivo é criar um serviço de cadastro de veículos, obtendo dados básicos e consultando o valor na tabela FIPE para posterior consulta. O projeto utiliza tecnologias como Spring Boot, Java, Maven e banco de dados relacional.

## Tecnologias Utilizadas
- Spring Boot
- Java
- Maven
- Banco de Dados Relacional (H2)

## Passo a Passo para Execução do Projeto

### Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:
- Java JDK (versão 8 ou superior)
- Maven
- Docker (opcional)

### Configuração do Banco de Dados
O projeto utiliza o banco de dados H2 como uma opção de banco de dados embutido. Para configurar o banco de dados, siga as etapas abaixo:

1. Abra o arquivo `application.properties` localizado na pasta `src/main/resources`.
2. Verifique as configurações do banco de dados:
```

spring.datasource.url=jdbc:h2:file:/Your/path/to/fipe-dryve/fipe-dryve/db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto= update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-ui
spring.h2.console.settings.web-allow-others=true
```
Certifique-se de fornecer o caminho correto para o diretório do banco de dados em `spring.datasource.url`.

Você pode acessar o console do H2 através do http://localhost:8080/h2-ui

### Execução do Projeto
Siga as etapas abaixo para executar o projeto:

1. Abra o terminal e navegue até a raiz do projeto.
2. Execute o comando `mvn clean install` para compilar e empacotar o projeto.
3. Execute o comando `mvn clean spring-boot:run` para iniciar o aplicativo.

O aplicativo será iniciado na porta 8080, você pode acessar os endpoints a partir do `http://localhost:8080`.

### Testes
O projeto inclui testes de unidade e integração para garantir o funcionamento correto das funcionalidades. Para executar os testes, execute o seguinte comando:
```
mvn test
```

### Docker (opcional)
Se você preferir executar o projeto usando Docker, siga as etapas abaixo:

1. Certifique-se de ter o Docker instalado e em execução em seu ambiente.
2. No terminal, navegue até a raiz do projeto.
3. Execute o comando `docker build -t fipe-dryve:latest .` para criar a imagem Docker.
4. Execute o comando `docker run -d -p 8080:8080 fipe-dryve:latest` para iniciar o contêiner Docker.

O aplicativo estará disponível em `http://localhost:8080`.

## Endpoints do Serviço

### Cadastro de Veículos

Para cadastrar um veículo, faça uma requisição `POST` para o endpoint `/veiculos` com os seguintes dados(use dados de acordo com a database no final do read.me) no corpo da requisição:

```
curl -X POST -H "Content-Type: application/json" -d '{
  "placa": "XYZ-1234",
  "precoAnuncio": 1234.56,
  "ano": 1987,
  "precoFipe": 1234.56,
  "dataCadastro": "2020-05-18",
  "modelo": {
    "id": "5bc16064-d3ee-4aed-a264-a914233d0c4f",
    "name": "147 C/ CL",
    "fipeId": 437,
    "marca": {
      "id": "ca43ec74-5bb0-4288-ab11-5df094ca4dc4",
      "name": "FIAT",
      "fipeId": 21
    }
  }
}' http://localhost:8080/veiculos
```

### Consulta de Marcas

Para consultar as marcas cadastradas, faça uma requisição `GET` para o endpoint `/marcas`:
```
curl -X GET http://localhost:8080/marcas
```

### Consulta de Marca por ID

Para consultar uma marca específica pelo seu ID, faça uma requisição `GET` para o endpoint `/marcas/{id}` substituindo `{id}` pelo ID da marca desejada:
```
curl -X GET http://localhost:8080/marcas/ca43ec74-5bb0-4288-ab11-5df094ca4dc4
```

### Consulta de Veículo por Placa

Para consultar um veículo pelo seu número de placa, faça uma requisição `GET` para o endpoint `/veiculos/{placa}` substituindo `{placa}` pela placa do veículo desejado:
```
curl -X GET http://localhost:8080/veiculos/XYZ-1234
```
Banco de Dados
O projeto utiliza um banco de dados H2 embutido. As tabelas marca e modelo já foram criadas com alguns registros de exemplo.



```
INSERT INTO marca (id, name, fipe_id) VALUES ('ca43ec74-5bb0-4288-ab11-5df094ca4dc4', 'FIAT', 21);
INSERT INTO marca (id, name, fipe_id) VALUES ('c0225595-e293-477b-8758-384872470f00', 'FORD', 22);
INSERT INTO marca (id, name, fipe_id) VALUES ('e66e8451-a442-4344-bbd9-17f249d9eea4', 'CHEVROLET', 23);
INSERT INTO modelo (id, name, fipe_id, id_marca) VALUES ('5bc16064-d3ee-4aed-a264-a914233d0c4f', '147 C/ CL', '437', 'ca43ec74-5bb0-4288-ab11-5df094ca4dc4');
INSERT INTO modelo (id, name, fipe_id, id_marca) VALUES ('e16e9ed4-43c6-4882-9f2f-12ca5aad6e7e', 'ARGO 1.0 6V Flex.', '8315', 'ca43ec74-5bb0-4288-ab11-5df094ca4dc4');
INSERT INTO modelo (id, name, fipe_id, id_marca) VALUES ('6533c337-f745-437c-8adf-a20895275cbf', 'Doblo ESSENCE 1.8 Flex 16V 5p', '5536', 'ca43ec74-5bb0-4288-ab11-5df094ca4dc4');
INSERT INTO modelo (id, name, fipe_id, id_marca) VALUES ('c08f77df-c6e0-4e73-a378-42bb83361266', 'Belina GL 1.8 / 1.6', '657', 'c0225595-e293-477b-8758-384872470f00');
INSERT INTO modelo (id, name, fipe_id, id_marca) VALUES ('deaf80e7-600c-4a35-af52-1fc7f1e967a8', 'EcoSport XL 1.6/ 1.6 Flex 8V 5p', '680', 'c0225595-e293-477b-8758-384872470f00');
INSERT INTO modelo (id, name, fipe_id, id_marca) VALUES ('b1c9a613-29ee-4171-a5ff-e7d98a0fdaac', 'Fiesta SEL Style 1.6 16V Flex Mec. 5p', '7754', 'c0225595-e293-477b-8758-384872470f00');
INSERT INTO modelo (id, name, fipe_id, id_marca) VALUES ('828bd4bf-ea80-4449-bf8f-154cda91d864', 'Astra Eleg. 2.0 MPFI FlexPower 8V 5p Aut', '940', 'e66e8451-a442-4344-bbd9-17f249d9eea4');
INSERT INTO modelo (id, name, fipe_id, id_marca) VALUES ('cc0b4033-9624-400d-b45d-84cceb7e0441', 'Celta Life 1.0 MPFI VHC 8V 3p', '997', 'e66e8451-a442-4344-bbd9-17f249d9eea4');
INSERT INTO modelo (id, name, fipe_id, id_marca) VALUES ('7a9e2990-b356-40e6-b0b5-c26d38e3f5bb', 'Meriva Joy 1.8 MPFI 8V FlexPower', '1093', 'e66e8451-a442-4344-bbd9-17f249d9eea4');
```
Esses comandos inserem registros de exemplo nas tabelas marca e modelo, correspondendo às marcas e modelos mencionados nos exemplos de requisições CURL.



## Considerações Finais

Este projeto foi desenvolvido como parte do desafio proposto pela empresa Dryve. Utilizou-se tecnologias como Spring Boot, Java, Maven e o banco de dados relacional H2. O serviço permite o cadastro de veículos, consulta de marcas e veículos, e utiliza a tabela FIPE para obter informações adicionais.
