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
        <property name="connection.password">*****</property>
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
## Hibernate simple using
```java
// main session object might be sent to DAO
SessionFactory factory = new Configuration().configure().buildSessionFactory();
//then in DAO
final Session session = factory.openSession();
// then work with session
```
## Hibernate many-to-one Video 2 (git branch p2_manyToOne)
two tables here
```xml
<hibernate-mapping xmlns="http://www.hibernate.org/xsd/hibernate-mapping">

    <class name="com.makeev.model.Car" table="cars_p2">
        <id name="id" type="int" column="id"> <generator class ="identity"/></id>
        <property name="mark" column="mark" />
        <property name="model" column="model"/>
        <!-- join engine to car (many cars links to one engine)-->
        <!-- cascade - what to do with child(engine) when parent(car) has been changed -->
        <!-- save-update - if new car(add to cars table) has new engine - add new engine to engines_p2 table  -->
        <!-- default - do nothing, change only cars table-->
        <many-to-one name="engine" column="engine_id"
                     class="com.makeev.model.Engine"
                     cascade="save-update" />
    </class>
</hibernate-mapping>
```
pay attention on nested classes:
```java
    public Car read(@NotNull final Integer id) {
        try(final Session session = factory.openSession()) {
            final Car result = session.get(Car.class, id);
    // for nested objects 
            if (result != null) {
                Hibernate.initialize(result.getEngine());
            }
            return result;
        }
    }
```

## Hibernate one-to-many Video 3 (git branch p3_oneToMany)
two tables here
```xml
<hibernate-mapping xmlns="http://www.hibernate.org/xsd/hibernate-mapping">

   <class name="com.makeev.model.Engine" table="engines_p3">
      <id name="id" type="int" column="id"> <generator class ="identity"/></id>
      <property name="name" column="name" />
      <property name="power" column="power"/>
      <property name="carMark" column="car_mark"/>
      <!-- join cars to this engine-->
      <set name="cars" table="cars_p3" cascade="all" fetch="join">
         <!-- column="mark" is from table cars_p3, property-ref="carMark" - is from Engine class -->
         <key column="mark" property-ref="carMark"/>
         <one-to-many class="com.makeev.model.Car"/>
      </set>
   </class>

</hibernate-mapping>
```
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Engine {
    private int id;
    private String name;
    private int power;
    private String carMark;
    private Set<Car> cars;

    public Engine(String name, int power, String carMark, Set<Car> cars) {
        this.name = name;
        this.power = power;
        this.carMark = carMark;
        this.cars = cars;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private int id;
    private String mark;
    private int cost;

    public Car(String mark, int cost) {
        this.mark = mark;
        this.cost = cost;
    }
}
```