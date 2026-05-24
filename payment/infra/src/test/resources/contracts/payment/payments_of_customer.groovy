package contracts.payment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "payments of customer"

    request {
        url "/ofCustomer/123"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body("""
            [
                {
                    "id": 1,
                    "customerId": "123",
                    "products": [
                        {
                            "id": 3,
                            "name": "table",
                            "price": 35,
                            "category": "FURNITURE",
                            "quantity": 3
                        },
                        {
                            "id": 6,
                            "name": "sickle",
                            "price": 9,
                            "category": "TOOL",
                            "quantity": 5
                        }
                    ],
                    "totalPrice": 150,
                    "status": "APPROVED",
                    "createdDate": "2025-01-26T14:16:18.000000575"
                },
                {
                    "id": 2,
                    "customerId": "123",
                    "products": [
                        {
                            "id": 2,
                            "name": "macbook",
                            "price": 1200,
                            "category": "ELECTRONICS",
                            "quantity": 1
                        },
                        {
                            "id": 8,
                            "name": "shirt",
                            "price": 50,
                            "category": "CLOTHING",
                            "quantity": 2
                        }
                    ],
                    "totalPrice": 1300,
                    "status": "APPROVED",
                    "createdDate": "2024-08-03T09:56:42.000003815"
                }
            ]
        """.replaceAll("\\s", "")
        )
    }
}
