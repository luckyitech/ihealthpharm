# IHealthPharm Database Schema Documentation

## Database Information
- **Database Name:** ihealthpharm
- **Database Server:** MySQL 8.0.37
- **Export Date:** 2026-06-13

## Schema Overview

This directory contains the complete MySQL database schema for the IHealthPharm system - a comprehensive pharmaceutical management application.

### Key Modules

The database is organized around the following business modules:

#### 1. **Finance Module**
- Chart of Accounts
- Account Payables
- Account Receivables
- General Ledger
- Bank Transactions
- Cheques

#### 2. **Sales Module**
- Customers
- Customer Insurance
- Customer Membership
- Invoices & Invoice Items
- Credit Notes
- Debit Notes
- Discounts

#### 3. **Stock Management**
- Product Stock
- Stock Adjustment
- Purchase Orders
- Quotations
- Delivery Notes
- Inventory Management

#### 4. **Employee Management**
- Employee Records
- Employee Credentials
- Employee Salary
- Employee Education & Publications
- Employee Pharmacy Roles
- Employee Access Control

#### 5. **Accounts**
- Family Accounts
- Customer Statements
- Account Types
- Account Status

#### 6. **Configuration**
- System Configuration
- Configuration Status
- Location Management (City, Country)
- Employee Types
- Delivery Types
- Bill Types

## Table Statistics

Total Tables: 150+

### Core Tables

The database includes tables for:
- Account management (receivables, payables)
- Customer and supplier management
- Invoice and billing management
- Stock and inventory control
- Employee and HR management
- Financial transactions and reporting
- Product and item management
- Insurance and membership management

## Files in this Directory

- `schema.sql` - Complete database schema and table definitions
- `DATABASE_SCHEMA.md` - This documentation file

## How to Use

### Create Database
```sql
CREATE DATABASE IF NOT EXISTS ihealthpharm;
```

### Import Schema
```bash
mysql -u root -p ihealthpharm < backend/database/schema.sql
```

### Connect to Database
```bash
mysql -u root -p ihealthpharm
```

## Important Notes

1. The schema includes system performance views (host_summary, io_global, etc.) which are part of MySQL's performance schema.
2. Some tables have prefixes like `#` which may indicate temporary or working tables.
3. Backup and version control of this schema is recommended.
4. Always test schema changes in a development environment first.

## Database Dependencies

- All tables use InnoDB storage engine for ACID compliance
- Primary keys are defined on all main tables
- Foreign key relationships are established where necessary
- Indexes are created for optimized query performance

## Maintenance

- Regular backups should be taken
- Schema migrations should be version controlled
- Test all changes in a development environment
- Document any schema modifications

---

For more information about the IHealthPharm system, refer to the main README.md file.
