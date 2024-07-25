create table  if not exists Users
(
    id          int auto_increment primary key,
    name        varchar(45),
    surname     varchar(45),
    age         int,
    email       varchar(55),
    password    varchar(55),
    phoneNumber varchar(20),
    avatar      varchar(255),
    accountType varchar(45)
);

create table if not exists Category
(
    id       int auto_increment primary key,
    name     varchar(45),
    parentId int,
    foreign key (parentId) references Category (id)
);

create table  if not exists Resume
(
    id          int auto_increment primary key,
    applicantId int,
    name        varchar(45),
    categoryId  int,
    salary      double,
    isActive    boolean,
    createdDate date,
    updateTime  date,
    foreign key (applicantId) references Users (id),
    foreign key (categoryId) references Category (id)
);

create table  if not exists Vacancy
(
    id          int auto_increment primary key,
    name        varchar(45),
    description text,
    categoryId  int,
    salary      double,
    expFrom     int,
    expTo       int,
    isActive    boolean,
    authorId    int,
    createdDate date,
    updateTime  date,
    foreign key (categoryId) references Category (id),
    foreign key (authorId) references Users (id)
);

create table  if not exists ContactType
(
    id int auto_increment primary key,
    type varchar(255)
);

create table  if not exists ContactInfo
(
    id           int auto_increment primary key,
    typeId       int,
    resumeId     int,
    contactValue varchar(255),
    foreign key  (typeId) references ContactType (id),
    foreign key  (resumeId) references Resume (id)
);

create table  if not exists EducationInfo
(
    id          int auto_increment primary key,
    resumeId    int,
    institution varchar(255),
    program     varchar(255),
    startDate   date,
    endDate     date,
    degree      varchar(255),
    foreign key (resumeId) references Resume (id)
);

create table  if not exists WorkExperienceInfo
(
    id               int auto_increment primary key,
    years            int,
    companyName      varchar(255),
    position         varchar(255),
    responsibilities text
);

create table  if not exists RespondedApplicant
(
    id           int auto_increment primary key,
    resumeId     int,
    vacancyId    int,
    confirmation boolean,
    foreign key (resumeId) references Resume (id),
    foreign key (vacancyId) references Vacancy (id)
);

create table  if not exists Message
(
    id                 int auto_increment primary key,
    respondedApplicant int,
    text               text,
    timeStamp          date,
    foreign key (respondedApplicant) references RespondedApplicant (id)
);
