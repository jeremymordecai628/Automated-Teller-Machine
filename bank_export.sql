PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE account (
    account_no TEXT PRIMARY KEY,         -- Unique account number
    national_id TEXT NOT NULL,           -- National ID of the account owner
    account_type TEXT NOT NULL,          -- e.g., 'Savings', 'Current'
    balance REAL DEFAULT 0.0,            -- Initial balance
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    account_no TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    FOREIGN KEY (account_no) REFERENCES account(account_no)
);
CREATE TABLE transactions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    account_no TEXT NOT NULL,
    type TEXT NOT NULL,           -- e.g., 'deposit', 'withdrawal'
    amount REAL NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_no) REFERENCES account(account_no)
);
COMMIT;
