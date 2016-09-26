CREATE TABLE users (
	user_id TEXT PRIMARY KEY,
	user_email TEXT
);
CREATE TABLE orders (
	order_id SERIAL PRIMARY KEY,
	user_id TEXT,
	order_json TEXT
);
INSERT INTO users (user_id, user_email) VALUES ('nenadm','nenadm@pivotal.io');
INSERT INTO users (user_id, user_email) VALUES ('shiehn','shiehn@pivotal.io');
INSERT INTO users (user_id, user_email) VALUES ('kabdul','kabdul@pivotal.io');


