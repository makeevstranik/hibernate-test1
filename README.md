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
<hibernate-configuration xmlns="http://www.hibernate.org/xsd/orm/cfg">
    <session-factory>
        <property name="connection.url">jdbc:mysql://localhost:3306/hibernate-jvision</property>
        <property name="connection.driver_class">com.mysql.jdbc.Driver</property>

        <property name="connection.username">root</property>
        <property name="connection.password">5729757297</property>
        <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
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
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">

<hibernate-mapping xmlns="http://www.hibernate.org/xsd/hibernate-mapping">

    <class name="com.makeev.model.Engine" table="engines">
        <id name="model" column="model" />
        <property name="power" column="power"/>
    </class>
</hibernate-mapping>
```
