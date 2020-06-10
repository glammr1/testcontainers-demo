CREATE TABLE foo (
  id BIGINT NOT NULL PRIMARY KEY,
  text VARCHAR(255) NOT NULL
);

INSERT INTO foo (id, text) VALUES (1, 'foo');
INSERT INTO foo (id, text) VALUES (2, 'bar');

CREATE TABLE bar (
  id INTEGER NOT NULL PRIMARY KEY,
  sample BIGINT NOT NULL,
  sample_date VARCHAR(255) NOT NULL
);

INSERT INTO bar (id, sample, sample_date) VALUES (64, 238, '2020-06-01');
INSERT INTO bar (id, sample, sample_date) VALUES (65, 250, '2020-06-02');
INSERT INTO bar (id, sample, sample_date) VALUES (66, 222, '2020-06-03');