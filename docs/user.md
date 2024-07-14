# User API Spec

## Register User

### Endpoint
`POST /api/users`

Request Body :

```json
{
  "username" : "irham",
  "password" : "password",
  "name" : "Irhamsyah" 
}
```

Response Body (Success, 200) :

```json
{
  "data" : "OK",
  "errors": "null"
}
```

Response Body (Bad Request, 400) :

```json
{
  "data": "null",
  "errors" : "Username sudah terdaftar"
}
```

## Login User

### Endpoint
`POST /api/auth/login`

Request Body :

```json
{
  "username" : "irham",
  "password" : "password" 
}
```

Response Body (Success, 200) :

```json
{
  "data" : {
    "token" : "X-API-TOKEN",
    "expiredAt" : 9999999999 //milliseconds
  },
  "errors": "null"
}
```

Response Body (Unauthorized, 401) :

```json
{
  "data": "null",
  "errors" : "Username atau password salah"
}
```

## Get User

### Endpoint
`GET /api/users/current`

Request Header :

- X-API-TOKEN : Token (Mandatory)

### Request Example

```http
GET /api/users/current
```

Response Body (Success) :

```json
{
  "data" : {
    "username" : "irham",
    "name" : "Irhamsyah"
  },
  "errors": "null"
}
```

Response Body (Failed, 401) :

```json
{
  "data": "null",
  "errors" : "Unauthorized"
}
```

## Logout User

### Endpoint
`DELETE /api/auth/logout`

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "OK",
  "errors": "null"
}
```