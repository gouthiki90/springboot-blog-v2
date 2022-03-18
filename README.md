# 블로그 V2 코드 연습

### 1. DB 및 사용자 생성

```sql
CREATE USER 'green'@'%' IDENTIFIED BY 'password';

CREATE DATABASE greendb;

GRANT ALL PRIVILEGES ON greendb.* TO 'green'@'%';
```

### 2. 프로젝트 세팅
- application.yml
- view 생성
