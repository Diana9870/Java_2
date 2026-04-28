SELECT c.name, SUM(w.salary) AS total_salary
FROM client c
         JOIN project p ON c.id = p.client_id
         JOIN project_worker pw ON p.id = pw.project_id
         JOIN worker w ON pw.worker_id = w.id
GROUP BY c.name
ORDER BY total_salary DESC
    LIMIT 1;