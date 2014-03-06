--liquibase formatted sql

UPDATE PERSON SET NAME='John',AGE=13,TEXT='one line text' WHERE ID=1;

UPDATE PERSON SET NAME='',AGE=23,TEXT='' WHERE ID=2;

UPDATE PERSON SET NAME='Jane',AGE=28,TEXT='Hello!

This is not a very
long message.

    Regards,
    Jane' WHERE ID=3;

UPDATE PERSON SET NAME='Jack',AGE=44,TEXT='${url} = http://host:80/test?x=1&y=""' WHERE ID=4;

