INSERT INTO Authorities(authority)
VALUES ('applicant'),
       ('employer');

INSERT INTO Users (name, surname, age, email, password, phoneNumber, avatar, enabled, authority_id)
VALUES ('Ivan', 'Ivanov', 30, 'ivan@example.com', '$2a$12$rTMjQ/Uw9Q8fuHZRYnWFF.CFenZn42KlTLRHc2bS6rKovVnz3U6Be',
        '1234567890', NULL, true, '1'),
       ('qwe', 'qwe', 33, 'qwe@qwe.qwe', '$2a$12$OsUEglL6BGMf7oqc3YD7V.PWAw/PKkR7atf0Bnz4ExoLfze/7M/gK', '0557057557',
        NULL, true, '1'),
       ('DetalingStudio', '', 3, 'detaling@example.com',
        '$2a$12$YRF7dh4FstGNEkZLnD7Mve0IRP0HysM7Uul6SnQ09nQ9HbXWi2qL2', '0987654321', NULL, true, '2');


INSERT INTO Category (name, PARENT_ID)
VALUES ('IT', NULL),
       ('Development', (SELECT id FROM Category WHERE name = 'IT' LIMIT 1)),
       ('Design', (SELECT id FROM Category WHERE name = 'IT' LIMIT 1));

INSERT INTO Resume (APPLICANT_ID, name, CATEGORY_ID, salary, IS_ACTIVE, CREATED_DATE, UPDATE_TIME)
VALUES ((SELECT id FROM Users WHERE name = 'Ivan' LIMIT 1), 'Developer Resume 1',
        (SELECT id FROM Category WHERE name = 'Development' LIMIT 1), 1000.0, TRUE, '2023-01-01', '2023-07-01'),
       ((SELECT id FROM Users WHERE name = 'Ivan' LIMIT 1), 'Developer Resume 2',
        (SELECT id FROM Category WHERE name = 'Development' LIMIT 1), 1500.0, TRUE, '2023-02-01', '2023-07-15');

INSERT INTO Vacancy (name, description, CATEGORY_ID, salary, EXP_FROM, EXP_TO, IS_ACTIVE, AUTHOR_ID, CREATED_DATE, UPDATE_TIME)
VALUES
    ('Junior Developer',
     'Seeking a junior developer with some experience in coding. Ideal for those eager to learn and grow in a supportive team environment. Experience with front-end or back-end technologies is a plus.',
     (SELECT id FROM Category WHERE name = 'Development' LIMIT 1),
     1200.0,
     3,
     5,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-03-01',
     '2023-07-01'),

    ('Senior Developer',
     'Looking for a senior developer with extensive experience in software development. Responsibilities include leading projects, mentoring junior developers, and ensuring high-quality code. Experience with various programming languages required.',
     (SELECT id FROM Category WHERE name = 'Development' LIMIT 1),
     2000.0,
     5,
     10,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-04-01',
     '2023-07-10'),

    ('UX Designer',
     'A creative UX Designer needed to enhance user experiences. Responsibilities include researching user needs, designing wireframes, and collaborating with developers. Previous experience with design tools is a must.',
     (SELECT id FROM Category WHERE name = 'Design' LIMIT 1),
     1500.0,
     2,
     5,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-05-01',
     '2023-08-01'),

    ('Project Manager',
     'Experienced Project Manager required to oversee project development from initiation to completion. Must have experience managing cross-functional teams and ensuring project milestones are met.',
     (SELECT id FROM Category WHERE name = 'IT' LIMIT 1),
     2500.0,
     5,
     12,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-06-01',
     '2023-08-15'),

    ('Frontend Developer',
     'Frontend Developer with experience in modern JavaScript frameworks needed. Responsibilities include developing user interfaces, collaborating with backend developers, and optimizing performance.',
     (SELECT id FROM Category WHERE name = 'Development' LIMIT 1),
     1400.0,
     3,
     7,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-07-01',
     '2023-08-20'),

    ('Backend Developer',
     'Looking for a Backend Developer with strong experience in server-side technologies. Responsibilities include building and maintaining server applications, working with databases, and ensuring scalability.',
     (SELECT id FROM Category WHERE name = 'Development' LIMIT 1),
     1600.0,
     4,
     8,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-08-01',
     '2023-09-01'),

    ('Data Analyst',
     'A Data Analyst with skills in data visualization and statistical analysis needed. Responsibilities include analyzing data trends, creating reports, and supporting decision-making processes.',
     (SELECT id FROM Category WHERE name = 'IT' LIMIT 1),
     1300.0,
     2,
     6,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-09-01',
     '2023-09-15'),

    ('Business Analyst',
     'Experienced Business Analyst required to gather and analyze business requirements. Must be able to communicate effectively with stakeholders and provide actionable insights.',
     (SELECT id FROM Category WHERE name = 'IT' LIMIT 1),
     1400.0,
     3,
     7,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-09-10',
     '2023-10-01'),

    ('SEO Specialist',
     'SEO Specialist needed to improve website visibility and search rankings. Responsibilities include keyword research, on-page optimization, and monitoring SEO performance.',
     (SELECT id FROM Category WHERE name = 'Development' LIMIT 1),
     1200.0,
     2,
     5,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-10-01',
     '2023-10-10'),

    ('Content Writer',
     'Creative Content Writer required to produce engaging and high-quality content. Must have experience with content strategy, SEO writing, and producing content across various platforms.',
     (SELECT id FROM Category WHERE name = 'Design' LIMIT 1),
     1100.0,
     1,
     4,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-10-15',
     '2023-11-01'),

    ('Systems Administrator',
     'Systems Administrator with experience in managing IT infrastructure needed. Responsibilities include maintaining servers, troubleshooting issues, and ensuring system reliability.',
     (SELECT id FROM Category WHERE name = 'IT' LIMIT 1),
     1600.0,
     4,
     8,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-11-01',
     '2023-11-15'),

    ('IT Support Specialist',
     'IT Support Specialist required to provide technical support and resolve IT issues. Must be skilled in troubleshooting hardware and software problems and providing excellent customer service.',
     (SELECT id FROM Category WHERE name = 'IT' LIMIT 1),
     1200.0,
     1,
     3,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-11-15',
     '2023-12-01'),

    ('Graphic Designer',
     'Graphic Designer with strong skills in Adobe Creative Suite needed. Responsibilities include creating visual content, working with clients, and ensuring high-quality design deliverables.',
     (SELECT id FROM Category WHERE name = 'Design' LIMIT 1),
     1300.0,
     2,
     6,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-12-01',
     '2023-12-15'),

    ('Network Engineer',
     'Network Engineer required to design and manage network infrastructure. Must have experience with network security, routing, and troubleshooting connectivity issues.',
     (SELECT id FROM Category WHERE name = 'IT' LIMIT 1),
     1500.0,
     4,
     7,
     TRUE,
     (SELECT id FROM Users WHERE name = 'DetalingStudio' LIMIT 1),
     '2023-12-15',
     '2024-01-01');

INSERT INTO RESPONDED_APPLICANT (RESUME_ID, VACANCY_ID, confirmation)
VALUES ((SELECT id FROM Resume WHERE name = 'Developer Resume 1' LIMIT 1),
        (SELECT id FROM Vacancy WHERE name = 'Junior Developer' LIMIT 1), TRUE);

INSERT INTO Message (RESPONDED_APPLICANT, text, TIME_STAMP)
VALUES ((SELECT id
         FROM RESPONDED_APPLICANT
         WHERE RESUME_ID = (SELECT id FROM Resume WHERE name = 'Developer Resume 1' LIMIT 1)
           AND VACANCY_ID = (SELECT id FROM Vacancy WHERE name = 'Junior Developer' LIMIT 1)
         LIMIT 1), 'I am interested in this vacancy.', '2023-07-01');

INSERT INTO CONTACT_TYPE (type)
VALUES ('Email'),
       ('Phone');

INSERT INTO CONTACT_INFO (TYPE_ID, RESUME_ID, CONTACT_VALUE)
VALUES ((SELECT id FROM CONTACT_TYPE WHERE type = 'Email' LIMIT 1),
        (SELECT id FROM Resume WHERE name = 'Developer Resume 1' LIMIT 1), 'ivan@example.com'),
       ((SELECT id FROM CONTACT_TYPE WHERE type = 'Phone' LIMIT 1),
        (SELECT id FROM Resume WHERE name = 'Developer Resume 1' LIMIT 1), '1234567890');

INSERT INTO EDUCATION_INFO (RESUME_ID, institution, program, START_DATE, END_DATE, degree)
VALUES ((SELECT id FROM Resume WHERE name = 'Developer Resume 1' LIMIT 1), 'IT University', 'Computer Science',
        '2015-09-01', '2019-06-30', 'Bachelor');

INSERT INTO WORK_EXPERIENCE_INFO (resume_id, years, COMPANY_NAME, position, responsibilities)
VALUES (1, 3, 'Tech Company', 'Junior Developer', 'Developing software applications');
