# ResFood API

O ResFood é um projeto backend utilizado como base para aprender os principais fundamentos
de uma API REST, que são extremamente essenciais para um bom desenvolvimento de software,
atraves do projeto consigo mostrar as minhas abilidades adiquiridas ao decorrer dos meus
estudos.

Esse repositório contém o código-fonte da API ResFood.

**Conteúdo:**

- [ResFood API](#resfood-api)
  - [Tecnologias e Ferramentas Utilizadas](#tecnologias-e-ferramentas-utilizadas)
  - [Instalar e rodar o projeto](#instalar-e-rodar-o-projeto)
    - [Dependências globais](#dependências-globais)
    - [Dependências locais](#dependências-locais)
    - [Rodar o projeto](#rodar-o-projeto)
      - [Clonar o repositório](#clonar-o-repositório)
      - [Configurações iniciais](#configurações-iniciais)
      - [Iniciar a aplicação](#iniciar-a-aplicação)
      - [Executar os testes de API](#executar-os-testes)

## Tecnologias e Ferramentas Utilizadas

- Linguagem Java 17+
- Framework: Spring Boot
- Módulos Spring: Spring Data JPA, Spring Security (OAuth2/JWT), Spring MVC,
- Banco de Dados: MySQL
- Migração de Schema: Flyway
- Documentação: OpenAPI 3 / Swagger UI
- Testes: JUnit 5, REST Assured,
- Outras Ferramentas: Lombok, Docker
- Entre outras:
  - Injeção de dependências
  - Modelagem de endpoints
  - Pool de conexões com hikariCP
  - Tratamento de Exceções
  - Validação com Bean validation
  - Value Objects padrão DTO
  - (Etc...)

## Instalar e rodar o projeto

Rodar o ResFood em sua máquina local é uma tarefa extremamente simples.

### Dependências globais

Você precisa ter duas principais dependências instaladas:

- Maven (mvn), utilize a versão mais recente disponível desse gerenciador de pacotes
- um jdk, que é um kit para desenvolver e rodar projetos em java (utilize a versão 17+ do java)
- mySQL instalado, apenas utilize a versão mais recente, saiba que o projeto ainda não foi concluido

Verifique a versão do seu java instalado:

```bash
java --version
```

### Dependências locais

Com o repositório clonado e as dependências globais instaladas, você pode instalar as dependências locais do projeto:

O próprio maven se encarregará de baixar todas as dependencias locais localizadas no `pom.xml` do projeto ao iniciar a aplicação.

### Rodar o projeto

#### Clonar o repositório

Para clonar o repositório é muito simples basta executar o seguinte comando utilizando como dependencia o git que é um versionador de projetos:

```bash
git clone https://github.com/wislansantos/resfood-restaurante-api.git
```

Se quiser pode ainda clonar para uma pasta com nome especifico:

```bash
git clone https://github.com/wislansantos/resfood-restaurante-api.git pasta-do-projeto
```

Para ver o código basta entrar na pasta criada pelo git ou especificada por você:

- No linux:
  
  ```bash
  cd pasta-do-projeto
  ```

#### Configurações iniciais

- Antes de iniciar a aplicação é necessário configurar um usuario no banco de dados com nome e senha, depois devemos startar o banco, não se preocupe por enquanto com configurações mais avançadas, pois ainda não é um projeto em produção.
- Para se conectar com o banco altere o arquivo `application.properties` em `src/main/resources` reatribuindo novos valores as propriedades de nome de usuario e senha do banco
  - `spring.datasource.username=seu-usuario`
  - `spring.datasource.password=sua-senha`

#### Iniciar a aplicação

Para rodar o projeto localmente, basta executar o comando abaixo:

```bash
mvn spring-boot:run
```

Tambem é possível executar a aplicação sem possuir a dependêvia global `Maven`:

 - No Linux:
   - Damos permissão de execução ao arquivo `mvnw' com:
     ```bash
     chmod +x mvnw
     ```
   - executamos ele e iniciamos a aplicação com:
     ```bash
     ./mvnw spring-boot:run
     ```
  - No windows:
    - Apenas executamos o comando abaixo:
      ```bash
      mvnw spring-boot:run
      ```
   

Isto irá automaticamente rodar serviços como Banco de dados (incluindo as Migrations) e irá expor um Serviço Web (API) no seguinte endereço:

```bash
http://localhost:8080/
http://localhost:8080/restaurantes
```

#### Executar os testes

Para rodar os testes da API basta executar o seguinte comando:

```bash
mvn verify
```

Ou utilizar os Wrappers do maven como ja vimos antes

Observações:

- Para derrubar todos os serviços, basta utilizar as teclas `CTRL+C`, que é o padrão dos terminais para matar
- A documentação das operações ainda serão implementadas
