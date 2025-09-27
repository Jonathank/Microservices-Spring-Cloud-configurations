# Microservices-Docker-images-Containers

This repository contains three main microservices, each with its own detailed README file. Here are the details for each:

---

### Accounts Microservice

**Overview:**  
A Java-based microservice for managing account-related operations, such as user registration and account management.

**Features:**  
- User registration  
- RESTful APIs for account operations  
- Modular microservices design  

**Technologies Used:**  
- Java (Java 17)  
- Spring Boot  
- OpenAPI  
- MySQL  
- Docker  

**Getting Started:**  
**Prerequisites:**  
- Java 8+ (uses Java 17)  
- Maven (used for builds)  
- Database or other services as needed  

**Setup:**  
1. Clone the repository:
   ```
   git clone https://github.com/Jonathank/Accounts-Microservices.git
   ```
2. Navigate to the project directory:
   ```
   cd Accounts-Microservices
   ```
3. Build the project:
   ```
   mvn clean install
   ```
4. Run the service:
   ```
   mvn spring-boot:run
   ```

**Configuration:**  
- Update `src/main/resources/application.properties` as needed  
- Set up environment variables for secrets or database credentials  

---

### Cards Microservice

**Overview:**  
A Java-based microservice for managing card-related operations: creating, updating, deleting, and fetching customer card info.

**Features:**  
- RESTful APIs for card operations  
- Modular microservices design  

**Technologies Used:**  
- Java (Java 17)  
- Spring Boot  
- OpenAPI  
- MySQL  
- Docker  

**Getting Started:**  
**Prerequisites:**  
- Java 8+ (uses Java 17)  
- Maven (used for builds)  
- Database or other services as needed  

**Setup:**  
1. Clone the repository:
   ```
   git clone https://github.com/Jonathank/Card-Microservices.git
   ```
2. Navigate to the project directory:
   ```
   cd card-Microservices
   ```
3. Build the project:
   ```
   mvn clean install
   ```
4. Run the service:
   ```
   mvn spring-boot:run
   ```

**Configuration:**  
- Update `src/main/resources/application.yml` as needed  
- Set up environment variables for secrets or database credentials  

---

### Loans Microservice

**Overview:**  
A Java-based microservice for managing loans: creating, updating, deleting, and fetching customer loans.

**Features:**  
- RESTful APIs for loans operations  
- Modular microservices design  

**Technologies Used:**  
- Java (Java 17)  
- Spring Boot  
- OpenAPI  
- MySQL  
- Docker  

**Getting Started:**  
**Prerequisites:**  
- Java 8+ (uses Java 17)  
- Maven (used for builds)  
- Database or other services as needed  

**Setup:**  
1. Clone the repository:
   ```
   git clone https://github.com/Jonathank/Loans-Microservices.git
   ```
2. Navigate to the project directory:
   ```
   cd Accounts-Microservices
   ```
3. Build the project:
   ```
   mvn clean install
   ```
4. Run the service:
   ```
   mvn spring-boot:run
   ```

**Configuration:**  
- Update `src/main/resources/application.yml` as needed  
- Set up environment variables for secrets or database credentials  

---
