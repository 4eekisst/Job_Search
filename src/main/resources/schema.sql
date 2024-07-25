create table  if not exists Users
(
    id          int auto_increment primary key,
    name        VARCHAR(255),
    surname     VARCHAR(255),
    age         INT,
    email       VARCHAR(255),
    password    VARCHAR(255),
    phoneNumber VARCHAR(20),
    avatar      VARCHAR(255),
    accountType VARCHAR(50)
);

create table  if not exists Category
(
    id       int auto_increment primary key,
    name     VARCHAR(255),
    parentId BIGINT,
    FOREIGN KEY (parentId) REFERENCES Category (id)
);

create table  if not exists Resume
(
    id          int auto_increment primary key,
    applicantId BIGINT,
    name        VARCHAR(255),
    categoryId  BIGINT,
    salary DOUBLE,
    isActive    BOOLEAN,
    createdDate DATE,
    updateTime  DATE,
    FOREIGN KEY (applicantId) REFERENCES Users (id),
    FOREIGN KEY (categoryId) REFERENCES Category (id)
);

create table  if not exists Vacancy
(
    id          int auto_increment primary key,
    name        VARCHAR(255),
    description TEXT,
    categoryId  BIGINT,
    salary DOUBLE,
    expFrom     INT,
    expTo       INT,
    isActive    BOOLEAN,
    authorId    BIGINT,
    createdDate DATE,
    updateTime  DATE,
    FOREIGN KEY (categoryId) REFERENCES Category (id),
    FOREIGN KEY (authorId) REFERENCES Users (id)
);

create table  if not exists ContactType
(
    id int auto_increment primary key,
    type VARCHAR(255)
);

create table  if not exists ContactInfo
(
    id           int auto_increment primary key,
    typeId       BIGINT,
    resumeId     BIGINT,
    contactValue VARCHAR(255),
    FOREIGN KEY (typeId) REFERENCES ContactType (id),
    FOREIGN KEY (resumeId) REFERENCES Resume (id)
);

create table  if not exists EducationInfo
(
    id          int auto_increment primary key,
    resumeId    BIGINT,
    institution VARCHAR(255),
    program     VARCHAR(255),
    startDate   DATE,
    endDate     DATE,
    degree      VARCHAR(255),
    FOREIGN KEY (resumeId) REFERENCES Resume (id)
);

create table  if not exists WorkExperienceInfo
(
    id               int auto_increment primary key,
    years            INT,
    companyName      VARCHAR(255),
    position         VARCHAR(255),
    responsibilities TEXT
);

create table  if not exists RespondedApplicant
(
    id           int auto_increment primary key,
    resumeId     BIGINT,
    vacancyId    BIGINT,
    confirmation BOOLEAN,
    FOREIGN KEY (resumeId) REFERENCES Resume (id),
    FOREIGN KEY (vacancyId) REFERENCES Vacancy (id)
);

create table  if not exists Message
(
    id                 int auto_increment primary key,
    respondedApplicant BIGINT,
    text               TEXT,
    timeStamp          DATE,
    FOREIGN KEY (respondedApplicant) REFERENCES RespondedApplicant (id)
);
