create database [stitchedstamped-local];
GO
exec sp_configure 'contained database authentication', 1;
GO

reconfigure;
GO

use [stitchedstamped-local];
alter database [stitchedstamped-local]
    set containment = partial;
GO

create user stitchedstampeduser with password = '7eTasP2BrAFE5usa';
grant CONTROL, ALTER to stitchedstampeduser;
EXEC sp_addrolemember 'db_ddladmin', 'stitchedstampeduser';
GO
