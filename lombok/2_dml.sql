SELECT * FROM users

-- SELECT column_1, column_2,...
-- FROM table_name


-- 이름, 나이, 잔고, 전화번호 출력
SELECT first_name, last_name, age, balance, phone
FROM users;

--DISTINCT : 중복없이 조회할 때 사용
SELECT DISTINCT country
FROM users;

SELECT DISTINCT first_name
FROM users;

-- DISTINCT에 두 개의 컬럼을 작성하면 어떻게 될까요?
-- first_name, last_name 둘 다 똑같은 것을 기준으로 중복제거
SELECT DISTINCT first_name, last_name
FROM users;

-- SELECT ORDER BY : 정렬할 때 사용
SELECT first_name, age, balance
FROM  users
ORDER BY first_name;

SELECT first_name, age, balance
FROM  users
ORDER BY balance DESC;

--ORDER BY에 두 가지 이상의 칼럼을 지정하면?
-- balance를 기준으로 먼저 정렬한 뒤 age로 정렬한다.
SELECT first_name, last_name
FROM users
ORDER BY balance, age;

-- WHERE : 조건을 덧붙인 조회
SELECT *
FROM users
WHERE age >= 30;

SELECT *
FROM users
WHERE age < 30;

--40 이상 50 미만
SELECT *
FROM users
WHERE age >= 40 AND age < 50;

--30미만 60이상
SELECT *
FROM users
WHERE age < 30 OR age >= 60;

--WHERE 문자열 LIKE
-- email이 naver.com인 계정만 조회
SELECT id, first_name, email
FROM users
WHERE email Like '%@naver.com';
-- % 기호를 사용하면, 0개 이상의 문자와 일치한다고 가정한다.

SELECT id, first_name, phone
FROM users
WHERE phone LIKE '010%';

--NOT LIKE
SELECT id, first_name, phone
FROM users
WHERE phone NOT LIKE '010%';

