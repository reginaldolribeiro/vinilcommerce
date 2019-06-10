[![Build Status](https://travis-ci.org/reginaldolribeiro/vinilcommerce.svg?branch=master)](https://travis-ci.org/reginaldolribeiro/vinilcommerce)
# Vinilcommerce

Backend de um ecommerce de discos de vinil com cashback, expondo uma API RESTFull com dados no formato JSON. 
Utilizada API do Spotify para carga inicial dos discos por gênero musical (Rock, Pop, MPB e Classic).

#### Regras de cashback

| Gênero  | Domingo | Segunda | Terça | Quarta | Quinta | Sexta | Sábado |
|---------|---------|---------|-------|--------|--------|-------|--------|
| POP     | 25%     | 7%      | 6%    | 2%     | 10%    | 15%   | 20%    |
| MPB     | 30%     | 5%      | 10%   | 15%    | 20%    | 25%   | 30%    |
| CLASSIC | 35%     | 3%      | 5%    | 8%     | 13%    | 18%   | 25%    |
| ROCK    | 40%     | 10%     | 15%   | 15%    | 15%    | 20%   | 40%    |

  #### Endpoints RESTFull
  1. (GET) Consultar o catálogo de discos de forma paginada, filtrando por gênero e
  ordenando de forma crescente pelo nome do disco:   
   - Sem paginação: http://localhost:8080/api/album?genre=rock
   - Com paginação: http://localhost:8080/api/album?genre=rock&size=100&page=1

  2. (GET) Consultar o disco pelo seu identificador: 
   - http://localhost:8080/api/album/200

  3. (GET) Consultar todas as vendas efetuadas de forma paginada, filtrando pelo range
  de datas (inicial e final) da venda e ordenando de forma decrescente pela
  data da venda:    
   - Sem paginação: http://localhost:8080/api/sale?start=01/06/2019&end=10/06/2019
   - Com paginação: http://localhost:8080/api/sale?start=01/06/2019&end=10/06/2019&size=1&page=0

  4. (GET) Consultar uma venda pelo seu identificador: 
   - http://localhost:8080/api/sale/1

  5. (POST) Registrar uma nova venda de discos calculando o valor total de cashback
  considerando a tabela: 
   - http://localhost:8080/api/sale
    
    Body do request JSON (Exemplo)
    
    {
      "customer": {
        "id": 1
      },
      "itens": [
        {
          "product": {
            "id": 1
          }
        },
        {
          "product": {
            "id": 2
          }
        },
        {
          "product": {
            "id": 36
          }
        }
      ]
    }   
   
  #### Setup
  1. Instalar o banco de dados MySQL, versão mais recente.
  2. Criar o database chamado "cashback".
  2. Realizar o download do projeto aqui no Github.
  2. Extrair o arquivo vinilcommerce.zip
  3. Entrar na pasta do projeto.
  4. Executar o comando mvn spring-boot:run
  5. Após a aplicação subir, acesse a URL http://localhost:8080/
  
  #### Tecnologias utilizadas
  1. Java
  2. Spring Boot
  3. Testes unitários com JUnit e Rest Assured
  4. Maven
  5. MySQL
  6. Flyway
  7. Travis CI
  8. Spotify Web API Java: https://github.com/thelinmichael/spotify-web-api-java
