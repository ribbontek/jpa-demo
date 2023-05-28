create database ribbontek;
begin;
create user jpademo with password 'jpademo';
grant connect on database ribbontek to jpademo;
grant all privileges on database ribbontek to postgres;
commit;