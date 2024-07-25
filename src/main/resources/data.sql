INSERT INTO Users (name, surname, age, email, password, phoneNumber, avatar, accountType)
VALUES ('Ivan', 'Ivanov', 30, 'ivan@example.com', 'password123', '1234567890', 'avatar1.png', 'applicant'),
       ('Company', 'Ltd', 0, 'company@example.com', 'password456', '0987654321', 'avatar2.png', 'employer');

INSERT INTO Category (name, parentId)
VALUES ('IT', NULL),
       ('Development', (SELECT id FROM Category WHERE name='IT' LIMIT 1));

INSERT INTO Resume (applicantId, name, categoryId, salary, isActive, createdDate, updateTime)
VALUES ((SELECT id FROM Users WHERE name='Ivan' LIMIT 1), 'Developer Resume 1', (SELECT id FROM Category WHERE name='Development' LIMIT 1), 1000.0, TRUE, '2023-01-01', '2023-07-01'),
       ((SELECT id FROM Users WHERE name='Ivan' LIMIT 1), 'Developer Resume 2', (SELECT id FROM Category WHERE name='Development' LIMIT 1), 1500.0, TRUE, '2023-02-01', '2023-07-15');

INSERT INTO Vacancy (name, description, categoryId, salary, expFrom, expTo, isActive, authorId, createdDate, updateTime)
VALUES ('Junior Developer', 'Looking for a junior developer', (SELECT id FROM Category WHERE name='Development' LIMIT 1), 1200.0, 0, 2, TRUE, (SELECT id FROM Users WHERE name='Company' LIMIT 1), '2023-03-01', '2023-07-01'),
       ('Senior Developer', 'Looking for a senior developer', (SELECT id FROM Category WHERE name='Development' LIMIT 1), 2000.0, 5, 10, TRUE, (SELECT id FROM Users WHERE name='Company' LIMIT 1), '2023-04-01', '2023-07-10');

INSERT INTO RespondedApplicant (resumeId, vacancyId, confirmation)
VALUES ((SELECT id FROM Resume WHERE name='Developer Resume 1' LIMIT 1), (SELECT id FROM Vacancy WHERE name='Junior Developer' LIMIT 1), TRUE);

INSERT INTO Message (respondedApplicant, text, timeStamp)
VALUES ((SELECT id FROM RespondedApplicant WHERE resumeId=(SELECT id FROM Resume WHERE name='Developer Resume 1' LIMIT 1) AND vacancyId=(SELECT id FROM Vacancy WHERE name='Junior Developer' LIMIT 1) LIMIT 1), 'I am interested in this vacancy.', '2023-07-01');

INSERT INTO ContactType (type)
VALUES ('Email'),
       ('Phone');

INSERT INTO ContactInfo (typeId, resumeId, contactValue)
VALUES ((SELECT id FROM ContactType WHERE type='Email' LIMIT 1), (SELECT id FROM Resume WHERE name='Developer Resume 1' LIMIT 1), 'ivan@example.com'),
       ((SELECT id FROM ContactType WHERE type='Phone' LIMIT 1), (SELECT id FROM Resume WHERE name='Developer Resume 1' LIMIT 1), '1234567890');

INSERT INTO EducationInfo (resumeId, institution, program, startDate, endDate, degree)
VALUES ((SELECT id FROM Resume WHERE name='Developer Resume 1' LIMIT 1), 'IT University', 'Computer Science', '2015-09-01', '2019-06-30', 'Bachelor');

INSERT INTO WorkExperienceInfo (years, companyName, position, responsibilities)
VALUES (3, 'Tech Company', 'Junior Developer', 'Developing software applications');
