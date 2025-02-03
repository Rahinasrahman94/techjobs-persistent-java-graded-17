--Part 1
select * from job;
--Part 2
SELECT name FROM employer WHERE location = "St. Louis City";
--Part 3
DROP TABLE job;
--Part 4
SELECT name ,description  FROM skill WHERE skill.id in(SELECT job_skills.skills_id FROM  job_skills
INNER JOIN  job ON job_skills.jobs_id = job.id WHERE job_skills.job_id IS NOT NULL) ORDER BY name ASC;


