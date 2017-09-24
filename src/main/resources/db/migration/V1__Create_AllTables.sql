CREATE TABLE user
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    mobileNumber varchar(255),
    name varchar(255),
    password varchar(255),
    role varchar(255),
    email varchar(255)
);
CREATE UNIQUE INDEX user_email_uindex ON user (email);

CREATE TABLE note
(
    date date PRIMARY KEY NOT NULL,
    note varchar(255),
    humidity float,
    pressure float,
    temp float,
    temp_max float,
    temp_min float
);

CREATE TABLE predefinednotes
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fifteenTo20 varchar(255),
    moreThat20 varchar(255),
    oneTo10 varchar(255),
    tenTo15 varchar(255)
);

