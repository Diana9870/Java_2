SELECT c.name, MAX(DATEDIFF('MONTH', p.start_date, p.finish_date)) AS max_duration
FROM client c
         JOIN project p ON c.id = p.client_id
GROUP BY c.name;