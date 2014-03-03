--liquibase formatted sql

INSERT INTO PERSON (ID,NAME,AGE,TEXT) VALUES (1,'John',13,'one line text');

INSERT INTO PERSON (ID,NAME,AGE,TEXT) VALUES (2,'',23,'');

INSERT INTO PERSON (ID,NAME,AGE,TEXT) VALUES (3,'Jane',28,'Hello!

This is not a very
long message.

    Regards,
    Jane');

