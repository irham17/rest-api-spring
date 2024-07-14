# Patient API Spec

## Add Patient

### Endpoint
`POST /api/patient`

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "nama": "Irham",
  "tanggalLahir": "2002-04-17",
  "email": "irham@example.com",
  "nomorTelp": "08888888888",
  "diagnosa": "Flu",
  "resepObat": "Panadol",
  "saranPerawatan": "Minum obat 3x sehari"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id": "ID",
    "nama": "Irham",
    "tanggalLahir": "2002-04-17",
    "email": "irham@example.com",
    "nomorTelp": "08888888888",
    "diagnosa": "Flu",
    "resepObat": "Panadol",
    "saranPerawatan": "Minum obat 3x sehari"
  },
  "errors": "null"
}
```

Response Body (Failed) :

```json
{
  "data": "null",
  "errors" : "Email sudah terdaftar"
}
```

## Show Patient By ID

### Endpoint
`GET /api/patient/{patientId}`

### Request Example

```http
GET /api/patient/{ID}
```

Response Body (Success) :

```json
{
  "data": {
    "id": "ID",
    "nama": "Irham",
    "tanggalLahir": "2002-04-17",
    "email": "irham@example.com",
    "nomorTelp": "08888888888",
    "diagnosa": "Flu",
    "resepObat": "Panadol",
    "saranPerawatan": "Minum obat 3x sehari"
  },
  "errors": null
}
```

Response Body (Failed, 400) :

```json
{
  "data": "null",
  "errors" : "Pasien tidak ditemukan"
}
```