# OompaLoompas Management API

###Requeriments
- Docker
- Docker compose
###Usage
To run the API execute this commands:

1. gradle build
2. docker-compose up --build

- To add a new OompaLoompa
    1. POST http://localhost:8080/api/add with body:
    ```
        {
        	"name" : "String",
        	"age" : Integer,
        	"job" : "String",
        	"height": Float,
        	"weight": Float,
        	"description": "String"
        }
    ```
- To edit an OompaLoompa
    1. Put http://localhost:8080/api/edit with body:
    ```
        {
            "id" : "String",
            "name" : "String",
            "age" : Integer,
            "job" : "String",
            "height": Float,
            "weight": Float,
            "description": "String"
        }
    ```
- To get List of OompaLoompas
    1. Get http://localhost:8080/api/list?page=0&size=10
    Parameters:
        1. Page: which page number to retrieve
        2. Size: the amount of OompaLoompas to show per page
- To get details of OompaLoompa
    1. Get http://localhost:8080/api/{id}
    Parameters:
        1. Id: The id of the OompaLoompa
        

    