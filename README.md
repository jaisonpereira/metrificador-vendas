# Metrificador-vendas

- Java 17
- Spring boot 3.3.2
- Junit testes unitarios


Problematica 

Crie um microserviço com os seguintes endpoints após consumir os dados dos mocks acima e retorne o que está sendo solicitado:


- GET: /compras - Retornar uma lista das compras ordenadas de forma crescente por valor, deve conter o nome dos clientes, cpf dos clientes, dado dos produtos, quantidade das compras e valores totais de cada compra.

``` java
// ordenando por valorTotalComprasCliente
http://localhost:8080/compras
```

- GET: /maior-compra/ano - (Exemplo: /maior_compra/2016) - Retornar a maior compra do ano informando os dados da compra disponibilizados, deve ter o nome do cliente, cpf do cliente, dado do produto, quantidade da compra e seu valor total.

``` java
http://localhost:8080/maior-compra/2016
```

- GET: /clientes-fieis - Retornar o Top 3 clientes mais fieis, clientes que possuem mais compras recorrentes com maiores valores.

``` java
http://localhost:8080/clientes-fieis
```

- GET: /recomendacao/cliente/tipo - Retornar uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra.
  

``` java
http://localhost:8080/recomendacao/cliente/tipo
```


A Conjectura de Collatz é um problema matemático simples de entender, mas ainda não completamente resolvido.

Basicamente, ela diz que se você pegar qualquer número inteiro positivo e aplicar as seguintes regras:

* Se o número for par, divida-o por 2,

* Se o número for ímpar, multiplique-o por 3 e some 1,

Você continuará repetindo essas regras com o resultado obtido, e eventualmente chegará ao número 1, independentemente do número inicial escolhido.

Exemplo:

começando com o número 10

10 → 5 → 16 → 8 → 4 → 2 → 1.

7 interações

começando com o número 6:

6 → 3 → 10 → 5 → 16 → 8 → 4 → 2 → 1.

9 interações

começando com o número 7

7 → 22 → 11 → 34 → 17 → 52 → 26 → 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1.

17 interações


Escreva uma função que recebe um número e retorna a quantidade de interações até chegar no 1.
