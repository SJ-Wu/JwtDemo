# JwtDemo

1. 實作 Jwt 參考範例：
   https://ithelp.ithome.com.tw/articles/10269270

2. 前端解析 jwt token:
   https://stackoverflow.com/questions/38552003/how-to-decode-jwt-token-in-javascript-without-using-a-library

3. 前端取得 token 後要再用 token 驗證取得服務時，前端透過 AJAX 發送 Request 時需要在 header 加上

    | key           | value          |
    | ------------- | -------------- |
    | Authorization | Bearer {token} |
