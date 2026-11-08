# Finance Tracker (Java CLI)

A command-line personal finance tracker built with Java. The project practices object-oriented programming, collections, repository design, file handling, streams, and basic transaction analysis.

## Features

- Add income and expense transactions
- List saved transactions
- Search transactions by description
- Filter transactions by date range
- Remove a transaction by ID
- Calculate total income, expenses, and balance
- Sort transactions by amount
- Save and load transactions from a local text file

## Tech Stack

- Java 25+
- Java Collections Framework (`HashMap`, `List`, `Optional`)
- Java Streams and `Comparator`
- `LocalDate` for transaction dates
- Buffered file I/O for local persistence

## Project Structure

```text
AppTransactionProject/
├── src/
│   ├── FinanceTrackerApp.java
│   ├── Repository.java
│   ├── Transaction.java
│   ├── TransactionAlgo.java
│   ├── TransactionFileStore.java
│   ├── TransactionRepository.java
│   └── TransactionType.java
├── .gitignore
└── README.md
```

## Run the Application

Compile the source files:

```bash
javac -d out src/*.java
```

Run with a compatible Java runtime:

```bash
java -cp out FinanceTrackerApp
```

The application stores transaction records in `transaction_data.txt` in the working directory. This local data file is intentionally excluded from Git so personal financial records are not published.

## Learning Goals

This project is part of my Java backend development learning journey. It focuses on separating transaction data, repository operations, algorithms, and file persistence into small, understandable components.

## Future Improvements

- Add input validation for transaction amounts and descriptions
- Improve balance calculation and add tests
- Support more flexible description search
- Add unit tests and clearer error handling
