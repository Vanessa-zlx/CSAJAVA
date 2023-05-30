DROP TABLE cqupt_student;
DROP TABLE CourseAa;
DROP TABLE ChooseBb;
DROP TABLE test;
DROP TABLE test_temp;

CREATE TABLE cqupt_student(
	studentid VARCHAR(10),
	name VARCHAR(20),
	sex VARCHAR(2),
	age INTEGER,
	Fee DECIMAL(10,2),
	address VARCHAR(50),
	memo VARCHAR(300)
);
CREATE TABLE CourseAa(
	Aa1 VARCHAR(20),
	Aa2 INTEGER,
	Aa3 DECIMAL(10)
);
CREATE TABLE ChooseBb(
	Bb1 VARCHAR(30),
	Bb2 INTEGER,
	Bb3 DECIMAL(6)
);
ALTER TABLE ChooseBb ADD Bb4 VARCHAR(20) NOT NULL 
	DEFAULT('系统测试值');

ALTER TABLE ChooseBb ADD Bb5 VARCHAR(10) PRIMARY KEY;

CREATE VIEW View_Choosebb(View_bb1,View_bb2,view_bb3)
AS SELECT Bb1,Bb4,Bb5 FROM ChooseBb;

DROP VIEW View_Choosebb;

CREATE INDEX Index_bb2 ON ChooseBb(Bb2 ASC);
CREATE INDEX Index_bb4 ON ChooseBb(Bb4 DESC);

DROP INDEX Index_bb4 ON ChooseBb;

CREATE TABLE test(
	Name VARCHAR(20),
	Age INTEGER,
	Score NUMERIC(10,2),
	Address VARCHAR(60)
);

INSERT into test
VALUES('赵一',20,580.00,'重邮宿舍 12-3-5');
INSERT into test
VALUES('钱二',19,540.00,'南福苑 5-2-9');
INSERT into test
VALUES('孙三',21,555.50,'学生新区 21-5-15');
INSERT into test
VALUES('李四',22,505.00,'重邮宿舍 8-6-22');
INSERT into test
VALUES('周五',20,495.50,'学生新区 23-4-8');
INSERT into test
VALUES('吴六',19,435.00,'南福苑 2-5-12');

CREATE TABLE test_temp(
	Name VARCHAR(20),
	Age INTEGER,
	Score NUMERIC(10,2),
	Address VARCHAR(60)
);

INSERT into test_temp
VALUES('郑七',21,490.50,'重邮宿舍 11-2-1');
INSERT into test_temp
VALUES('张八',20,560.00,'南福苑 3-3-3');
INSERT into test_temp
VALUES('王九',19,515.00,'学生新区 19-7-1');

INSERT into test select * from test_temp;
UPDATE test set Score=Score+5 WHERE age<=20;
UPDATE test set Age=Age-1 WHERE Address LIKE '南福苑%';
DELETE FROM test WHERE age>=21 AND score>=500;
DELETE from test WHERE score<550 and address LIKE '重邮宿舍%';

CREATE table Student (
	SNO VARCHAR(20),
	Name VARCHAR(10),
	Age INTEGER,
	College VARCHAR(30)
);

CREATE TABLE Course(
	CourseID VARCHAR(15),
	CourseName VARCHAR(30),
	CourseBeforeID VARCHAR(15));

CREATE TABLE Choose(
	SNO VARCHAR(20),
	CourseID VARCHAR(30),
	Score DECIMAL(5,2));

INSERT INTO Student VALUES('S00001','张三',20,'计算机学院');
INSERT INTO Student VALUES('S00002','李四',19,'通信学院');
INSERT INTO Student VALUES('S00003','王五',21,'计算机学院');

INSERT INTO Course VALUES('C1','计算机引论',NULL);
INSERT INTO Course VALUES('C2','C语言','C1');
INSERT INTO Course VALUES('C3','数据结构','C2');

INSERT INTO Choose VALUES('S00001','C1',95);
INSERT INTO Choose VALUES('S00001','C2',80);
INSERT INTO Choose VALUES('S00001','C3',84);
INSERT INTO Choose VALUES('S00002','C1',80);
INSERT INTO Choose VALUES('S00002','C2',85);
INSERT INTO Choose VALUES('S00003','C1',78);
INSERT INTO Choose VALUES('S00003','C3',70);

SELECT SNO,Name from Student where College='计算机学院';
SELECT * from Student where Age BETWEEN 20 AND 23;
SELECT COUNT(*) from Student;

SELECT MAX(Score) from Choose WHERE CourseID = 'C1';
SELECT MIN(Score) from Choose WHERE CourseID = 'C1';
SELECT SUM(Score) from Choose WHERE CourseID = 'C1';
SELECT AVG(Score) from Choose WHERE CourseID = 'C1';

SELECT CourseID,CourseName from Course WHERE CourseBeforeID is null;

SELECT Choose.SNO,Name,CourseName,Score from Choose,Course,Student
WHERE Choose.SNO = Student.SNO AND Choose.CourseID = Course.CourseID;

SELECT * from Student s1 WHERE s1.Name!='张三' AND EXISTS (
SELECT * from Student s2 WHERE s2.College=s1.College AND Name='张三');

SELECT Choose.SNO,Score from Choose,Student
WHERE Choose.SNO = Student.SNO AND CourseID='C1' 
AND Score<(SELECT Score from Choose,Student
WHERE Choose.SNO = Student.SNO AND Choose.CourseID='C1' AND Student.Name = '张三');


SELECT SNO FROM Choose WHERE CourseID='C1' 
UNION SELECT SNO FROM Choose WHERE CourseID='C3';

SELECT SNO FROM Choose WHERE CourseID='C1' 
UNION DISTINCT SELECT SNO FROM Choose WHERE CourseID='C3';