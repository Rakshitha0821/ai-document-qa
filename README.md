# DocuAsk — AI Document Q&A

A Java 21 + Spring Boot app for adding plain-text documents and asking questions with source passages. It includes a sample employee-handbook document so you can see an answer immediately without creating an API key. When `OPENAI_API_KEY` is set, it uses the OpenAI API to generate a concise answer from the retrieved passages.

## Requirements

- JDK 21 or newer (your installed JDK 25 is suitable)
- Maven 3.6.3 or newer
- Optional for real AI answers: an OpenAI API key with API usage enabled

## Run in VS Code (PowerShell)

Open this project folder in VS Code, then choose **Terminal → New Terminal**. If Maven and Java are not on PATH in this terminal, set them for this session:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-25.0.3"
$env:Path += ";C:\Users\RAKSHITHA\Downloads\apache-maven-3.10.1-bin\apache-maven-3.10.1\bin;$env:JAVA_HOME\bin"
```

To start in demo mode, run:

```powershell
mvn spring-boot:run
```

Open `http://localhost:8081` in a browser. Ask: **How many days of annual leave do employees receive?** The sample document should yield an answer with its source excerpt. Demo mode uses local passage matching; it does not call an AI model.

Expected demo answer:

> Full-time employees receive 15 days of paid annual leave per calendar year. Leave requests should be submitted to a manager at least five working days in advance. Approval depends on team coverage. Unused annual leave does not roll over to the next year.

## Enable AI answers

Create an API key at [OpenAI API keys](https://platform.openai.com/api-keys). In the same PowerShell terminal, before running the app, set:

```powershell
$env:OPENAI_API_KEY = "paste-your-OpenAI-key-here"
mvn spring-boot:run
```

The page badge will show **OpenAI AI mode** when the key is set. API use is billed separately from a ChatGPT subscription. Never commit or share the key.

## Add a document

Paste plain text into **Your documents**, enter a name, and click **Add document**. Documents are stored in memory and reset when the app restarts. This starter version does not yet handle PDF or Word extraction, user accounts, persistent storage, or vector embeddings.

## API

- `GET /api/documents` — list loaded documents
- `POST /api/documents` — JSON body: `{"name":"Notes","content":"Document text..."}`
- `POST /api/ask` — JSON body: `{"question":"Your question"}`

The answer response includes `answer`, `sources`, and `mode` (`demo` or `openai`).
