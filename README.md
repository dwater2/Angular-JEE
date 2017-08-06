# Java EE Restful with JPA, AngularJS and Maven (Eclipse Java EE IDE for Web Developers)

### How to run

1. Create a database for your DataSource.
2. Create a new DataSource in your server (i've use WildFly)
3. Modify the **persistence.xml** file and change the DataSource name: `<jta-data-source>your datasource jndi name here</jta-data-source>`.
4. Before the first run uncomment, in the file persistence.xml, the lines for the creation of the tables and the insertion of the initial registers.
5. Compile and run.
6. http://<localhost>:<port>/desafio/index.html
