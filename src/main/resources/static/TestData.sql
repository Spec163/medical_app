insert into role_entity values (1, 'ROLE_ADMIN') on conflict do nothing;
insert into role_entity values (2, 'ROLE_MANAGER') on conflict do nothing;
insert into role_entity values (3, 'ROLE_EMPLOYEE') on conflict do nothing;

insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (201, 111, 50, true, 'teacher201', 'teacher201', 1, 'teacher201', 'teacher201');
insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (202, 112, 50, true, 'teacher202', 'teacher202', 1, 'teacher202', 'teacher202');
insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (203, 113, 50, true, 'teacher203', 'teacher203', 1, 'teacher203', 'teacher203');
insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (204, 114, 60, true, 'teacher204', 'teacher204', 1, 'teacher204', 'teacher204');
insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (205, 115, 60, true, 'teacher205', 'teacher205', 1, 'teacher205', 'teacher205');
insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (206, 116, 60, true, 'teacher206', 'teacher206', 1, 'teacher206', 'teacher206');
insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (207, 117, 70, true, 'teacher207', 'teacher207', 1, 'teacher207', 'teacher207');
insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (208, 118, 70, true, 'teacher208', 'teacher208', 1, 'teacher208', 'teacher208');
insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (209, 119, 70, true, 'teacher209', 'teacher209', 1, 'teacher209', 'teacher209');
insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (210, 120, 80, true, 'teacher210', 'teacher210', 1, 'teacher210', 'teacher210');
insert into user_entity (user_id, account_id, department_id, is_active_user, job_title, name, parent_id, patronymic, surname) values (211, 121, 80, true, 'teacher211', 'teacher211', 1, 'teacher211', 'teacher211');



insert into schedule (schedule_id, comment, end_period, organization_id, start_period, status, max_number_of_people) VALUES (301, null, '2023-05-25', 1, '2023-04-25', 'ACTIVE', 5);
insert into schedule (schedule_id, comment, end_period, organization_id, start_period, status, max_number_of_people) VALUES (302, null, '2023-07-27', 1, '2023-06-27', 'ACTIVE', 7);

insert into available_date (available_date_id, date, count, schedule_id) values (35, '2023-04-25', 0, 301);
insert into available_date (available_date_id, date, count, schedule_id) values (36, '2023-04-26', 0, 301);
insert into available_date (available_date_id, date, count, schedule_id) values (37, '2023-04-27', 0, 301);
insert into available_date (available_date_id, date, count, schedule_id) values (38, '2023-04-28', 0, 301);
insert into available_date (available_date_id, date, count, schedule_id) values (39, '2023-04-29', 0, 301);
insert into available_date (available_date_id, date, count, schedule_id) values (40, '2023-04-30', 0, 301);
insert into available_date (available_date_id, date, count, schedule_id) values (41, '2023-01-01', 0, 301);

insert into meeting (meeting_id, comment, date, schedule_id, status, user_id) values (421, null, '2023-07-11', 302, 'WAITING_FOR_APPROVE', 202);
