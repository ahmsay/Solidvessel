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
                    "address": "26593-birmingham,-uk",
                    "createdDate": "2025-03-13T22:45:03.000004831",
                    "cancellation": null,
                    "recipient": "Judge-Holden"
                },
                {
                    "id": 2,
                    "status": "ON_THE_WAY",
                    "customerId": "123",
                    "paymentId": 6,
                    "address": "48249-helsinki,-finland",
                    "createdDate": "2023-12-09T11:49:32.000008371",
                    "cancellation": null,
                    "recipient": null
                }
            ]
        """.replaceAll("\\s", "")
        )
    }
}
