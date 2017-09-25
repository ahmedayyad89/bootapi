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

CREATE TABLE weather_app.predefinednotes
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    maximumTemperture double,
    message varchar(255),
    minimumTemperture double
);