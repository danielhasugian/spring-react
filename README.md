# spring-react

**Run spring boot**
<dl>
<dt>Build</dt>
<dd>mvn : clean install</dd>
<dt>Run</dt>
<dd>mvn : spring-boot:run </dd>
<dt>Prepare database Cassandra</dt>
<dd> use : cassandra version2.7 </dd>
<dd> cqlsh> CREATE KEYSPACE tes WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
<dd> cqlsh> CREATE TABLE owner (
  id int PRIMARY KEY,
  age int,
  name text,
);</dd>
</dl>
