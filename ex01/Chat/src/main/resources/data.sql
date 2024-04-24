INSERT INTO chat.users (login, password) VALUES ('Danil', '123');
INSERT INTO chat.users (login, password) VALUES ('Ira', 'qwerty');
INSERT INTO chat.users (login, password) VALUES ('Rasim', 'qazwsx');
INSERT INTO chat.users (login, password) VALUES ('Shihali', 'baguvix');
INSERT INTO chat.users (login, password) VALUES ('Anvar', 'hesoyam');

INSERT INTO chat.rooms (name, owner) VALUES ('school21', 1);
INSERT INTO chat.rooms (name, owner) VALUES ('adm_kazan', 2);
INSERT INTO chat.rooms (name, owner) VALUES ('Intership', 3);
INSERT INTO chat.rooms (name, owner) VALUES ('product_info', 4);
INSERT INTO chat.rooms (name, owner) VALUES ('general', 5);

INSERT INTO chat.message (author, chatroom, text, time) VALUES (1, 1, 'give me penalty', current_timestamp);
INSERT INTO chat.message (author, chatroom, text, time) VALUES (2, 2, 'prodlite dedline pls', current_timestamp);
INSERT INTO chat.message (author, chatroom, text, time) VALUES (3, 3, 'gde rabota', current_timestamp);
INSERT INTO chat.message (author, chatroom, text, time) VALUES (4, 4, 'BASE NUJNA', current_timestamp);
INSERT INTO chat.message (author, chatroom, text, time) VALUES (5, 5, 'give me 10 penalty', current_timestamp);