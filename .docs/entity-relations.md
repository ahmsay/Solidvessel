# Entity Relationships

![image](https://github.com/ahmsay/Solidvessel/assets/22731894/cf306519-2231-43f2-aafe-f91a5a55bbdb)

The entity relationships are shown above.
- Obviously there is no actual db-to-db relation. The relationships shown as arrows are just informative.
- cart_product table only holds which product is in the cart with what quantity. payment_product on the other hand,
holds all the information about the product. Since products are bought at this phase, their information is stored in the payment database.
- customer is not an actual table in Keycloak DB. It refers to the user table that is managed by Keycloak. Every user signed up into the system
is treated as a customer.