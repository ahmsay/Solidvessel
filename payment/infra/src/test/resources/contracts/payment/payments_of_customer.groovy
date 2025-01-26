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
                    "totalCharge": 150,
                    "status": "APPROVED",
                    "creationDate": [2025,1,26,14,16,18,575]
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
                    "totalCharge": 1300,
                    "status": "APPROVED",
                    "creationDate": [2024,8,3,9,56,42,3815]
                }
            ]
        """.replaceAll("\\s", "")
        )
    }
}
