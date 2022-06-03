# hibernate-test1
Learning Hibernate - by Java Vision tutorial
## Hibernate configuration
[java vision git source](https://github.com/PavelRavvich/hibernate-tutorial/tree/lesson/01.configuration)
- create db and table with columns equal to target class (Engine)
```sql
create table if not exists engines (
    model VARCHAR(25) PRIMARY KEY,
    power INTEGER NOT NULL );
```
- crate maven project with pom.xml:
```xml
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.29</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>6.0.2.Final</version>
            <type>pom</type>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.24</version>
            <scope>provided</scope>
        </dependency>

    </dependencies>
```
- create class that needs to be interacted with DB (here Engine with lombok) 
```java
package com.makeev.model;

import lombok.*;

/**
 * Author : Makeev Evgeny
 * Created 03/06/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Engine {
    private String model;
    private int power;
}

```
- create `resources/hibernate-jvision.iml` with help of idea `File --> ProjectStructure --> Modules --> Add Hibernate --> Add`
```xml
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
    "-//Hibernate/Hibernate Configuration DTD//EN"
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
  <session-factory>
    <!-- Database connection settings -->
    <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
    <property name="connection.url">jdbc:mysql://localhost:3306/hibernate-jvision</property>
    <property name="connection.username">root</property>
    <property name="connection.password">***</property>
    <!-- JDBC connection pool (use the built-in) -->
    <property name="connection.pool_size">1</property>
    <!-- SQL dialect -->
    <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
    <!-- Enable Hibernate's automatic session context management -->
    <property name="current_session_context_class">thread</property>
    <!-- Echo all executed SQL to stdout -->
    <property name="show_sql">true</property>
    <mapping resource="com.makeev.model/Engine.hbm.xml"/>
  </session-factory>
</hibernate-configuration>
```
- create mapping file for class entity (Engine) in `resources` in the same naming folder for example:
```
src/main:
java/com.makeev.model/Engine.java
resources/com.makeev.model/Engine.hbm.xml
```
```xml
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">

<hibernate-mapping package="com.makeev.model">

   <class name="Engine" table="engines">
      <property name="power" column="power"/>
      <id name="model" column="model" />
   </class>
</hibernate-mapping>
```
