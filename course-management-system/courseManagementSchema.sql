create database courseManagementDB;
use courseManagementDB;

create table department(
	id int primary key auto_increment,
    deptName varchar(255) not null
);

create table course(
	id int primary key auto_increment,
    courseName varchar(255) not null,
    department int,
    semester tinyint,
    days char(5),
    startTime time,
    endTime time,
    constraint deptCourse foreign key(department) references department(id)
    on delete cascade
);

create table student(
	rollNo int primary key auto_increment,
    name varchar(255) not null,
    semester tinyint,
    department int not null,
    email varchar(255),
    password varchar(255) not null,
    credits int,
    constraint deptStudent foreign key(department) references department(id)
    on delete cascade
);

create table tutor(
	id int primary key auto_increment,
    name varchar(255) not null,
    email varchar(255),
    department int not null,
    constraint deptTutor foreign key(department) references department(id)
    on delete cascade
);

create table studenttocourse(
	id int primary key auto_increment,
    student int not null,
    course int not null,
    foreign key(student) references student(rollNo) on delete cascade,
    foreign key(course) references course(id) on delete cascade
);

create table tutortocourse(
	id int primary key auto_increment,
    tutor int not null,
    course int not null,
    foreign key(tutor) references tutor(id) on delete cascade,
    foreign key(course) references course(id) on delete cascade
);
