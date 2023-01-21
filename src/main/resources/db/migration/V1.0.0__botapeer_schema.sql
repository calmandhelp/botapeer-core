CREATE DATABASE IF NOT EXISTS botapeer_db;
USE botapeer_db;
CREATE TABLE IF NOT EXISTS users
(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR (255) UNIQUE,
   email VARCHAR (255) UNIQUE,
   password VARCHAR (255),
   description VARCHAR (255),
   profile_image VARCHAR(255),
   cover_image VARCHAR(255),
   status INT,
   created_at DATETIME,
   updated_at DATETIME,
   PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS records
(
   id INT NOT NULL AUTO_INCREMENT,
   user_id INT,
   title VARCHAR(255),
   alive boolean,
   status INT,
   end_date DATETIME,
   created_at DATETIME,
   updated_at DATETIME,
   PRIMARY KEY (id),
   CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS follows
(
   id INT NOT NULL AUTO_INCREMENT,
   followee_id INT,
   follwer_id INT,
   created_at DATETIME,
   updated_at DATETIME,
   PRIMARY KEY (id),
   CONSTRAINT FOREIGN KEY (followee_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE,
   CONSTRAINT FOREIGN KEY (follwer_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS labels
(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR (255),
   record_id INT,
   end_date DATETIME,
   created_at DATETIME,
   updated_at DATETIME,
   PRIMARY KEY (id),
   CONSTRAINT FOREIGN KEY (record_id) REFERENCES records (id) ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS posts
(
   id INT NOT NULL AUTO_INCREMENT,
   record_id INT,
   title VARCHAR (255),
   description VARCHAR (255),
   image_url VARCHAR (255),
   status INT,
   created_at DATETIME,
   updated_at DATETIME,
   PRIMARY KEY (id),
   CONSTRAINT FOREIGN KEY (record_id) REFERENCES records (id) ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS comments
(
   id INT NOT NULL AUTO_INCREMENT,
   record_id INT,
   post_id INT,
   user_id INT,
   status INT,
   content VARCHAR (255),
   created_at DATETIME,
   updated_at DATETIME,
   PRIMARY KEY (id),
   CONSTRAINT FOREIGN KEY (record_id) REFERENCES records (id) ON DELETE RESTRICT ON UPDATE CASCADE,
   CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE,
   CONSTRAINT FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS likes
(
   id INT NOT NULL AUTO_INCREMENT,
   user_id INT,
   post_id INT,
   comment_id INT,
   created_at DATETIME,
   updated_at DATETIME,
   PRIMARY KEY (id),
   CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE,
   CONSTRAINT FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE RESTRICT ON UPDATE CASCADE,
   CONSTRAINT FOREIGN KEY (comment_id) REFERENCES comments (id) ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS l_activity_types
(
   id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR (255),
   PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS s_activity_types
(
   id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR (255),
   PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS activities
(
   id INT NOT NULL AUTO_INCREMENT,
   comments VARCHAR (255),
   label_id INT,
   l_activity_type_id INT,
   s_activity_type_id INT,
   created_at DATETIME,
   updated_at DATETIME,
   PRIMARY KEY (id),
   CONSTRAINT FOREIGN KEY (label_id) REFERENCES labels (id) ON DELETE RESTRICT ON UPDATE CASCADE,
   CONSTRAINT FOREIGN KEY (l_activity_type_id) REFERENCES l_activity_types (id) ON DELETE RESTRICT ON UPDATE CASCADE,
   CONSTRAINT FOREIGN KEY (s_activity_type_id) REFERENCES s_activity_types (id) ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS goodjobs
(
   id INT NOT NULL AUTO_INCREMENT,
   received_user_id INT,
   send_user_id INT,
   activitiy_id INT,
   created_at DATETIME,
   updated_at DATETIME,
   PRIMARY KEY (id),
   CONSTRAINT FOREIGN KEY (received_user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE,
   CONSTRAINT FOREIGN KEY (send_user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE,
   CONSTRAINT FOREIGN KEY (activitiy_id) REFERENCES activities (id) ON DELETE RESTRICT ON UPDATE CASCADE
);