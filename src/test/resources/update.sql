--liquibase formatted sql

UPDATE PERSON SET ID=1,NAME='John',AGE=13,TEXT='one line text' WHERE ID=1;

UPDATE PERSON SET ID=2,NAME='',AGE=23,TEXT='' WHERE ID=2;

UPDATE PERSON SET ID=3,NAME='Jane',AGE=28,TEXT='Hello!

This is not a very
long message.

    Regards,
    Jane' WHERE ID=3;

