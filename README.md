# Order Request

## Descrição

O Order Request é uma aplicação desenvolvida em Java utilizando o framework Spring Boot para gerenciar
pedidos. O sistema permite criar, atualizar, buscar e deletar pedidos, além de calcular o valor total de
um pedido com base nos itens associados, aplicando descontos quando aplicável.

## Funcionalidades

**Criar Item**

**Atualizar Item**

**Encontrar Item**

**Deletar Item**

**Criar Pedido**

**Atualizar Pedido**

**Encontrar Pedido**

**Deletar Pedido**

## Tecnologias Utilizadas

**Java 17**

**Spring Boot 3.3**

**Spring Data JPA**

**PostgresSql 16**

**Maven**

## Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina as seguintes ferramentas:

Java 17 ou superior

Maven 3.6.3 ou superior

PostgreSQL 16 ou superior

IDE (IntelliJ IDEA, Eclipse, VSCode, etc.)

## Instalação

### Clone o projeto

Abra o terminal e execute o seguinte comando para clonar o repositorio

```bash
git clone https://github.com/gustavohpaula/OrderRequest.git
cd orderRequest
```

### Configuração do Banco

Abra o Projeto no seu IDE e configure as propriedades do Spring Boot para conectar ao PostgreSql

No arquivo `application.properties` localizado em src/main/resources/application.properties. Atualize com
as credenciais do banco de dados que você criou em sua maquina

## Compilar e o Projeto

### Compilar o Projeto

No terminal dentro do diretorio do projeto execute o comando :

```bash
mvn clean install
```

### Rodar a Aplicação

para rodar a aplicação use o comando

```bash
mvn spring-boot:run
```

## Uso

### Endpoints da API

| Método | Endpoint                      | Descrição                            |
|--------|-------------------------------|--------------------------------------|
| POST   | `/item/createItem`            | Cria um novo item                    |
| GET    | `/item/{id}`                  | Busca um item pelo ID                |
| PUT    | `/item/updateItem/{id}`       | Atualiza um item existente           |
| DELETE | `/item/deleteItem{id}`        | Deleta um item existente             |
| GET    | `/getAllItems/{page}&{size}`  | Lista todos os pedidos com paginação |
| POST   | `/order/createOrder`          | Cria um novo pedido                  |
| GET    | `/order/{id}`                 | Busca um pedido pelo ID              |
| PUT    | `/order/updateOrder{id}`      | Atualiza um pedido existente         |
| DELETE | `/orders/deleteOrder{id}`     | Deleta um pedido existente           |
| GET    | `/getAllOrders/{page}&{size}` | Lista todos os pedidos com paginação |

### Exemplos de JSON

#### create e Update Item

```
{
	"name": "teste",
	"value": "10.00",
	"itemType": 0,
	"activated": 1
}
```
`itemType é o tipo do item, 0 para produto e 1 para serviço`
`activated é para saber se o item está ativo ou não`

#### Resposta Esperada Ao criar, atualizar e encontrar um item
```
{
	"id": "2e7057e6-e600-43a7-acbc-d3a0d656e999",
	"name": "teste",
	"itemValue": 10.00,
	"itemType": "PRODUCT",
	"activated": true
}
```

#### Create e Update Order
```
{
	{
	"orderItems": [
        {
            "id": "7c4a2a35-6823-46b0-860f-3ab4b8cf2438",
            "name": "teste",
            "itemValue": 10.00,
            "itemType": "PRODUCT",
            "activated": true
        },
		{
            "id": "35005cd8-3666-4d0d-b7cd-0016b7f60c6d",
            "name": "teste",
            "itemValue": 10.00,
            "itemType": "SERVICE",
            "activated": true
		},
		{
            "id": "2e7057e6-e600-43a7-acbc-d3a0d656e999",
            "name": "teste",
            "itemValue": 10.00,
            "itemType": "PRODUCT",
            "activated": true
		}
		],
	"discountPercentage": "0.5",
	"situation": 1
}
}
```
`situation é a situação do pedido sendo 0 para fechado e 1 para aberto`
#### Resposta Esperada Ao criar, atualizar e encontrar um pedido
```
{
	"id": "d1a2ce89-9c3d-41a8-ac7e-f63a2e8e4407",
	"orderItens": [
		{
			"id": "7c4a2a35-6823-46b0-860f-3ab4b8cf2438",
			"name": "teste",
			"itemValue": 10.00,
			"itemType": "PRODUCT",
			"activated": true
		},
		{
			"id": "35005cd8-3666-4d0d-b7cd-0016b7f60c6d",
			"name": "teste",
			"itemValue": 10.00,
			"itemType": "SERVICE",
			"activated": true
		},
		{
			"id": "2e7057e6-e600-43a7-acbc-d3a0d656e999",
			"name": "teste",
			"itemValue": 10.00,
			"itemType": "PRODUCT",
			"activated": true
		}
	],
	"discountPercentage": 0.5,
	"totalValue": 20.000,
	"situation": true
}
```

### Ferramentas Recomendadas Para Testes da API
**Postman**

**Insomnia**

## Execução dos Testes
Para Executar os testes utilize o seguinte comando
````bash
mvn test
````

### Testes Disponiveis
**OrderServiceTest:** Teste da camada de serviço do pedido

**ItemServiceTest:** Teste da camada de serviço dos items
