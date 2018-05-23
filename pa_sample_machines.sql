DROP TABLE IF EXISTS groups_customers;
DROP TABLE IF EXISTS sold_machines;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS machines ;
DROP TABLE IF EXISTS groups;


/* ***** CUSTOMERS ***** */

CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    balance INTEGER NOT NULL,
	    CONSTRAINT email_not_empty CHECK (email <> ''),
	    CONSTRAINT positive_balance CHECK (balance > 0)
);


/* ***** MACHINES ***** */

CREATE TABLE machines (
    id SERIAL PRIMARY KEY,
    make TEXT NOT NULL,
    price INTEGER NOT NULL,
    	CONSTRAINT positive_price CHECK (price > 0)
);


/* ***** GROUPS ***** */

CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    group_name TEXT NOT NULL,
    minimum_limit INTEGER,
    group_balance INTEGER DEFAULT 0,
        CONSTRAINT positive_balance CHECK (group_balance > 0),
        CONSTRAINT positive_limit CHECK (minimum_limit > 0)
);


/* ***** GROUPS-COSTUMERS CONNECTION ***** */

CREATE TABLE groups_customers (
    group_id INTEGER NOT NULL,
    customer_id INTEGER NOT NULL,
    UNIQUE (group_id, customer_id),
    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE

);


/* ***** SOLD-MACHINES CONNECTION ***** */

CREATE TABLE sold_machines (
    machine_id INTEGER UNIQUE NOT NULL,
    customer_id INTEGER,
    group_id INTEGER,
    FOREIGN KEY (machine_id) REFERENCES machines(id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE,
    UNIQUE (machine_id, customer_id, group_id),
        CONSTRAINT one_of_owners_is_null CHECK ((customer_id IS NOT NULL AND group_id IS NULL) OR (group_id IS NOT NULL AND customer_id IS NULL))
);



/* ***** USER_CASES ***** */


INSERT INTO customers (email, balance) VALUES
    ('reach_r_dgere@farmer.com', 10000), --1
    ('joy_ntogroup@lovemytractor.com', 500), --2
    ('carlito_may_b_alone@italianfarmers.it', 1000), --3
    ('john.dear@bondmen.com', 400) --4
;


INSERT INTO machines (make, price) VALUES
	('John Deere', 3000), --1
	('Zetor', 450), --2
	('Zetor God', 900), --3
	('Lamborghini', 4500), --4
	('MTZ-80', 1200), --5
	('Krieger', 1200) --6
;


INSERT INTO groups (group_name, minimum_limit, group_balance) VALUES
	('Losers', 500, 1000), --1
	('Groundlover Group', 300, 200) --2
;

INSERT INTO groups_customers (group_id, customer_id) VALUES
	(1, 4)
;

INSERT INTO sold_machines (machine_id, customer_id, group_id) VALUES
	(1, 1, NULL),
	(2, 2, NULL),
	(3, NULL, 1),
	(5, NULL, 1)
;


/* ***** list_costumers ***** */

SELECT email FROM customers;


/* ***** list_costumer_with_no_remaining_balance ***** */

SELECT email FROM customers
WHERE balance == 0;


/* ***** list_machines ***** */

SELECT make, price FROM machines;


/* ***** list_machines_between_price_range ***** */

SELECT make, price FROM machines
WHERE price between 500 and 4400;


/* ***** list_groups ***** */

SELECT group_name, minimum_limit FROM groups;


/* ***** costumer_details ***** */

SELECT email, balance FROM customers
WHERE id = 2;


/* ***** group_details ***** */

SELECT group_name, minimum_limit, group_balance FROM groups
WHERE group_id = 1;


/* ***** delete_existing_costumer ***** */

BEGIN;
DELETE FROM machines WHERE id IN (SELECT m.id FROM machines AS m
                                JOIN sold_machines AS sm
                                ON m.id = sm.machine_id
                                WHERE customer_id = 1);
DELETE FROM customers CASCADE WHERE id = 1;
COMMIT;


/* ***** delete_existing_machine ***** */

DELETE FROM machines WHERE id = 4 AND id NOT IN (SELECT m.id FROM machines AS m
                                 inner JOIN sold_machines AS sm
                                 ON m.id = sm.machine_id);


/* ***** delete_existing_group ***** */

BEGIN;
DELETE FROM machines WHERE id IN (SELECT m.id FROM machines AS m
                                inner JOIN sold_machines AS sm
                                ON m.id = sm.machine_id
                                WHERE group_id = 1);
DELETE FROM customers CASCADE WHERE id = 1;
COMMIT;

/* ***** group_join ***** */

BEGIN;
INSERT INTO groups_customers (group_id, customer_id) VALUES (1, 3);
UPDATE customers SET balance = balance - (SELECT minimum_limit FROM groups)
    WHERE id = 3;
UPDATE groups SET group_balance = group_balance + minimum_limit
    WHERE id = 1;
COMMIT;


/* ***** display_membership_data ***** */

SELECT email, balance FROM customers AS c
JOIN groups_customers AS gc on gc.customer_id = c.id
WHERE gc.group_id = 1;


/* ***** purchase_machine ***** */

BEGIN;
INSERT INTO sold_machines (machine_id, customer_id, group_id) VALUES (6, 1, NULL)
UPDATE customers SET balance = balance - (SELECT price FROM machines WHERE id = 6)
    WHERE id = 1;
COMMIT;


/* ***** purchase_history_by_given_customer ***** */

SELECT make FROM machines AS m
JOIN sold_machines AS sm
ON m.id = sm.machine_id
WHERE customer_id = 1;


/* ***** purchase_history_by_given_group ***** */

SELECT make FROM machines AS m
JOIN sold_machines AS sm
ON m.id = sm.machine_id
WHERE group_id = 1;


/* ***** machine_availability ***** */

SELECT m.make, sm.customer_id, sm.group_id FROM machines AS m
LEFT JOIN sold_machines as sm
ON m.id = sm.machine_id;