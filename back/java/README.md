# springboot-security-jwt   

```
curl -X POST -H "X-Requested-With: XMLHttpRequest" -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{  
    "username": "svlada@gmail.com",
    "password": "test1234"
}' "http://localhost:9966/api/auth/login"
```

```
curl -X GET -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsb3VyaXZhbC5hbG1laWRhQGdtYWlsLmNvbSIsInNjb3BlcyI6WyJVU1VBUklPIl0sImlkIjo1Njk4LCJ1c2VybmFtZSI6IkxvdXJpdmFsIEFsbWVpZGEiLCJpc3MiOiJodHRwOi8vY2FtcHVzYmFzZS5jb20uYnIiLCJpYXQiOjE1MzA1MDc4MjUsImV4cCI6MTUzMDUwODcyNX0.FaogYyHEB255gxHtr6WDx1ELfvaAwV8VVItPUDZzV4rSRWniQFb8Aob5ZzuzPnMbzVkOeqhu2gESaXp4vwtbRg" -H "Cache-Control: no-cache" "http://localhost:9966/api/me" 
```

Sequencia:

1. WebSecurityConfig
2. AjaxLoginProcessingFilter
3. AjaxAuthenticationProvider
