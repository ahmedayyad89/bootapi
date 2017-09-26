CREATE TABLE user
(
    id int(11) NOT NULL AUTO_INCREMENT,
    mobileNumber varchar(255),
    name varchar(255),
    password varchar(255),
    role varchar(255),
    email varchar(255) ,
    UNIQUE (email),
    PRIMARY KEY (id)
);

CREATE TABLE note
(
    date date NOT NULL,
    note varchar(255),
    humidity float,
    pressure float,
    temp float,
    temp_max float,
    temp_min float ,
    PRIMARY KEY (date)
);

CREATE TABLE predefinednotes
(
    id int(11) NOT NULL AUTO_INCREMENT,
    maximumTemperture double,
    message varchar(255),
    minimumTemperture double,
    PRIMARY KEY (id)
);