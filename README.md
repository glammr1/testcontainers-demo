Testcontainers Demo
===================

Open `BasicDBTest.kt` (in `src/main/kotlin/tc`) and run the `BasicDBTest` class from IntelliJ.

How it works
------------

The test should pull the MySQL 5.7 container from Docker Hub if you do not already have it,
copy any init/config files to the container, start the container, and wait for the container to
be started.  The two tests will then run against the container, then the container will be torn
down and removed after the tests are complete.

* JUnit 5 sees the `@Testcontainers` annotation and uses the corresponding JUnit 5 extension
to initialize all `@Container` variables.
  * Containers assigned to static variables are initialized once per test class;
    containers assigned to member variables are initialized once per test.
* Each test can access properties of the associated `@Container` to access the container.

Gotchas
-------

The MySQL test container has a weird self-referential type that does not work well with
Kotiln, so the initializer code for the MySQL container is in `src/test/java/tc/MySQLTestUtils.java`.

Database initialization uses the `tmpfs` option for `/var/mysql/db` so that the filesystem
backing the database is in a ramdisk so all reads/writes should be fast.

