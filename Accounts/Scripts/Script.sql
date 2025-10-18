INSERT INTO "tenant_nana_tech_world1_DB"."entity_masters"
(entity_name, table_name, primary_key_column_name, description, is_active, created_by)
VALUES
('APPROVAL_CONFIGURATION', 'approval_configurations', 'approval_configuration_id', 'Stores approval configuration settings', TRUE, 'system'),
('APPROVAL_LEVEL', 'approval_levels', 'approval_level_id', 'Stores approval level configurations', TRUE, 'system');


SELECT entity_id, entity_name, table_name, primary_key_column_name, description, is_active, created_at, updated_at, created_by, updated_by
FROM "tenant_nana_tech_world1_DB".entity_masters;