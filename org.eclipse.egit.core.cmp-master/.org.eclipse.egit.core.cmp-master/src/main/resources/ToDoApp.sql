
CREATE DATABASE todo_database;
use todo_database;

CREATE TABLE users(id INT IDENTITY(1,1) PRIMARY KEY,user_name NVARCHAR(50) ,password NVARCHAR(100),created_time TIMESTAMP,
CREATE TABLE tasks(id INT IDENTITY(1,1) PRIMARY KEY,title NVARCHAR(100) NOT NULL,note TEXT,completed BIT,user_id INT NOT NULL FOREIGN KEY REFERENCES users(id) ON DELETE CASCADE,created_at TIMESTAMP,)

CREATE TABLE shared_tasks(
    id INT IDENTITY(1,1) PRIMARY KEY,
    task_id INT NOT NULL FOREIGN KEY REFERENCES tasks(id) ON DELETE CASCADE ,
    user_id INT NOT NULL FOREIGN KEY REFERENCES users(id) ON DELETE CASCADE ,
    shared_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

INSERT INTO users(user_name, password) VALUES ('sa', '123');
INSERT INTO users(user_name, password) VALUES ('admin','123');

INSERT INTO tasks (title, note, user_id) VALUES ('Learn english','learn english 3 hours from 8h to 12h','1');
INSERT INTO tasks (title, note, user_id) values ('Buy groceries','Total amound: 200$','2');

INSERT INTO shared_tasks (task_id, user_id) VALUES ('1','2');

















































































































