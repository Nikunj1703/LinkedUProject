drop table studentDb.Students;

create table studentDb.Students (
  FirstName                  VARCHAR(55),
  LastName                   VARCHAR(55),
  Email                  VARCHAR(55),
  Password1                      VARCHAR(25),
  Password2                    VARCHAR(25),
  Dob                  VARCHAR(50),
  ContactNo                   NUMERIC(10),
  Address1                  VARCHAR(55),
  Address2                  VARCHAR(55),
  City                  VARCHAR(25),
  States                   VARCHAR(25),
  Country                  VARCHAR(25),
  Zip                  VARCHAR(25),
  SchoolName                  VARCHAR(55),
  Gpa                  VARCHAR(25),
  Exam                  VARCHAR(25),
  Score                  NUMERIC(3),
  TargetSchool                  VARCHAR(500),
  Major                  VARCHAR(25),
  ExtraC                  VARCHAR(55),
  Awards                  VARCHAR(50),
  Sports                  VARCHAR(50),
photoPath                  VARCHAR(900),
sop                  VARCHAR(9000)
);
