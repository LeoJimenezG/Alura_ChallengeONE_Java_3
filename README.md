# Alura_ChallengeONE_Java_3
Alura Challenge: Forum-Hub. Java BackEnd API REST

This API REST system simulates the functionality of a Forum using four main endpoints. It supports the GET, POST, PUT and DELETE http methods.

---

## 📘 How does it work

- Every endpoint capable of returning an object response, it will return the status code and a json.
- The system uses PostgresSQL for the database.
- The url before the endpoints can be adjusted according to the needs. (e.g., `http:/anyserver/ENDPOINT`).
- The system has four main endpoints: **/login**, **/topic**, **/author** and **/answer**. Each one of them correspond to different aspects and will vary on the http methods allowed.

---

## 💻 Main Endpoints

### Endpoint("/login"):
- The only http method it accepts is POST.
- This endpoint does not require any kind of authorization, so anyone can access to it.
- You need to send a Json with valid credentials in order to recive a JsonWebToken that will allow you to access all the other endpoints.
- Example of the Json with credentials:

  ![image](https://github.com/user-attachments/assets/a4c2b858-756e-4c88-b442-319e71305803)
- If your credentials are valid you will recive your JWT and it will valid for two hours according to the UTC(-06:00).
   - Example of successful response:

     ![image](https://github.com/user-attachments/assets/da7064a0-b052-485b-8c56-1b8db29d403e)
   - If your credentials are not valid you will recive a 403 status code.
- Once you have recived your JWT, you will be able to access all the other endpoints.

### Endpoint("/topic"):
- This endpoint will accept GET, POST, PUT and DELETE http methods.
- Only the active topics will be considered for this functions (`status = true`).  
- #### Method POST:
   - To register a new topic, you need to send a Json which can't have empty information.
   - Example of a valid Json:
   
      ![image](https://github.com/user-attachments/assets/faf32e28-b286-4d36-903b-644d434bac32)
   - If the request is successful you will get a 201 status code, the created object and its location `http:/server/topic/TOPIC_ID`):

     ![image](https://github.com/user-attachments/assets/49fd8029-eb95-44f2-ba72-e8e3613be7b5)
   - If you try to register a topic with the same `title` and `message` that another topic, you will get a 400 status code.
    
   
- #### Method GET:
   - **Endpoint("/all")**:
      - This endpoint will return a list of all the topics in a Json format.
      - Example of sucessful response:

        ![image](https://github.com/user-attachments/assets/174d3653-be7d-4baf-85cf-4b38ba95b940)

  - **Endpoint("/firstTen")**:
      - This endpoint will return a list of ten topics in ascending order according to their `creation_date`.

  - **Endpoint("/title/{title}")**:
      - This endpoint allowes you to search a topic by its exact title.
      - An example of a request is `http:/server/topic/title/example-of-title`.
      - If the topic is found, you will recive a Json object:

        ![image](https://github.com/user-attachments/assets/d9062c2b-360c-4590-bb39-6d510afc25ba)
      - If the topic is not found, you will get a 404 status code.

  - **Endpoint("/author/{id}")**:
      - This endpoint allowes you to search a topic by an author's id.
      - An example of a request is `http:/server/topic/author/2`.
      - If the author's id and topic is found, you will recive a list of topics in a Json format:

        ![image](https://github.com/user-attachments/assets/de89c582-b9b5-4933-a008-b6caf9435da5)
      - If the author's id or topic is not found, you will get a 404 status code.
    
   - **Endpoint("/course/{id}")**:
      - This endpoint allowes you to search a topic by a course's id.
      - An example of a request is `http:/server/topic/course/4`.
      - If the course's id and topic is found, you will recive a list of topics in a Json format:
        
        ![image](https://github.com/user-attachments/assets/243992a1-133a-47b9-828e-180193c54135)
       - If the course's id or topic is not found, you will get a 404 status code.

  - **Endpoint("/{id}")**:
      - This endpoint allowes you to search a topic by its id.
      - An example of a request is `http:/server/topic/1`.
      - If the topic is found, you will recive a Json object:
        
        ![image](https://github.com/user-attachments/assets/d9e0d443-aeb8-4455-94eb-54dab6e233ad)
      - If the topic is not found, you will get a 404 status code.

        
- #### Method PUT: 
   - **Endpoint("/{id}")**:
      - This endpoint allowes you to update a topic by its id.
      - Example of a valid Json to update a topic:
        
        ![image](https://github.com/user-attachments/assets/5b798bb5-a1e2-497f-a05b-7e231f6126b8)
      - You can omit any of the Json properties as this is only an update. 
      - If the topic is found and the Json is valid, you will recive a Json of the updated object:
      
        ![image](https://github.com/user-attachments/assets/44764e6e-5b30-4559-baa0-7784b49351fb)
      - If the topic is not found, you will get a 404 status code.


- #### Method DELETE:
   - **Endpoint("/{id}")**:
      - This endpoint allowes you to delete a topic by its id.
      - If the topic is found, you will only get a 200 status code.
      - If the topic is not found, you will get a 404 status code.



### Endpoint("/author"):
- This endpoint will accept GET and PUT http methods.
- #### Method GET:
   - **Endpoint("/{id}")**:
      - This endpoint allowes you to search an author by its id.
      - An example of a request is `http:/server/author/1`.
      - If the author is found, you will recive a Json object:
   
        ![image](https://github.com/user-attachments/assets/5a39801e-9b4b-4bc5-bffd-4cc2c03757d4)
      - If the author is not found, you will get a 404 status code.
        
   - **Endpoint("/name/{name}")**:
      - This endpoint allowes you to search an author by its exact name.
      - An example of a request is `http:/server/author/name/Pedro-Hernandez`.
      - If the author is found, you will recive a Json object:

        ![image](https://github.com/user-attachments/assets/5a39801e-9b4b-4bc5-bffd-4cc2c03757d4)
      - If the author is not found, you will get a 404 status code.
    
   - **Endpoint("/email/{email}")**:
      - This endpoint allowes you to search an author by its exact email.
      - An example of a request is `http:/server/author/email/pedro123@gmail.com`.
      - If the author is found, you will recive a Json object:

        ![image](https://github.com/user-attachments/assets/5a39801e-9b4b-4bc5-bffd-4cc2c03757d4)
      - If the author is not found, you will get a 404 status code.


- #### Method PUT:
   - **Endpoint("/{id}")**:
      - This endpoint allowes you to update an author by its id.
      - Example of a valid Json to update an author:

        ![image](https://github.com/user-attachments/assets/0e608cd3-07a7-4102-ac0c-f369ad6f2b66)
      - You can omit any of the Json properties as this is only an update. 
      - If the author is found and the Json is valid, you will revice a Json of the updated object:

        ![image](https://github.com/user-attachments/assets/d9314ccc-a294-445d-9009-9108eb6b4af0)
      - If the author is not found, you will get a 404 status code.

### Endpoint("/answer"):
- This endpoint will accept GET, POST, PUT and DELETE http methods.
- #### Method GET:
   - **Endpoint("/{id}")**:
      - This endpoint allowes you to search an answer by its id.
      - An example of a request is `http:/server/answer/1`.
      - If the answer is found, you will recive a Json object:

        ![image](https://github.com/user-attachments/assets/1ba26c27-48cd-41fe-b19e-838693428b5b)
      - If the answer is not found, you will get a 404 status code.  
- #### Method POST:
   - **Endpoint("/add")**: 
      - This endpoint allowes you to add a new answer.
      - You need to send a Json which can't have empty information.
      - Example of a valid Json:

        ![image](https://github.com/user-attachments/assets/8efc9e1e-a978-48fc-a5d1-baf963287c6c)
      - If the request is successful you will get a 201 status code, the created object and its location `http:/server/answer/ANSWER_ID`:

        ![image](https://github.com/user-attachments/assets/2f858d01-da97-415c-bd35-6d202e936cad)
      - If your request is missing something or it is not correct, you will get a 400 status code.
- #### Method PUT:
   - **Endpoint("/{id}")**:
      - This endpoint allowes you to update an answer by its id.
      - Example of a valid Json to update an answer:

        ![image](https://github.com/user-attachments/assets/963a3611-77e2-497f-ba54-9ce51554445f)
      - You can omit any of the Json properties as this is only an update. 
      - If the answer is found and the Json is valid, you will revice a Json of the updated object:

        ![image](https://github.com/user-attachments/assets/219a3313-ed21-4cfd-98aa-10b608d8056d)
      - If the answer is not found, you will get a 404 status code.
- #### Method DELETE:
   - **Endpoint("/{id}")**:
      - This endpoint allowes you to delete an answer by its id.
      - If the answer is found, you will only get a 200 status code.
      - If the answer is not found, you will get a 404 status code.

---

 ## 🔐 Security Considerations

- The API uses JWT (JSON Web Token) for authentication and authorization.
- The token must be included in the `Authorization` header for all requests except the `/login` endpoint.
- Ensure secure storage of tokens on the client side.

---

## 📚 Useful Resources

- [Spring Boot docs](https://spring.io/projects/spring-boot)
- [JWT introduction](https://jwt.io/introduction)
- [PostgresSQL docs](https://www.postgresql.org/docs/)

