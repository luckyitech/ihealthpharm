# IHealthPharm Backend

Java backend application for IHealthPharm - A comprehensive pharmaceutical management system.

## Project Structure

```
backend/
├── database/          # MySQL Database schemas
│   ├── schema.sql
│   └── ...
└── [Java source code will be added]
```

## Database

The database folder contains all MySQL schema files for the IHealthPharm backend.

### Schema Files

- `schema.sql` - Main database schema and tables

## Prerequisites

- Java JDK (v8 or higher)
- Maven
- MySQL 5.7 or higher

## Setup

1. Create MySQL database:
```sql
CREATE DATABASE ihealthpharm;
```

2. Import schema:
```bash
mysql -u root -p ihealthpharm < backend/database/schema.sql
```

## License

All rights reserved.
