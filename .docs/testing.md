# Testing

[![codecov](https://codecov.io/gh/ahmsay/Solidvessel/graph/badge.svg?token=KVJ8AABE5Z)](https://codecov.io/gh/ahmsay/Solidvessel)

[![codecov](https://codecov.io/gh/ahmsay/Solidvessel/graphs/icicle.svg?token=KVJ8AABE5Z)](https://codecov.io/gh/ahmsay/Solidvessel)

There are 5 different types of tests included in this project. I also achieved %100 coverage
by writing tests for everything except configuration classes.

## Unit Tests

Unit tests are very easy to write, and they run very fast compared to other tests. They can be used to
test business logic in the domain part of the application.

You can also use unit tests if you don't need to test the actual behavior of a bean by mocking it.
To do this, you can mock the bean you want by using Mockito in your test class, and make your test class
extend a BaseUnitTest class. Inspect
<a href="../shared/domain/src/main/java/com/solidvessel/shared/test/BaseUnitTest.java">BaseUnitTest<a/>
and
<a href="../account/domain/src/test/java/com/solidvessel/account/address/service/RemoveAddressCommandServiceTest.java">
this example test<a/>
for more information.

## Database Tests

You may want to test the actual behavior of your persistence layer when working with databases. In that case
unit tests won't be enough. You can set up a database test configuration by using Testcontainers, which provides an
efficient way
to test databases.

1. Add Testcontainers dependency for your database in your build.gradle file. Since I am using postgres in this project,
   I added the
   testcontainers:postgresql dependency.
2. Create a
   <a href="../order/infra/src/test/java/com/solidvessel/order/integrationtest/BaseDatabaseTest.java">
   BaseDatabaseTest<a/>
   class to setup testcontainers and common configurations.
3. (Optional) Create a
   <a href="../shared/infra/src/main/java/com/solidvessel/shared/test/database/TestEntityHelper.java">helper class<a/>
   that can persist and find entities. This way you don't have to persist an entity with a class that you are trying to
   test.
   Make the BaseDatabaseTest extend the helper class.
4. You can start testing by extending BaseDatabaseTest in your
   <a href="../order/infra/src/test/java/com/solidvessel/order/adapter/out/order/db/OrderDBQueryAdapterTest.java">test
   class<a/>
   .

## Controller Tests

You can test the controller layer of your application by using MockMvc. The main goal is to test whether your controller
is
directing your request to the correct destination.

1. Create a
   <a href="../shared/infra/src/main/java/com/solidvessel/shared/test/controller/BaseControllerTest.java">
   BaseControllerTest<a/>
   class to setup common configurations. I also imported a TestSecurityFilterConfig in that class to do two things:
    - I didn't include the OAuth2 part and disabled csrf (unlike in the actual security filter) since I'm not testing
      them. Please note that it's okay to disable
      csrf for testing purposes, but it should <b>always</b> be enabled in production.
    - I added `@EnableWebSecurity` and `@EnableMethodSecurity` just like in the actual security filter, so that the
      authorization
      part can also be tested.
2. (Optional) Create
   <a href="../shared/infra/src/main/java/com/solidvessel/shared/test/controller/WithMockCustomer.java">user specific
   annotations<a/>
   to mimic users with specific roles. This is just for code reuse and makes your tests easier to read and write.
3. You can start testing by extending BaseControllerTest in your
   <a href="../inventory/infra/src/test/java/com/solidvessel/inventory/adapter/in/product/rest/ProductControllerTest.java">
   test class<a/>
   .

## Contract Tests

## Postman Tests
