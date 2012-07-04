create user 'upf2012' identified by 'upf2012';
create database upf2012;
GRANT ALL ON upf2012.* TO 'upf2012'@'localhost';
SET PASSWORD FOR 'upf2012'@'%' = PASSWORD('upf2012');
SET PASSWORD FOR 'upf2012'@'localhost' = PASSWORD('upf2012');
commit;


