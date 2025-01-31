--Part 1
select * from job;
--Part 2
select name from employer where location="St. Louis City";
--Part 3
DROP TABLE job;
--Part 4
select name from skill where id in(select skills_id from  job_skills
inner join job on job_skills.jobs_id = job.id) order by name asc;

