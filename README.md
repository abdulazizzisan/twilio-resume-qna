# SMS Recruiter Support Agent

A Spring Boot application that provides AI-powered document processing and question-answering capabilities through vector embeddings and natural language processing.

## Features

This application provides the following capabilities:

1. **PDF Document Processing**: Store embeddings from PDF files to a vector database for semantic search and retrieval
2. **Document Management**: Delete embeddings based on file name for easy document lifecycle management
3. **AI-Powered Q&A**: Ask questions about PDF content and receive intelligent responses based on the document's information
4. **Twilio Integration**: Includes a webhook endpoint designed for Twilio webhook features, allowing any Twilio service to interact with the application

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL with pgvector extension (for vector database)
- OpenAI API key

## Configuration

⚠️ **Important**: You must create a `secrets.properties` file in the `src/main/resources/` directory for the project to work properly.

### Required Configuration File

Create `src/main/resources/secrets.properties` with the following content:

```properties
spring.ai.openai.api-key=your-openai-api-key-here
```

Replace `your-openai-api-key-here` with your actual OpenAI API key.

### Additional Configuration

Make sure to configure your database connection in `application.properties` for the vector database functionality.

## Getting Started

1. Clone the repository
2. Create the required `secrets.properties` file (see Configuration section above)
3. Configure your database settings in `application.properties`
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

- **POST /load** - Upload and process PDF documents
- **DELETE /delete** - Remove documents from vector database
- **POST /ask** - Ask questions about document content
- **POST /webhook** - Twilio webhook endpoint

## Technology Stack

- Spring Boot
- Spring AI
- OpenAI API
- PostgreSQL with pgvector
- Maven

## License

This project is licensed under the MIT License.
