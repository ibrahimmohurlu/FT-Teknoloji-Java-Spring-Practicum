# Case-1

# Table Of Content

* ### [Description](#description)
* ### [API Endpoints](#api-endpoints)
* ### [Responses](#responses)
* ### [Tests](#tests)

---

## Description
In this case Entities and according Repositories in the table below created.

| Kullanıcı        | Urun                               | UrunYorum         |
|------------------|------------------------------------|-------------------|
| Id               | Id                                 | Id                |
| Adı(50 char)     | Adı                                | Yorum(500 char)   |
| Soyadı(50 char)  | Fiyat                              | yorumTarihi(date) |
| Email(50 char)   | Son Kullanma Tarihi(null olabilir) | urunId            |
| telefon(15 char) |                                    | kullaniciId       |

These repositories should contain methods that implement functionalities such as
- Write a method that lists comments on a specific product.
- Write a method that lists comments on a specific product between given dates.
- Write a method that lists all comments that commented by a specific user.
- Write a method that lists all comments that commented by a specific user between given dates.
- Write a method that lists expired products.
- Write a method that lists not expired products (Also lists products that expiration date is NULL).

<details>
<summary>Turkish version of list above</summary>

- Bir ürüne ait yorumları listeleyen bir metot yazınız.
- Verilen tarih aralıklarında belirli bir ürüne yapılmış olan yorumları gösteren bir metot yazınız.
- Bir kullanıcının yapmış olduğu yorumları listeleyen bir metot yazınız.
- Bir kullanıcının belirli tarihler aralığında yapmış olduğu yorumları gösteren bir metot yazınız.
- Son kullanma tarihi geçmiş ürünleri listeleyen bir metot yazınız.
- Son kullanma tarihi geçmemiş ürünleri listeleyen bir metot yazınız. (Son kullanma tarihi boş olanlar da gelmeli.)

</details>

To implement those functionalities an API implemented with Postgresql database and prefilled
with [data.sql](./src/main/resources/data.sql)

[Mockaroo](https://www.mockaroo.com/) used to create mock data.

---

## API Endpoints

| Endpoint                | Functionality                                                                                                                                       | Request Type |
|-------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|--------------|
| /products/{id}/comments | lists comments on a specific product. If start_date and end_date Request Parameters get value returns comments between those dates.                 | GET          |
| /products/expired       | lists expired products.                                                                                                                             | GET          |
| /products/not-expired   | lists not expired products                                                                                                                          | GET          |
| /users/{id}/comments    | lists all comments that commented by a specific user. If start_date and end_date Request Parameters get value returns comments between those dates. | GET          |

---

## Responses
<details>
<summary> /products/5/comments (Comments based on product ID)</summary>

``` yaml
 [
    {
        "id": 4,
        "comment": "mesh distributed web-readiness",
        "commentDate": "2022-01-17",
        "product": {
            "id": 5,
            "name": "Instant Coffee",
            "price": 52.06,
            "expDate": "2022-06-30"
        },
        "user": {
            "id": 4,
            "firstName": "Kristal",
            "lastName": "Kohrs",
            "email": "kkohrs3@tmall.com",
            "phoneNumber": "375-431-1496"
        }
    },
    {
        "id": 6,
        "comment": "optimize transparent web-readiness",
        "commentDate": "2021-10-18",
        "product": {
            "id": 5,
            "name": "Instant Coffee",
            "price": 52.06,
            "expDate": "2022-06-30"
        },
        "user": {
            "id": 1,
            "firstName": "Tailor",
            "lastName": "Feaveer",
            "email": "tfeaveer0@nifty.com",
            "phoneNumber": "770-404-4917"
        }
    },
    {
        "id": 13,
        "comment": "engage viral partnerships",
        "commentDate": "2022-03-26",
        "product": {
            "id": 5,
            "name": "Instant Coffee",
            "price": 52.06,
            "expDate": "2022-06-30"
        },
        "user": {
            "id": 3,
            "firstName": "Jaye",
            "lastName": "Anstiss",
            "email": "janstiss2@jimdo.com",
            "phoneNumber": "716-389-7511"
        }
    },
    {
        "id": 19,
        "comment": "redefine proactive synergies",
        "commentDate": "2021-09-28",
        "product": {
            "id": 5,
            "name": "Instant Coffee",
            "price": 52.06,
            "expDate": "2022-06-30"
        },
        "user": {
            "id": 4,
            "firstName": "Kristal",
            "lastName": "Kohrs",
            "email": "kkohrs3@tmall.com",
            "phoneNumber": "375-431-1496"
        }
    }
]
```
</details>

<details>
<summary> /products/5/comments?start_date=2021-01-01&end_date=2021-12-31 (Comments based on product ID between 2021-01-01 and 2021-12-31)</summary>

``` yaml
 [
    {
        "id": 6,
        "comment": "optimize transparent web-readiness",
        "commentDate": "2021-10-18",
        "product": {
            "id": 5,
            "name": "Instant Coffee",
            "price": 52.060001373291016,
            "expDate": "2022-06-30"
        },
        "user": {
            "id": 1,
            "firstName": "Tailor",
            "lastName": "Feaveer",
            "email": "tfeaveer0@nifty.com",
            "phoneNumber": "770-404-4917"
        }
    },
    {
        "id": 19,
        "comment": "redefine proactive synergies",
        "commentDate": "2021-09-28",
        "product": {
            "id": 5,
            "name": "Instant Coffee",
            "price": 52.060001373291016,
            "expDate": "2022-06-30"
        },
        "user": {
            "id": 4,
            "firstName": "Kristal",
            "lastName": "Kohrs",
            "email": "kkohrs3@tmall.com",
            "phoneNumber": "375-431-1496"
        }
    }
]
```
</details>

<details>
<summary> /products/expired (Expired products based on the date that request sent.)</summary>

``` yaml
 [
    {
        "id": 1,
        "name": "Coffee - Decafenated",
        "price": 87.25,
        "expDate": "2021-09-21"
    },
    {
        "id": 2,
        "name": "Chocolate - Feathers",
        "price": 72.0,
        "expDate": "2022-03-06"
    },
    {
        "id": 3,
        "name": "Tray - Foam, Square 4 - S",
        "price": 71.41,
        "expDate": "2021-09-08"
    },
    {
        "id": 4,
        "name": "Wine - Casillero Del Diablo",
        "price": 35.26,
        "expDate": "2021-10-31"
    },
    {
        "id": 5,
        "name": "Instant Coffee",
        "price": 52.06,
        "expDate": "2022-06-30"
    },
    {
        "id": 6,
        "name": "Cinnamon Rolls",
        "price": 1.94,
        "expDate": "2021-11-11"
    },
    {
        "id": 7,
        "name": "Muffin Orange Individual",
        "price": 14.71,
        "expDate": "2021-08-26"
    },
    {
        "id": 8,
        "name": "Pasta - Gnocchi, Potato",
        "price": 84.11,
        "expDate": "2022-01-29"
    },
    {
        "id": 9,
        "name": "Ham - Proscuitto",
        "price": 37.08,
        "expDate": "2022-05-12"
    },
    {
        "id": 10,
        "name": "Lobster - Baby, Boiled",
        "price": 91.08,
        "expDate": "2022-08-14"
    }
]
```
</details>

<details>
<summary> /products/not-expired (Not expired products based on the date that request sent.)</summary>

``` yaml
 [
    {
        "id": 11,
        "name": "Orange Juice",
        "price": 2.85,
        "expDate": "4022-08-14"
    }
]
```
</details>

<details>
<summary> /users/3/comments (Comments based on user ID)</summary>

``` yaml
 [
    {
        "id": 1,
        "comment": "iterate innovative networks",
        "commentDate": "2021-11-22",
        "product": {
            "id": 1,
            "name": "Coffee - Decafenated",
            "price": 87.25,
            "expDate": "2021-09-21"
        },
        "user": {
            "id": 3,
            "firstName": "Jaye",
            "lastName": "Anstiss",
            "email": "janstiss2@jimdo.com",
            "phoneNumber": "716-389-7511"
        }
    },
    {
        "id": 11,
        "comment": "strategize out-of-the-box methodologies",
        "commentDate": "2022-07-18",
        "product": {
            "id": 8,
            "name": "Pasta - Gnocchi, Potato",
            "price": 84.11,
            "expDate": "2022-01-29"
        },
        "user": {
            "id": 3,
            "firstName": "Jaye",
            "lastName": "Anstiss",
            "email": "janstiss2@jimdo.com",
            "phoneNumber": "716-389-7511"
        }
    },
    {
        "id": 13,
        "comment": "engage viral partnerships",
        "commentDate": "2022-03-26",
        "product": {
            "id": 5,
            "name": "Instant Coffee",
            "price": 52.06,
            "expDate": "2022-06-30"
        },
        "user": {
            "id": 3,
            "firstName": "Jaye",
            "lastName": "Anstiss",
            "email": "janstiss2@jimdo.com",
            "phoneNumber": "716-389-7511"
        }
    },
    {
        "id": 20,
        "comment": "revolutionize user-centric channels",
        "commentDate": "2022-04-23",
        "product": {
            "id": 10,
            "name": "Lobster - Baby, Boiled",
            "price": 91.08,
            "expDate": "2022-08-14"
        },
        "user": {
            "id": 3,
            "firstName": "Jaye",
            "lastName": "Anstiss",
            "email": "janstiss2@jimdo.com",
            "phoneNumber": "716-389-7511"
        }
    }
]
```
</details>

<details>
<summary> /users/3/comments?start_date=2021-01-01&end_date=2021-12-31 (Comments based on user ID between 2021-01-01 and 2021-12-31)</summary>

``` yaml
 [
    {
        "id": 1,
        "comment": "iterate innovative networks",
        "commentDate": "2021-11-22",
        "product": {
            "id": 1,
            "name": "Coffee - Decafenated",
            "price": 87.25,
            "expDate": "2021-09-21"
        },
        "user": {
            "id": 3,
            "firstName": "Jaye",
            "lastName": "Anstiss",
            "email": "janstiss2@jimdo.com",
            "phoneNumber": "716-389-7511"
        }
    }
]
```
</details>

---

## Tests
Repository and Services tested to verify functionality of the project.
H2 in-memory database used for testing.
Test results can be seen in the image below.
![Test Result](src/test/resources/test-result.PNG)
