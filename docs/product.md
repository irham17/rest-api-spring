# Product API Spec

## Search Product

### Endpoint
`GET /products`

### Query Parameters

| Parameter | Type   | Description                                 |
|-----------|--------|---------------------------------------------|
| page      | int    | The page number for pagination.             |
| limit     | int    | The number of items per page.               |
| kfa_code  | string | The KFA code to filter the products.        |

### Request Example

```http
GET /products?page=1&limit=10&kfa_code=92000372
```

Response Body (Success, 200) :

```json
{
  "total": 13,
  "page": 1,
  "limit": 1,
  "items": {
    "data": [
      {
        "kfa_code": "92000372",
        "product_template_name": "Acarbose 100 mg Tablet",
        "document_ref": "HK.01.07/MENKES/1905/2023",
        "active": false,
        "region_name": "Regional 2",
        "region_code": "regional2",
        "start_date": "2023-08-23",
        "end_date": "2024-02-29",
        "price_unit": 933,
        "uom_name": "Tablet",
        "updated_at": "2024-03-25 03:56:12",
        "uom_pack": [
          "Blister",
          "Strip"
        ],
        "province": [
          {
            "province_code": "12",
            "province_name": "Sumatera Utara"
          },
          {
            "province_code": "13",
            "province_name": "Sumatera Barat"
          },
          {
            "province_code": "14",
            "province_name": "Riau"
          },
          {
            "province_code": "15",
            "province_name": "Jambi"
          },
          {
            "province_code": "16",
            "province_name": "Sumatera Selatan"
          },
          {
            "province_code": "17",
            "province_name": "Bengkulu"
          },
          {
            "province_code": "19",
            "province_name": "Kepulauan Bangka Belitung"
          },
          {
            "province_code": "52",
            "province_name": "Nusa Tenggara Barat"
          }
        ]
      }
    ]
  }
}
```

Response Body (Bad Request, 400) :
````http
Isilah parameter {nama parameter} dengan benar.
````