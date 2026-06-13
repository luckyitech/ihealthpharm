# Database Files Index

Complete MySQL database schema and objects for IHealthPharm application.

## Quick Start

### Import Everything
```bash
cd backend/database

# 1. Create and import schema
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS ihealthpharm;"
mysql -u root -p ihealthpharm < schema.sql

# 2. Import all objects (procedures, events, triggers)
mysql -u root -p ihealthpharm < stored_procedures.sql
mysql -u root -p ihealthpharm < triggers.sql
mysql -u root -p ihealthpharm < events.sql

# 3. Enable event scheduler
mysql -u root -p -e "SET GLOBAL event_scheduler = ON;"
```

---

## Database Files

### 1. **schema.sql** (1.1 MB)
Complete database schema with 150+ tables covering:
- Finance & Accounting
- Sales & Invoicing
- Stock Management
- Employee Management
- Customer Management
- Configuration

**Import:**
```bash
mysql -u root -p ihealthpharm < schema.sql
```

**Documentation:** See `DATABASE_SCHEMA.md`

---

### 2. **stored_procedures.sql** (2.1 MB)
Stored procedures and functions for:
- Complex business logic
- Report generation
- Financial calculations
- Data processing

**Import:**
```bash
mysql -u root -p ihealthpharm < stored_procedures.sql
```

**View Procedures:**
```sql
SHOW PROCEDURE STATUS WHERE db='ihealthpharm';
```

---

### 3. **events.sql** (27.7 KB)
Scheduled events that run automatically:
- Stock closing events
- Reorder level calculations
- Account updates
- Quote generation
- Till balance reconciliation

**Import:**
```bash
mysql -u root -p ihealthpharm < events.sql
```

**Enable Scheduler:**
```sql
SET GLOBAL event_scheduler = ON;
```

---

### 4. **triggers.sql** (2.5 KB)
Database triggers for maintaining data integrity:
- Automatic calculations
- Data validation
- Referential integrity
- Audit trails

**Import:**
```bash
mysql -u root -p ihealthpharm < triggers.sql
```

---

## Documentation

- **DATABASE_SCHEMA.md** - Detailed schema documentation with module breakdown
- **DATABASE_OBJECTS.md** - Documentation for procedures, events, and triggers
- **INDEX.md** - This file

---

## Total Database Size
Approximately 3.2 MB with all objects

## Requirements
- MySQL 5.7 or higher (developed with 8.0.37)
- Sufficient disk space for database
- CREATE DATABASE, CREATE TABLE, CREATE ROUTINE, CREATE TRIGGER privileges

## Notes
- Always backup before importing
- Test in development first
- Ensure event scheduler is enabled after import
- Some procedures may require specific data setup

---

For more details on specific components, see the corresponding documentation files in this directory.
