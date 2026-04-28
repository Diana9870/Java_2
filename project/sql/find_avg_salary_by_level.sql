SELECT level, AVG(salary) AS avg_salary
FROM worker
GROUP BY level;