# Pokémon API — Spring Boot

API RESTful com CRUD completo em Oracle, usando JDBC, DAO, BO, Bean Validation e CORS.

## Endpoints

- GET /pokemons
- GET /pokemons/{codigo}
- POST /pokemons
- PUT /pokemons/{codigo}
- DELETE /pokemons/{codigo}

## Variáveis de ambiente

DB_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521/SEU_SERVICE_NAME  
DB_USER=RM569675  
DB_PASSWORD=sua_senha

## Executar localmente

1. Rode `Database.sql` no Oracle SQL Developer.
2. Configure as variáveis de ambiente.
3. Execute `PokemonApiApplication`.
4. Teste em `http://localhost:8080/pokemons`.

## Build

./mvnw clean -DskipTests package

## Deploy

Suba para o GitHub e crie um Web Service no Render usando o Dockerfile.