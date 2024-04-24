CREATE TABLE IF NOT EXISTS tbl_role_permission(
    id SERIAL PRIMARY KEY,
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES tbl_role(id),
    FOREIGN KEY (permission_id) REFERENCES tbl_permission(id)
)