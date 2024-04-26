DROP SCHEMA IF EXISTS chat CASCADE;
CREATE SCHEMA IF NOT EXISTS chat;
CREATE TABLE IF NOT EXISTS chat.users (id SERIAL PRIMARY KEY, login varchar(15) UNIQUE NOT NULL, password varchar(15) NOT NULL);
CREATE TABLE IF NOT EXISTS chat.rooms (id SERIAL PRIMARY KEY, name TEXT UNIQUE NOT NULL, owner INTEGER REFERENCES chat.users(id) NOT NULL);
CREATE TABLE IF NOT EXISTS chat.message (id SERIAL PRIMARY KEY, author INTEGER REFERENCES chat.users(id) NOT NULL, chatroom INTEGER REFERENCES chat.rooms(id) NOT NULL, text TEXT NOT NULL, time TIMESTAMP NOT NULL);
CREATE TABLE IF NOT EXISTS chat.user_chatroom (user_id integer not null, chat_id integer not null,foreign key (user_id) references chat.users(id), foreign key (chat_id) references chat.rooms(id));