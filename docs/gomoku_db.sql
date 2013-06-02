-- Select the appropriate database --
USE gomoku;

-- Cleanup the database: Drop all the tables --
DROP TABLE IF EXISTS `game_moves`;
DROP TABLE IF EXISTS `human_users`;
DROP TABLE IF EXISTS `ai_machines`;
DROP TABLE IF EXISTS `games`;
DROP TABLE IF EXISTS `users` CASCADE;

-- Create all the relevant tables --
CREATE TABLE users (
	user_id		BIGINT AUTO_INCREMENT,
	username	VARCHAR(30) NOT NULL,
	password	TEXT,
	UNIQUE(username),
	PRIMARY KEY(user_id)
) ENGINE INNODB;

CREATE TABLE human_users (
	username	VARCHAR(30) NOT NULL,
	firstname	VARCHAR(30) NOT NULL,
	lastname	VARCHAR(30) NOT NULL,
	email		VARCHAR(50) NOT NULL,
	phone		VARCHAR(30) NOT NULL,
	FOREIGN KEY(username) REFERENCES users(username),
	PRIMARY KEY(username)
) ENGINE INNODB;

CREATE TABLE ai_machines (
	username	VARCHAR(30) NOT NULL,
	owner		VARCHAR(30) NOT NULL,
	-- This `owner` field should eventually point to the owner of the ai_machine whether 
	-- the owner be an individual, institution, company, etc. --
	
	FOREIGN KEY(username) REFERENCES users(username),
	PRIMARY KEY(username)
);

CREATE TABLE games (
	game_id		BIGINT AUTO_INCREMENT,
	created_on	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	playerO		BIGINT NOT NULL,
	playerX		BIGINT NOT NULL,
	status		ENUM('ONGOING', 'PAUSED', 'FORFEIT', 'COMPLETE') NOT NULL DEFAULT 'ONGOING',
	FOREIGN KEY(playerO) REFERENCES users(user_id),
	FOREIGN KEY(playerX) REFERENCES users(user_id),
	PRIMARY KEY(game_id)
)ENGINE INNODB;

CREATE TABLE game_moves(
	move_id		BIGINT NOT NULL AUTO_INCREMENT,
	game_id		BIGINT NOT NULL,
	player_id	BIGINT NOT NULL,
	played_on	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	row			INT NOT NULL,
	col			INT NOT NULL,
	FOREIGN KEY(game_id) REFERENCES games(game_id),
	FOREIGN KEY(player_id) REFERENCES users(user_id),
	PRIMARY KEY(move_id)
);

-- Grant the relevant permissions to database user --
GRANT CREATE,INSERT,SELECT,UPDATE,DELETE ON gomoku.* TO 'gomoku'@'localhost' IDENTIFIED BY 'gomoku';