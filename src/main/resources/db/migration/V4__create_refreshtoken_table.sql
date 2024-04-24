CREATE TABLE IF NOT EXISTS  tbl_refreshtoken(
  id SERIAL PRIMARY KEY,
  reset_key VARCHAR(255) NOT NULL,
  reset_date DATE NOT NULL
);
