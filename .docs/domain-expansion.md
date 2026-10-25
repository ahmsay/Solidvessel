# Solidvessel Domain Expansion

Solidvessel currently has the foundations of an e-commerce system:

**0.1** Accounts and addresses  
**0.2** Product inventory  
**0.3** Cart  
**0.4** Payments  
**0.5** Orders  
**0.6** Event-driven communication  
**0.7** Microservice infrastructure

The domain is currently closer to a basic single-store checkout system. The following roadmap can evolve it into a
complete commerce platform.

## 1. Product Catalog

The current `Product` model is too simple for a real catalog.

Add:

**1.1** ~~Product descriptions~~  
**1.2** Product images  
**1.3** ~~Brands~~  
**1.4** Manufacturers  
**1.5** ~~Categories and subcategories~~  
**1.6** Product variants such as size, color, storage, or weight  
**1.7** SKU and barcode support  
**1.8** Product attributes and specifications  
**1.9** Product bundles and kits  
**1.10** Related and substitute products  
**1.11** Product lifecycle: draft, published, archived  
**1.12** SEO metadata  
**1.13** Multiple currencies and localized content

Potential bounded context: `catalog`.

## 2. Marketplace and Sellers

Multi-vendor support is one of the largest possible domain expansions.

Add:

**2.1** Seller accounts  
**2.2** Seller storefronts  
**2.3** Seller product listings  
**2.4** Seller-specific pricing and inventory  
**2.5** Seller commissions  
**2.6** Seller payouts  
**2.7** Seller ratings  
**2.8** Seller verification  
**2.9** Seller fulfillment options  
**2.10** Marketplace dispute handling

Potential bounded context: `seller` or `marketplace`.

This introduces seller onboarding, commissions, settlements, disputes, and split orders.

## 3. Inventory and Warehousing

The current inventory model only tracks product quantity and regional availability.

Add:

**3.1** Warehouses  
**3.2** Storage locations  
**3.3** Stock reservations  
**3.4** Stock movements  
**3.5** Inventory adjustments  
**3.6** Purchase orders  
**3.7** Suppliers  
**3.8** Restock thresholds  
**3.9** Batch and lot tracking  
**3.10** Serial numbers  
**3.11** Expiration dates  
**3.12** Damaged and quarantined stock  
**3.13** Multi-warehouse allocation  
**3.14** Inventory transfers  
**3.15** Backorders and preorders

Important distinction:

```text
available stock = physical stock - reserved stock - unavailable stock
```

Potential bounded contexts: `inventory`, `warehouse`, and `procurement`.

## 4. Checkout and Order Management

The current order lifecycle is limited to:

```text
PREPARING -> ON_THE_WAY -> DELIVERED
```

A more realistic workflow would include:

```text
CREATED
PAYMENT_PENDING
PAID
ALLOCATING
PICKING
PACKED
SHIPPED
OUT_FOR_DELIVERY
DELIVERED
CANCEL_REQUESTED
CANCELLED
RETURN_REQUESTED
RETURNED
REFUNDED
FAILED
```

Add:

**4.1** Checkout sessions  
**4.2** Price and inventory validation  
**4.3** Shipping method selection  
**4.4** Tax calculation  
**4.5** Discounts and coupons  
**4.6** Order splitting by seller or warehouse  
**4.7** Partial fulfillment  
**4.8** Partial cancellation  
**4.9** Order history  
**4.10** Invoice generation  
**4.11** Guest checkout  
**4.12** Idempotent order creation

Potential bounded context: `checkout` or `order`.

## 5. Payment and Financial Operations

The current payment model only has pending, approved, and cancelled states.

Add:

**5.1** Payment methods  
**5.2** Saved payment methods through a tokenized provider  
**5.3** Payment authorization and capture  
**5.4** Partial payments  
**5.5** Refunds  
**5.6** Partial refunds  
**5.7** Payment failures  
**5.8** Chargebacks  
**5.9** Fraud checks  
**5.10** Payment reconciliation  
**5.11** Gift cards  
**5.12** Store credit  
**5.13** Seller payouts  
**5.14** Platform commissions  
**5.15** Financial ledger

Payment status should not be treated as the same thing as order status. They should evolve independently and communicate
through events.

Potential bounded contexts: `payment`, `refund`, `ledger`, and `settlement`.

## 6. Shipping and Delivery

Shipping deserves its own domain rather than remaining inside orders.

Add:

**6.1** Shipping providers  
**6.2** Shipping zones  
**6.3** Shipping rates  
**6.4** Delivery estimates  
**6.5** Tracking numbers  
**6.6** Shipment creation  
**6.7** Package and parcel entities  
**6.8** Multiple shipments per order  
**6.9** Delivery attempts  
**6.10** Pickup points  
**6.11** Local delivery  
**6.12** International shipping  
**6.13** Customs information  
**6.14** Proof of delivery  
**6.15** Delivery exceptions

Potential bounded context: `shipping` or `fulfillment`.

## 7. Returns, Refunds, and Customer Service

This is essential for a realistic shopping application.

Add:

**7.1** Return requests  
**7.2** Return reasons  
**7.3** Return eligibility rules  
**7.4** Return windows  
**7.5** Return labels  
**7.6** Inspection results  
**7.7** Exchanges  
**7.8** Refund calculation  
**7.9** Store credit refunds  
**7.10** Warranty claims  
**7.11** Customer complaints  
**7.12** Support tickets  
**7.13** Dispute resolution

Potential bounded contexts: `returns` and `support`.

## 8. Customer Experience

Expand the account domain beyond addresses.

Add:

**8.1** Customer profiles  
**8.2** Multiple delivery addresses  
**8.3** Billing addresses  
**8.4** Wishlists  
**8.5** Saved searches  
**8.6** Recently viewed products  
**8.7** Product alerts  
**8.8** Back-in-stock notifications  
**8.9** Price-drop alerts  
**8.10** Loyalty points  
**8.11** Customer tiers  
**8.12** Referral programs  
**8.13** Gift registries  
**8.14** Product reviews and ratings  
**8.15** Product questions and answers  
**8.16** Personalized recommendations

Potential bounded contexts: `customer`, `loyalty`, `reviews`, and `recommendation`.

## 9. Promotions and Pricing

A proper pricing domain can become quite sophisticated.

Add:

**9.1** Coupons  
**9.2** Promotional campaigns  
**9.3** Percentage discounts  
**9.4** Fixed discounts  
**9.5** Buy-one-get-one offers  
**9.6** Quantity discounts  
**9.7** Category discounts  
**9.8** Seller-funded promotions  
**9.9** Platform-funded promotions  
**9.10** Flash sales  
**9.11** Flash-sale inventory reservations  
**9.12** Customer-specific pricing  
**9.13** Membership pricing  
**9.14** Price history  
**9.15** Scheduled price changes

Potential bounded context: `promotion` or `pricing`.

## 10. Search and Discovery

A production e-commerce system should not rely only on database queries.

Add:

**10.1** Full-text search  
**10.2** Faceted filtering  
**10.3** Sorting  
**10.4** Autocomplete  
**10.5** Synonyms  
**10.6** Typo tolerance  
**10.7** Category navigation  
**10.8** Search analytics  
**10.9** Personalized ranking  
**10.10** Recommendation engine  
**10.11** Similar-product search

Potential services: `search` and `recommendation`, backed by Elasticsearch or OpenSearch.

## 11. Notifications

Create a notification domain for:

**11.1** Email notifications  
**11.2** SMS notifications  
**11.3** Push notifications  
**11.4** Order updates  
**11.5** Payment updates  
**11.6** Shipment tracking  
**11.7** Return updates  
**11.8** Back-in-stock alerts  
**11.9** Promotional campaigns  
**11.10** Notification preferences  
**11.11** Template management  
**11.12** Retry and dead-letter handling

Potential bounded context: `notification`.

## 12. Administration and Governance

Add internal operational capabilities:

**12.1** Admin users  
**12.2** Role-based permissions  
**12.3** Seller moderation  
**12.4** Product moderation  
**12.5** Order intervention  
**12.6** Refund approval  
**12.7** Audit logs  
**12.8** Feature flags  
**12.9** Configuration management  
**12.10** Fraud review queues  
**12.11** Data export and deletion  
**12.12** GDPR and privacy workflows

Potential bounded context: `admin` or `backoffice`.

## 13. Analytics and Reporting

Useful reports include:

**13.1** Sales by period  
**13.2** Conversion rate  
**13.3** Cart abandonment  
**13.4** Product performance  
**13.5** Seller performance  
**13.6** Inventory turnover  
**13.7** Refund rate  
**13.8** Customer lifetime value  
**13.9** Repeat purchase rate  
**13.10** Promotion effectiveness  
**13.11** Delivery performance  
**13.12** Payment failure rate

Introduce read models and CQRS for reporting instead of querying transactional databases for every report.

## 14. Recommended Expansion Order

**14.1** Improve the product catalog with variants, images, attributes, and categories.  
**14.2** Add proper checkout, pricing, taxes, shipping, and order state transitions.  
**14.3** Add reservations and warehouse-aware inventory.  
**14.4** Add shipments, tracking, returns, and refunds.  
**14.5** Add reviews, wishlists, promotions, and notifications.  
**14.6** Add search and recommendations.  
**14.7** Add sellers, commissions, payouts, and marketplace orders.  
**14.8** Add analytics, fraud detection, loyalty, and subscriptions.

## 15. Architectural Improvements

As the domain grows, introduce:

**15.1** Transactional outbox for reliable event publishing  
**15.2** Idempotency keys for checkout and payment operations  
**15.3** Saga workflows for checkout, cancellation, and refunds  
**15.4** Explicit event versioning  
**15.5** Separate read models for catalog, order history, and analytics  
**15.6** Correlation IDs across asynchronous workflows  
**15.7** Dead-letter queues and retry policies  
**15.8** Audit trails for financial and administrative actions  
**15.9** Strong money types instead of `Double`  
**15.10** Immutable order and payment snapshots  
**15.11** Explicit tax and currency models

The most valuable next step is to introduce `catalog`, `checkout`, `shipping`, and `returns` concepts before adding many
more microservices. Enrich the business domain first; otherwise the system risks becoming technically complex without
representing realistic commerce workflows.
