CREATE TABLE users (
	id VARCHAR(20) NOT NULL PRIMARY KEY,
	pw VARCHAR(20) NOT NULL,
	user_name VARCHAR(20),
	user_role VARCHAR(10)
);
INSERT INTO users VALUES("admin","adminpass","admin","admin"),("user1","user1pass","user1","user"),("user2","user2pass","user2","user");