-- Experience
INSERT INTO portfolio.experience (company, role, period, location, description_es, description_en,
  highlights_es, highlights_en, stack, sort_order) VALUES
('NTTData', 'Senior Software Engineer - Technical Lead', '2025 — Present',
 'Lima, Perú (Hybrid)',
 'Desarrollé microservicios para el equipo de Telecredito del BCP usando el Agente de Copilot.',
 'Developed microservices for BCP Telecredito team leveraging the Copilot Agent.',
 '["Desarrollé 5+ APIs en Azure (Blob, SQL, EventHub, APIM) soportando 1K+ transacciones bancarias diarias",
   "Reduje tiempos de desarrollo usando IA (ChatGPT) para código, pruebas (99% coverage) y documentación",
   "Incrementé la productividad del equipo en 30%",
   "Impulsé el uso de herramientas de IA mejorando velocidad de entrega"]',
 '["Built 5+ APIs on Azure (Blob, SQL, EventHub, APIM) supporting 1K+ daily banking transactions",
   "Reduced development time using AI (ChatGPT) for code generation, testing (99% coverage) and docs",
   "Increased team productivity by 30%",
   "Championed AI tooling adoption improving delivery speed and code quality"]',
 '["RxJava","Quarkus","Jenkins","JFrog","Kibana","Github Copilot"]', 1),

('Globant', 'SemiSenior Java Developer', '2022 — 2025', 'Lima, Perú (Remote)',
 'Desarrollé múltiples microservicios con Spring, fortaleciendo conocimientos en Azure.',
 'Developed multiple microservices with Spring, deepening expertise in Azure cloud services.',
 '["Reconocimiento por MGM Resorts por completar una integración antes del plazo",
   "Implementé pipelines en Azure Data Factory para migrar datos a Azure SQL Database",
   "Optimicé el rendimiento usando Azure Functions y Azure Databricks",
   "Coordiné equipo para una billetera virtual (50K+ transacciones diarias)"]',
 '["Recognized by MGM Resorts for completing an integration ahead of schedule",
   "Implemented Azure Data Factory pipelines to migrate data to Azure SQL Database",
   "Optimized performance using Azure Functions and Azure Databricks",
   "Coordinated team for a virtual wallet handling 50K+ daily transactions"]',
 '["DataFactory","Functions","React","TypeScript","MongoDB","PowerApps","Active Directory","Dynatrace","Argo"]', 2);

-- Skills
INSERT INTO portfolio.skills (category_es, category_en, icon, items, sort_order) VALUES
('Lenguajes',    'Languages',    'layers',   '["Java","JavaScript","TypeScript","SQL","Bash"]', 1),
('Frontend',     'Frontend',     'monitor',  '["React","Next.js","Tailwind CSS","Angular"]', 2),
('Backend',      'Backend',      'server',   '["Java","Node.js","Express","NestJS","Spring","Quarkus"]', 3),
('Cloud & DevOps','Cloud & DevOps','cloud',  '["Azure","AWS","Docker","Kubernetes","Terraform","Argo"]', 4),
('Bases de datos','Databases',   'database', '["PostgreSQL","MongoDB","Redis","SQL Server","DynamoDB","Oracle"]', 5),
('Herramientas', 'Tools',        'tool',     '["Confluence","Jira","Git","GitHub Actions","Figma","Postman","Datadog","Dynatrace"]', 6);

-- Projects
INSERT INTO portfolio.projects (name, description_es, description_en, metrics, stack, featured, github_url, sort_order) VALUES
('Kafka Retry and Dead Letter Queue',
 'Proyecto para profundizar en los conceptos de manejo de eventos con reintentos y colas de mensajes fallidos usando Kafka y Spring.',
 'Project to deepen knowledge of event handling with retries and dead letter queues using Kafka and Spring.',
 'retry 2+ sec', '["Java","Spring","Kafka"]', true,
 'https://github.com/DxrosNgu/kafka-using-spring-boot', 1),
('Clients Integration',
 'Integración de múltiples tecnologías para conexión con clientes, demostrando distintos patrones de comunicación entre servicios.',
 'Multi-technology integration demonstrating different inter-service communication patterns.',
 '3 client types', '["Java","SpringBoot","gRPC","Feign","RestTemplate"]', false,
 'https://github.com/DxrosNgu/communicate', 2);

-- Certifications
INSERT INTO portfolio.certifications (name, issuer, year, badge, sort_order) VALUES
('AZ-900 Azure Fundamentals',          'Microsoft Azure',        '2021', 'Azure', 1),
('AZ-204 Microsoft Azure Developer',   'Microsoft Azure',        '2021', 'Azure', 2),
('Google Cloud Digital Leader',        'Google Cloud',           '2025', 'GCP',   3),
('AWS Certified Cloud Practitioner',   'Amazon Web Services',    '2026', 'AWS',   4),
('AZ-104 Microsoft Azure Administrator','Microsoft Azure',       '2026', 'Azure', 5);

-- Education
INSERT INTO portfolio.education (institution, degree_es, degree_en, period, location) VALUES
('Universidad Tecnológica del Perú', 'Ingeniería de Sistemas', 'Systems Engineering', '2015 — 2020', 'Lima, Perú');
