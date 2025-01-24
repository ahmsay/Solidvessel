package contracts.order

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "orders of customer"

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
                    "status": "DELIVERED",
                    "customerId": "123",
                    "paymentId": 5,
                    "address": "26593-birmingham,-uk"
                },
                {
                    "id": 2,
                    "status": "ON_THE_WAY",
                    "customerId": "123",
                    "paymentId": 6,
                    "address": "48249-helsinki,-finland"
                }
            ]
        """.replaceAll("\\s", "")
        )
    }
}
