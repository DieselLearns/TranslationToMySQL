# SELECT * FROM mydbtest.users;
# insert into users (name, age, emal) VALUES
# ('Gir', 5 ,'gir@mail.ru'),
# ('Rutor',22 ,'Rutor@mail.ru'),
# ('Gogol',23 ,'gogol@mail.com'),
# ('Pushkin', 24,'pushkin@gmail.com'),
# ('Mikhalkov',25 ,'miha@yandex.ru'),
# ('Dostoi', 25,'dost@rambler.ru'),
# ('Kabaeva', 25,'kaba@yahoo.com');

CREATE TABLE mydbtest.users (
    id INT NOT NULL AUTO_INCREMENT,
    anim_name VARCHAR(45) NULL ,
    anim_desc VARCHAR(255) NULL ,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
)