CREATE TABLE IF NOT EXISTS tbl_user_role(
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tbl_user(id),
    FOREIGN KEY (role_id) REFERENCES tbl_role(id)
)