## Workspace setup
- Clone/Download this repo
- Ensure that JAVA_HOME is set pointing to jdk11
- Run command mvnw spring-boot:run

# todoapi
Rest API for todo application



### API End points


- ToDo
    - List todos (GET /api/v1/todo)
    ```json
    [
    {
        "Id": 1,
        "title": "test1",
        "completed": false
    },
    {
        "Id": 2,
        "title": "test2",
        "completed": true
    }
    ]
    ```
    
  - Create todo (POST /api/v1/todo) 
    #### Request body
    ```json
    {
        "title": "title name",
        "completed": true
            
    }
    ```
     
    #### Response body
    ```json
    {
        "Id": 3,
        "title": "title name",
        "completed": true
    }

    ```
   -  update todo (PUT /api/v1/todo/{id})
      #### request body
        ```json
             {
                "title": "title name update",
		        "completed": true
		      }
        ```
        #### Resonse body
        ```json
        {
        "Id": 3,
        "title": "title name update",
        "completed": true
        }
        ```
       
     - Delete todo (DELETE /api/v1/todo/{id})
     
		response code - 202     
           
## Run API operations using(testing)
http://localhost:8080/swagger-ui.html
        
        
