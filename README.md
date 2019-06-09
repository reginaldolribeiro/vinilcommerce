[![Build Status](https://travis-ci.org/reginaldolribeiro/vinilcommerce.svg?branch=master)](https://travis-ci.org/reginaldolribeiro/vinilcommerce)
# vinilcommerce

  ## Endpoints
  1. Consultar o catálogo de discos de forma paginada, filtrando por gênero e
  ordenando de forma crescente pelo nome do disco:    
    http://localhost:8080/api/album?genre=ROCK
    http://localhost:8080/api/album?genre=ROCK&size=100&page=1

  2. Consultar o disco pelo seu identificador: http://localhost:8080/api/album/200

  3. Consultar todas as vendas efetuadas de forma paginada, filtrando pelo range
  de datas (inicial e final) da venda e ordenando de forma decrescente pela
  data da venda:  
    http://localhost:8080/api/sale?start=01/06/2019&end=10/06/2019
    http://localhost:8080/api/sale?start=01/06/2019&end=10/06/2019&size=1&page=0

  4. Consultar uma venda pelo seu identificador: http://localhost:8080/api/sale/1

  5. Registrar uma nova venda de discos calculando o valor total de cashback
  considerando a tabela: http://localhost:8080/api/sale
    
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
    
    
