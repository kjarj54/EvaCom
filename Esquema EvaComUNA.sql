-- Create the user 
create user EvaComUNA
  identified by una
  default tablespace USERS
  temporary tablespace TEMP
  profile DEFAULT;
-- Grant/Revoke role privileges 
grant connect to EvaComUNA;
grant resource to EvaComUNA;
grant dba to EvaComUNA;
-- Grant/Revoke system privileges 
grant unlimited tablespace to EvaComUNA;
grant create session to EvaComUNA;
