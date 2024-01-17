package contracts.payment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "payment by id"

    request {
        url "/1"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body("""
            {
                "id": 1,
                "customerId": "123",
                "products": [
                    {
                        "id": 4,
                        "name": "slippers",
                        "price": 12,
                        "category": "CLOTHING",
                        "quantity": 2
                    },
                    {
                        "id": 5,
                        "name": "chair",
                        "price": 50,
                        "category": "FURNITURE",
                        "quantity": 3
                    }
                ],
                "totalCharge": 174,
                "status": "APPROVED"
            }
        """.replaceAll("\\s", "")
        )
    }
}
