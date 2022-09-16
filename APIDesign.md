
### 구현 API 명세

회원 가입  (POST, /users/signup)

(1) 임의 값으로 가입 후  (2)
```
request
{
    "email": "user123@gmail.com"
    "
}

```

```
response


{
    "message": 회원 가입이 완료되었습니다. 
}
```

게임 진행 (POST /game/123/answer)

```
UserAnswerRequest

request
{
    "answer": "345"
}


```

response(게임 종료가 아닐 시)

```
UserAnswerResponse

{
    "success": true,
    "data->gameData": { 
        "correct": true, // false
        "remainingCount": 8,
        "strike": 3,
        "ball": 0,
        "out": 0
    }
	"error":null; 
}
```

response(게임 종료 시)

```

{
    "success": false,
    "data": null,
    "error": {
        "code": "CLOSED_GAME",
        "message": ""
    }
}

```