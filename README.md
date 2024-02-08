# Any Piece Marktplace

<div align="center">
<img class="logo" src="https://github.com/EnzowMb/Any-Piece-Store/assets/89809584/ac264629-2385-4cae-be5a-79186c5090f8" width="1000px">
</div>

<div align="center">
   <h2>Back-End Project</h1>
</div>

This project is an API built using **Java, Java Spring, AWS Simple Queue Service, Mongo DB and AWS Simple Storage Service.**

  It consists of a RESTful API application in Java to enhance my skills in Spring Boot, MongoDB, and various Amazon Web Services (AWS) services:
  <br>
  - AWS Lambda;<br>
  - Simple Queue Service (SQS);<br>
  - Simple Notification Service (SNS);<br>
  - S3.

  The objective is to enable the creation of a catalog of stores that sell products inspired by the anime One Piece.
  For instance, Store A offers a variety of products with categories related to the series universe, while Store B follows the same procedure, providing a wide selection of thematic items.
  <br>
  The API listens to requests made by the client and, through CRUD operations, persists the data in the MongoDB database. Additionally, it publishes a message to the SNS topic 'catalog-emit', which, in turn, forwards the message to an SQS queue named 'catalog-update'. 
  This way, messages are processed asynchronously, and if the consumer is unavailable, it can process all pending messages upon resuming operation. 
  The SQS queue is integrated with a Lambda function (AWS Lambda), which processes the message and updates the catalog in JSON format on the S3 service. 
  Thus, the Lambda is solely responsible for updating the existing JSON on S3; if it is necessary to create the JSON for the first time, it generates it with empty arrays of products and categories.

## 🔨 Technologies used

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

Database:

```bash
MongoDB
```

[![My Skills](https://skillicons.dev/icons?i=mongo)](https://skillicons.dev)

Dependencies:

```bash
spring-boot-starter-data-mongodb
lombok
aws-java-sdk-sns
json
```

### 🤖 Prerequisites

Requirements for the software and other tools for development and testing.

- AWS
- Spring Boot
- Insomnia or Postman
- MongoDB

[![My Skills](https://skillicons.dev/icons?i=postman,mongodb,spring,aws)](https://skillicons.dev)

## 🎲 Installing and running

1. Clone the repository:

```bash
git clone https://github.com/EnzowMb/Any-Piece-Store.git
```

2. Install dependencies with Maven

3. Create a configuration with your runtime environment variables with your AWS Credentials that are used in `application.properties`

```yaml
aws.region=sa-east-1
aws.accessKeyId=${AWS_KEY_ID}
aws.secretKey=${AWS_SECRET}
```

**Config Values**

```yaml
AWS_KEY_ID=VALUE;AWS_SECRET=VALUE2
```

### Running

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

## API Endpoints
The API provides the following endpoints:

**API PRODUCT**
```markdown
POST /api/product - Create a new product
GET /api/product - Retrieve all products
PUT /api/product/{id} - Updates a product
DELETE /api/product/{id} - Delete a product
```

**BODY**
```json
{
  "title": "One Piece Vol. 1",
  "description": "A tripulação pirata mais famosa dos quadrinhos finalmente joga sua âncora de novo no Brasil!",
  "ownerId": "123456",
  "categoryId": "65c3db78cbfa873eede8496f",
  "price": 4000
}
```

**API CATEGORY**
```markdown
POST /api/category - Create a new category
GET /api/category - Retrieve all categories
PUT /api/category/{id} - Updates a category
DELETE /api/category/{id} - Delete a category
```

**BODY**
```json
{
  "title": "Manga",
  "description": "Mangas de One piece",
  "ownerId": "123456"
}
```

## 👥 Autor

- **Enzo Martinelli**
