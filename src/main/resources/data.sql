INSERT INTO Members(username, pass, enabled)
VALUES ('Simon', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', TRUE);
INSERT INTO Members(username, pass, enabled)
VALUES ('Snehal', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', TRUE);

INSERT INTO Role(rolename)
VALUES('ROLE_ADMIN');
INSERT INTO Role(rolename)
VALUES('ROLE_USER');


INSERT INTO Members_Role_list
VALUES(1,1);
INSERT INTO Members_Role_list
VALUES(1,2);
INSERT INTO Members_Role_list
VALUES(2,2);



INSERT INTO Threads(topic, message, name, thread_Date, thread_Time)
VALUES ('PSG vs Bayern', 'Great assit nice score', 'Pochitino', '2021-04-04', '02:30:45');
INSERT INTO Threads(topic, message, name, thread_Date, thread_Time)
VALUES ('Manc vs BVB', 'Nice Work Team', 'Pep', '2021-04-05', '08:30:45');

INSERT INTO Comment(id, comment, name, cdate, ctime)
VALUES (1, 'Awesome.', 'Neymar', '2021-04-04', '11:30:45');
INSERT INTO Comment(id, comment, name, cdate, ctime)
VALUES (2, 'Keep it up.', 'Messi', '2021-04-05', '09:30:45');

INSERT INTO Threads_comment_list(Threads_ID, COMMENT_LIST_ID)
VALUES(1,1);
INSERT INTO Threads_comment_list(Threads_ID, COMMENT_LIST_ID)
VALUES(2,2);
