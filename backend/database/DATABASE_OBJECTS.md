# IHealthPharm Database Objects

## Stored Procedures & Functions

### File: `stored_procedures.sql` (2.1 MB)

Contains all stored procedures and functions used by the IHealthPharm system for complex business logic operations.

### Purpose

Stored procedures encapsulate complex SQL operations including:
- Data manipulation and calculations
- Report generation
- Financial calculations
- Stock movement tracking
- Transaction processing

### How to Import

```bash
mysql -u root -p ihealthpharm < backend/database/stored_procedures.sql
```

### View Stored Procedures

```sql
USE ihealthpharm;
SHOW PROCEDURE STATUS WHERE db='ihealthpharm';
SHOW FUNCTION STATUS WHERE db='ihealthpharm';
```

---

## Database Events (Scheduled Tasks)

### File: `events.sql` (27.7 KB)

Contains all scheduled events that automatically run at specified intervals.

### Configured Events

1. **closing_stock_event**
   - Purpose: Updates closing stock quantities daily
   - Frequency: Scheduled based on system configuration

2. **create_item_reorder_level_event**
   - Purpose: Calculates item reorder levels based on sales trends
   - Logic: Computes reorder level and quantity for inventory management

3. **credit_account_credit_days_left**
   - Purpose: Decrements credit days remaining for customer accounts
   - Frequency: Daily

4. **generateuniquecodereset**
   - Purpose: Resets unique code generator for daily operations
   - Frequency: Daily

5. **insert_auto_quotation_items**
   - Purpose: Automatically generates quotations for items below reorder level
   - Trigger: When sales occur below reorder quantity

6. **opening_stock_event**
   - Purpose: Records opening stock movements
   - Frequency: Daily

7. **sales_till_account_update**
   - Purpose: Updates accounting records based on sales till balance
   - Frequency: As needed

### How to Import

```bash
mysql -u root -p ihealthpharm < backend/database/events.sql
```

### View Events

```sql
USE ihealthpharm;
SELECT EVENT_NAME, EVENT_TYPE, EXECUTE_AT, STATUS 
FROM information_schema.EVENTS 
WHERE EVENT_SCHEMA='ihealthpharm';
```

### Enable/Disable Events

```sql
-- Enable all events for the database
SET GLOBAL event_scheduler = ON;

-- Disable event
ALTER EVENT event_name DISABLE;

-- Enable event
ALTER EVENT event_name ENABLE;
```

---

## Triggers

### File: `triggers.sql` (2.5 KB)

Contains database triggers that automatically execute in response to INSERT, UPDATE, or DELETE operations.

### Triggers Overview

Triggers maintain data integrity by:
- Enforcing business rules automatically
- Updating related tables on data changes
- Auditing data modifications
- Preventing invalid state transitions

### How to Import

```bash
mysql -u root -p ihealthpharm < backend/database/triggers.sql
```

### View Triggers

```sql
USE ihealthpharm;
SELECT TRIGGER_NAME, TRIGGER_TIME, TRIGGER_EVENT, EVENT_OBJECT_TABLE
FROM information_schema.TRIGGERS
WHERE TRIGGER_SCHEMA='ihealthpharm';
```

---

## Import Order

When setting up a new database, import in this order:

```bash
# 1. Create database
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS ihealthpharm;"

# 2. Import schema and tables
mysql -u root -p ihealthpharm < backend/database/schema.sql

# 3. Import stored procedures and functions
mysql -u root -p ihealthpharm < backend/database/stored_procedures.sql

# 4. Import triggers
mysql -u root -p ihealthpharm < backend/database/triggers.sql

# 5. Import events
mysql -u root -p ihealthpharm < backend/database/events.sql

# 6. Enable event scheduler
mysql -u root -p -e "SET GLOBAL event_scheduler = ON;"
```

---

## File Summary

| File | Size | Content |
|------|------|---------|
| schema.sql | 1.1 MB | Tables and database structure |
| stored_procedures.sql | 2.1 MB | Stored procedures and functions |
| events.sql | 27.7 KB | Scheduled events |
| triggers.sql | 2.5 KB | Database triggers |
| **Total** | **~3.2 MB** | **Complete database schema** |

---

## Important Notes

1. **Event Scheduler**: Ensure the event scheduler is enabled:
   ```sql
   SHOW VARIABLES LIKE 'event_scheduler';
   SET GLOBAL event_scheduler = ON;
   ```

2. **Permissions**: User importing these objects needs CREATE ROUTINE and CREATE TRIGGER privileges

3. **Backups**: Always backup before importing these scripts

4. **Testing**: Test in development environment first

5. **Dependencies**: Some procedures may depend on specific tables and data structures

---

## Maintenance

### Monitoring Events
```sql
SELECT * FROM information_schema.EVENTS WHERE EVENT_SCHEMA='ihealthpharm';
```

### View Trigger Definitions
```sql
SHOW CREATE TRIGGER trigger_name;
```

### View Procedure Definitions
```sql
SHOW CREATE PROCEDURE procedure_name;
```

---

For complete database documentation, refer to `DATABASE_SCHEMA.md`.
