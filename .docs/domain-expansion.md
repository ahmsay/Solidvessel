# Solidvessel Domain Expansion

Solidvessel currently has the foundations of an e-commerce system:

- Accounts and addresses
- Product inventory
- Cart
- Payments
- Orders
- Event-driven communication
- Microservice infrastructure

The domain is currently closer to a basic single-store checkout system. The following roadmap can evolve it into a
complete commerce platform.

## 1. Product Catalog

The current `Product` model is too simple for a real catalog.

Add:

- Product descriptions and images
- Brands and manufacturers
- Categories and subcategories
- Product variants such as size, color, storage, or weight
- SKU and barcode support
- Product attributes and specifications
- Product bundles and kits
- Related and substitute products
- Digital products
- Product lifecycle: draft, published, archived
- SEO metadata
- Multiple currencies and localized content

Potential bounded context: `catalog`.

## 2. Marketplace and Sellers

Multi-vendor support is one of the largest possible domain expansions.

Add:

- Seller accounts
- Seller storefronts
- Seller product listings
- Seller-specific pricing and inventory
- Seller commissions
- Seller payouts
- Seller ratings
- Seller verification
- Seller fulfillment options
- Marketplace dispute handling

Potential bounded context: `seller` or `marketplace`.

This introduces seller onboarding, commissions, settlements, disputes, and split orders.

## 3. Inventory and Warehousing

The current inventory model only tracks product quantity and regional availability.

Add:

- Warehouses
- Storage locations
- Stock reservations
- Stock movements
- Inventory adjustments
- Purchase orders
- Suppliers
- Restock thresholds
- Batch and lot tracking
- Serial numbers
- Expiration dates
- Damaged and quarantined stock
- Multi-warehouse allocation
- Inventory transfers
- Backorders and preorders

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

- Checkout sessions
- Price and inventory validation
- Shipping method selection
- Tax calculation
- Discounts and coupons
- Order splitting by seller or warehouse
- Partial fulfillment
- Partial cancellation
- Order history
- Invoice generation
- Guest checkout
- Idempotent order creation

Potential bounded context: `checkout` or `order`.

## 5. Payment and Financial Operations

The current payment model only has pending, approved, and cancelled states.

Add:

- Payment methods
- Saved payment methods through a tokenized provider
- Payment authorization and capture
- Partial payments
- Refunds
- Partial refunds
- Payment failures
- Chargebacks
- Fraud checks
- Payment reconciliation
- Gift cards
- Store credit
- Seller payouts
- Platform commissions
- Financial ledger

Payment status should not be treated as the same thing as order status. They should evolve independently and communicate
through events.

Potential bounded contexts: `payment`, `refund`, `ledger`, and `settlement`.

## 6. Shipping and Delivery

Shipping deserves its own domain rather than remaining inside orders.

Add:

- Shipping providers
- Shipping zones
- Shipping rates
- Delivery estimates
- Tracking numbers
- Shipment creation
- Package and parcel entities
- Multiple shipments per order
- Delivery attempts
- Pickup points
- Local delivery
- International shipping
- Customs information
- Proof of delivery
- Delivery exceptions

Potential bounded context: `shipping` or `fulfillment`.

## 7. Returns, Refunds, and Customer Service

This is essential for a realistic shopping application.

Add:

- Return requests
- Return reasons
- Return eligibility rules
- Return windows
- Return labels
- Inspection results
- Exchanges
- Refund calculation
- Store credit refunds
- Warranty claims
- Customer complaints
- Support tickets
- Dispute resolution

Potential bounded contexts: `returns` and `support`.

## 8. Customer Experience

Expand the account domain beyond addresses.

Add:

- Customer profiles
- Multiple delivery addresses
- Billing addresses
- Wishlists
- Saved searches
- Recently viewed products
- Product alerts
- Back-in-stock notifications
- Price-drop alerts
- Loyalty points
- Customer tiers
- Referral programs
- Gift registries
- Product reviews and ratings
- Product questions and answers
- Personalized recommendations

Potential bounded contexts: `customer`, `loyalty`, `reviews`, and `recommendation`.

## 9. Promotions and Pricing

A proper pricing domain can become quite sophisticated.

Add:

- Coupons
- Promotional campaigns
- Percentage discounts
- Fixed discounts
- Buy-one-get-one offers
- Quantity discounts
- Category discounts
- Seller-funded promotions
- Platform-funded promotions
- Flash sales
- Flash-sale inventory reservations
- Customer-specific pricing
- Membership pricing
- Price history
- Scheduled price changes

Potential bounded context: `promotion` or `pricing`.

## 10. Search and Discovery

A production e-commerce system should not rely only on database queries.

Add:

- Full-text search
- Faceted filtering
- Sorting
- Autocomplete
- Synonyms
- Typo tolerance
- Category navigation
- Search analytics
- Personalized ranking
- Recommendation engine
- Similar-product search

Potential services: `search` and `recommendation`, backed by Elasticsearch or OpenSearch.

## 11. Notifications

Create a notification domain for:

- Email notifications
- SMS notifications
- Push notifications
- Order updates
- Payment updates
- Shipment tracking
- Return updates
- Back-in-stock alerts
- Promotional campaigns
- Notification preferences
- Template management
- Retry and dead-letter handling

Potential bounded context: `notification`.

## 12. Administration and Governance

Add internal operational capabilities:

- Admin users
- Role-based permissions
- Seller moderation
- Product moderation
- Order intervention
- Refund approval
- Audit logs
- Feature flags
- Configuration management
- Fraud review queues
- Data export and deletion
- GDPR and privacy workflows

Potential bounded context: `admin` or `backoffice`.

## 13. Analytics and Reporting

Useful reports include:

- Sales by period
- Conversion rate
- Cart abandonment
- Product performance
- Seller performance
- Inventory turnover
- Refund rate
- Customer lifetime value
- Repeat purchase rate
- Promotion effectiveness
- Delivery performance
- Payment failure rate

Introduce read models and CQRS for reporting instead of querying transactional databases for every report.

## Recommended Expansion Order

1. Improve the product catalog with variants, images, attributes, and categories.
2. Add proper checkout, pricing, taxes, shipping, and order state transitions.
3. Add reservations and warehouse-aware inventory.
4. Add shipments, tracking, returns, and refunds.
5. Add reviews, wishlists, promotions, and notifications.
6. Add search and recommendations.
7. Add sellers, commissions, payouts, and marketplace orders.
8. Add analytics, fraud detection, loyalty, and subscriptions.

## Architectural Improvements

As the domain grows, introduce:

- Transactional outbox for reliable event publishing
- Idempotency keys for checkout and payment operations
- Saga workflows for checkout, cancellation, and refunds
- Explicit event versioning
- Separate read models for catalog, order history, and analytics
- Correlation IDs across asynchronous workflows
- Dead-letter queues and retry policies
- Audit trails for financial and administrative actions
- Strong money types instead of `Double`
- Immutable order and payment snapshots
- Explicit tax and currency models

The most valuable next step is to introduce `catalog`, `checkout`, `shipping`, and `returns` concepts before adding many
more microservices. Enrich the business domain first; otherwise the system risks becoming technically complex without
representing realistic commerce workflows.
