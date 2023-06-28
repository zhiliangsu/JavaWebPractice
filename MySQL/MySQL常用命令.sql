1.MySQL安装(8.X版本):
==> (数据库安装)
1.1.解压和配置环境变量(省略)
1.2.初始化MySQL
	mysqld --initialize-insecure
1.3.注册MySQL服务
	mysqld -install
1.4.启动和停止MySQL服务
	// 启动mysql服务
	net start mysql
	// 停止mysql服务
	net stop mysql
1.5.修改默认账户密码
	mysqladmin -u root password 1234
1.6.登录MySQL
	语法:
	mysql -u用户名 -p密码 -h要连接的mysql服务器的ip地址(默认127.0.0.1) -P端口号(默认3306)
	实例:
	mysql -uroot -proot -h127.0.0.1 -P3306
1.7.卸载MySQL
	net stop mysql
	mysqld -remove mysql
	最后删除MySQL目录及相关的环境变量


2.DDL: Data Definition Language
==> (数据库操作)
2.1.查询
	查询所有数据库语法:
	show databases;
	查询当前数据库语法:
	select database();
2.2.使用
	语法:
	use 数据库名;
	实例:
	use db01;
2.3.创建
	语法:
	create database [if not exists] 数据库名;
	实例:
	create database db01;
2.4.删除
	语法:
	drop database [if exists] 数据库名;
	实例:
	drop database db01;
2.5.注意事项: 
	上述命令中database可以替换成schema,例如show databases可以替换成show schemas;

==> (表结构操作)
2.6.创建表结构
	语法:
	create table  表名(
		字段1  字段1类型 [约束]  [comment  字段1注释 ],
		字段2  字段2类型 [约束]  [comment  字段2注释 ],
		......
		字段n  字段n类型 [约束]  [comment  字段n注释 ] 
	) [ comment  表注释 ] ;
	注意： [ ] 中的内容为可选参数； 最后一个字段后面没有逗号;	注释需要用单引号包裹
	实例:
	create table tb_emp
	(
		id          int primary key auto_increment comment '主键ID',
		username    varchar(20)                  not null comment '用户名',
		password    varchar(32) default '123456' null comment '密码',
		name        varchar(10)                  not null comment '姓名',
		gender      tinyint unsigned             not null comment '性别, 1 男, 2 女',
		image       varchar(300)                 null comment '图像url',
		job         tinyint unsigned             null comment '职位, 1 班主任 , 2 讲师 , 3 学工主管, 4 教研主管',
		entrydate   date                         null comment '入职日期',
		create_time datetime                     not null comment '创建时间',
		update_time datetime                     not null comment '修改时间',
		constraint tb_emp_username_uindex unique (username)
	) comment '员工表';
2.7.查询表结构
	查询当前数据库所有表语法:
	show tables;
	查询表结构语法:
	desc 表名;
	实例:
	desc tb_emp;
	查询建表语句语法:
	show create table 表名;
	实例:
	show create table tb_emp;
2.8.修改表结构
	添加字段:
	alter table 表名 add 字段名 类型(长度) [comment 注释] [约束];
	实例:
	alter table tb_emp add qq varchar(11) comment 'QQ';
	修改字段类型:
	alter table 表名 modify 字段名 新数据类型(长度);
	实例:
	alter table tb_emp modify qq varchar(13) comment 'QQ';
	修改字段名和字段类型:
	alter table 表名 change 旧字段名 新字段名 类型(长度) [comment 注释] [约束];
	实例:
	alter table tb_emp change qq qq_num varchar(13) comment 'QQ';
	删除字段:
	alter table 表名 drop column 字段名;
	实例:
	alter table tb_emp drop column qq_num;
	修改表名:
	rename table 表名 to 新表名;
	实例:
	rename table tb_emp to emp;
2.9.删除表结构
	语法:
	drop table [if exists] 表名;
	实例:
	drop table if exists tb_emp;


3.DML: Data Manipulation Language
==> (INSERT)
3.1.添加数据
	指定字段添加数据:
	insert into 表名 (字段名1, 字段名2) values (值1, 值2);
	实例:
	insert into tb_emp(username, name, gender, create_time, update_time) values ('wuji', '张无忌', 1, now(), now());
	全部字段添加数据:
	insert into 表名 values (值1, 值2, ...);
	实例:
	insert into tb_emp(id, username, password, name, gender, image, job, entrydate, create_time, update_time) values (null, 'zhiruo', '123', '周芷若', 2, '1.jpg', 1, '2010-01-01', now(), now());
	insert into tb_emp values (null, 'zhiruo2', '123', '周芷若', 2, '1.jpg', 1, '2010-01-01', now(), now());
	批量添加数据 (指定字段):
	insert into (字段名1, 字段名2) values (值1, 值2), (值1, 值2);
	实例:
	insert into tb_emp(username, name, gender, create_time, update_time) values ('weifuwang', '韦一笑', 1, now(), now()), ('xieshiwang', '谢逊', 1, now(), now());
	批量添加数据(全部字段):
	insert into 表名 values (值1, 值2, ...), (值1, 值2, ...);
	实例:
	insert into tb_emp values (null, 'zhiruo2', '123', '周芷若', 2, '1.jpg', 1, '2010-01-01', now(), now()), (null, 'zhiruo', '123', '周芷若', 2, '1.jpg', 1, '2010-01-01', now(), now())

==> (UPDATE)
3.2.修改数据
	语法:
	update 表名 set 字段名1 = 值1, 字段名2 = 值2, ... [where 条件];
	实例:
	update tb_emp set name = '张三', update_time = now() where id = 1;
	update tb_emp set entrydate = '2010-01-01', update_time = now();

==> (DELETE)
3.3.删除数据
	语法:
	delete from 表名 [where 条件];
	实例:
	delete from tb_emp where id = 1;
	delete from tb_emp;


4.DQL: Data Query Language
==> (语法结构)
4.1.DQL语法结构
	SELECT
		字段列表
	FROM
		表名列表
	WHERE
		条件列表
	GROUP  BY
		分组字段列表
	HAVING
		分组后条件列表
	ORDER BY
		排序字段列表
	LIMIT
		分页参数

==> (基本查询)
4.2.基本查询
	查询多个字段:
	select 字段1, 字段2, 字段3 from  表名;
	查询所有字段(通配符):
	select *  from  表名;
	设置别名:
	select 字段1 [ as 别名1 ] , 字段2 [ as 别名2 ]  from  表名;
	去除重复记录:
	select distinct 字段列表 from  表名;
	实例:
	--  =================== DQL: 基本查询 ======================
	-- 1. 查询指定字段 name,entrydate 并返回
	select name, entrydate from tb_emp;
	
	-- 2. 查询返回所有字段
	select id, username, password, name, gender, image, job, entrydate, create_time, update_time from tb_emp;
	select * from tb_emp;
	
	-- 3. 查询所有员工的 name,entrydate, 并起别名(姓名、入职日期)
	select name as '姓名', entrydate as '入职日期' from tb_emp;
	select name '姓名', entrydate '入职日期' from tb_emp;
	
	-- 4. 查询已有的员工关联了哪几种职位(不要重复)
	select distinct job from tb_emp;

==> (条件查询: where)
4.3.条件查询
	语法:
	select  字段列表  from   表名   where   条件列表 ; -- 条件列表：意味着可以有多个条件
	实例:
	--  =================== DQL: 条件查询 ======================
	-- 1. 查询 姓名 为 杨逍 的员工
	select * from tb_emp where name = '杨逍';
	
	-- 2. 查询 id小于等于5 的员工信息
	select * from tb_emp where id <=5;
	
	-- 3. 查询 没有分配职位 的员工信息
	select * from tb_emp where job is null;
	
	-- 4. 查询 有职位 的员工信息
	select * from tb_emp where job is not null;
	
	-- 5. 查询 密码不等于 '123456' 的员工信息
	select * from tb_emp where password != '123456';
	select * from tb_emp where password <> '123456';
	
	-- 6. 查询 入职日期 在 '2000-01-01' (包含) 到 '2010-01-01'(包含) 之间的员工信息
	select * from tb_emp where entrydate>='2000-01-01' and entrydate<='2010-01-01';
	select * from tb_emp where entrydate between '2000-01-01' and '2010-01-01';
	
	-- 7. 查询 入职时间 在 '2000-01-01' (包含) 到 '2010-01-01'(包含) 之间 且 性别为女 的员工信息
	select * from tb_emp where entrydate between '2000-01-01' and '2010-01-01' and gender=2;
	
	-- 8. 查询 职位是 2 (讲师), 3 (学工主管), 4 (教研主管) 的员工信息
	select * from tb_emp where job=2 or job=3 or job=4;
	select * from tb_emp where job in (2,3,4);
	
	-- 9. 查询 姓名 为两个字的员工信息
	select * from tb_emp where name like '__';
	
	-- 10. 查询 姓 '张' 的员工信息
	select * from tb_emp where name like '张%';

==> (分组查询: group by)
4.4.分组查询
	聚合函数:
	select  聚合函数(字段列表)  from  表名 ; -- count, max, min, avg, sum
	语法:
	select  字段列表  from  表名  [where 条件]  group by 分组字段名  [having 分组后过滤条件];
	注意事项:
​	- 分组之后，查询的字段一般为聚合函数和分组字段，查询其他字段无任何意义
​	- 执行顺序：where > 聚合函数 > having
	where与having区别（面试题):
	- 执行时机不同：where是分组之前进行过滤，不满足where条件，不参与分组；而having是分组之后对结果进行过滤。
	- 判断条件不同：where不能对聚合函数进行判断，而having可以。
	实例:
	--  =================== DQL: 分组查询 ======================
	-- 聚合函数
	-- 1. 统计该企业员工数量
	select count(name) from tb_emp;
	select count(job) from tb_emp;
	select count('A') from tb_emp;
	select count(*) from tb_emp;
	
	-- 2. 统计该企业员工 ID 的平均值
	select avg(id) from tb_emp;
	
	-- 3. 统计该企业最早入职的员工
	select min(entrydate) from tb_emp;
	
	-- 4. 统计该企业最迟入职的员工
	select max(entrydate) from tb_emp;
	
	-- 5. 统计该企业员工的 ID 之和
	select sum(id) from tb_emp;
	
	-- 分组
	-- 1. 根据性别分组 , 统计男性和女性员工的数量
	select gender,count(*) from tb_emp group by gender;
	
	-- 3. 先查询入职时间在 '2015-01-01' (包含) 以前的员工 , 并对结果根据职位分组 , 获取员工数量大于等于2的职位
	select job,count(*) from tb_emp where entrydate<='2015-01-01' group by job having count(*)>=2;

==> (排序查询: order by)
4.5.排序查询
	语法:
	select  字段列表  from   表名   [where  条件列表] [group by  分组字段] order  by  字段1  排序方式1 , 字段2  排序方式2 … ;
	排序方式：
	- ASC ：升序（默认值）
	- DESC：降序
	实例:
	--  =================== 排序查询 ======================
	-- 1. 根据入职时间, 对员工进行升序排序
	select * from tb_emp order by entrydate asc;
	select * from tb_emp order by entrydate;
	
	-- 2. 根据入职时间, 对员工进行降序排序
	select * from tb_emp order by entrydate desc ;
	
	-- 3. 根据 入职时间 对公司的员工进行 升序排序 ， 入职时间相同 , 再按照 更新时间 进行降序排序
	select * from tb_emp order by entrydate, update_time desc;

==> (分页查询: limit)
4.6.分页查询
	语法:
	select  字段列表  from   表名  limit  起始索引, 查询记录数 ;
	注意事项:
	- 起始索引从0开始。计算公式 ：起始索引 = （查询页码 - 1）* 每页显示记录数
	- 分页查询是数据库的方言，不同的数据库有不同的实现，MySQL中是LIMIT
	- 如果查询的是第一页数据，起始索引可以省略，直接简写为 limit  条数
	实例:
	--  =================== 分页查询 ======================
	-- 1. 从起始索引0开始查询员工数据, 每页展示5条记录
	select * from tb_emp limit 0,5;
	
	-- 2. 查询 第1页 员工数据, 每页展示5条记录
	select * from tb_emp limit 5;
	
	-- 3. 查询 第2页 员工数据, 每页展示5条记录
	select * from tb_emp limit 5,5;
	
	-- 4. 查询 第3页 员工数据, 每页展示5条记录
	select * from tb_emp limit 10,5;


5.DCL: Data Control Language(暂省略)


6.多表设计
6.1.一对多: 
	实现关系: 在数据库表中多的一方(子表)，添加字段，来关联属于一这方(父表)的主键
	实例: 部门表(一) - 员工表(多)
	关系: 一个部门可以包含多个员工, 一个员工只能属于一个部门

6.2.一对一: 
	实现关系: 在任意一方加入外键，关联另外一方的主键，并且设置外键为唯一的(UNIQUE)
	实例: 用户基本信息表(一) - 用户身份信息表(一)
	关系: 一个用户对应一个身份证号码

6.3.多对多:
	实现关系: 建立第三张中间表，中间表至少包含两个外键，分别关联两方主键
	实例: 学生表(多) - 课程表(多)
	关系：一个学生可以选修多门课程，一门课程也可以供多个学生选择

6.4.外键约束: 让两张表的数据建立连接，保证数据的一致性和完整性
	语法:
	-- 创建表时指定
	create table 表名(
		字段名    数据类型,
		...
		[constraint]   [外键名称]  foreign  key (外键字段名)   references   主表 (主表列名)	
	);
	-- 建完表后，添加外键
	alter table  表名  add constraint  外键名称  foreign key(外键字段名) references 主表(主表列名);
	--删除外键
	alter table 表名 drop foreign key 外键字段名;
	实例:
	-- 修改表： 添加外键约束
	alter table tb_emp  add  constraint  fk_dept_id  foreign key (dept_id)  references  tb_dept(id);
	-- 修改表： 删除外键约束
	alter table tb_emp drop foreign key tb_emp_dept_id;
	物理外键和逻辑外键:
	- 物理外键
	  - 概念：使用foreign key定义外键关联另外一张表。
	  - 缺点：
	    - 影响增、删、改的效率（需要检查外键关系）。
	    - 仅用于单节点数据库，不适用与分布式、集群场景。
	    - 容易引发数据库的死锁问题，消耗性能。
	- 逻辑外键
	  - 概念：在业务层逻辑中，解决外键关联。
	  - 通过逻辑外键，就可以很方便的解决上述问题。
	注意:在现在的企业开发中，很少会使用物理外键，都是使用逻辑外键。 甚至在一些数据库开发规范中，会明确指出禁止使用物理外键 foreign key

6.5.案例:
	一对多: 分类表 - 菜品表
		分类表 - 套餐表
	多对多: 套餐表 - 菜品表
	-- 1. 分类表category
	create table category(
	     id int unsigned primary key auto_increment comment '主键ID',
	     name varchar(20) not null unique comment '分类名称',
	     type tinyint unsigned not null comment '类型 1 菜品分类 2 套餐分类',
	     sort tinyint unsigned not null comment '顺序',
	     status tinyint unsigned not null default 0 comment '状态 0 禁用，1 启用',
	     create_time datetime not null comment '创建时间',
	     update_time datetime not null comment '更新时间'
	) comment '分类表' ;
	
	-- 2. 菜品表 dish
	create table dish(
	     id int unsigned primary key auto_increment comment '主键ID',
	     name varchar(20) not null unique comment '菜品名称',
	     category_id int unsigned not null comment '菜品分类ID',
	     price decimal(8, 2) not null comment '菜品价格',
	     image varchar(300) not null comment '菜品图片',
	     description varchar(200) comment '描述信息',
	     status tinyint unsigned not null default 0 comment '状态, 0 停售 1 起售',
	     create_time datetime not null comment '创建时间',
	     update_time datetime not null comment '更新时间'
	) comment '菜品表';
	
	
	-- 3. 套餐表 setmeal
	create table setmeal(
	    id int unsigned primary key auto_increment comment '主键ID',
	    name varchar(20) not null unique comment '套餐名称',
	    category_id int unsigned not null comment '分类id',
	    price decimal(8, 2) not null comment '套餐价格',
	    image varchar(300) not null comment '图片',
	    description varchar(200) comment '描述信息',
	    status tinyint unsigned not null default 0 comment '状态 0 停售 1 起售',
	    create_time datetime not null comment '创建时间',
	    update_time datetime not null comment '更新时间'
	)comment '套餐' ;
	
	
	-- 4. 套餐菜品关系表 setmeal_dish
	create table setmeal_dish(
	     id int unsigned primary key auto_increment comment '主键ID',
	     setmeal_id int unsigned not null comment '套餐id ',
	     dish_id int unsigned not null comment '菜品id',
	     copies tinyint unsigned not null comment '份数'
	)comment '套餐菜品关系';


7.多表查询：查询时从多张表中获取所需数据
	语法:
	单表查询的SQL语句：select  字段列表  from  表名;
	那么要执行多表查询，只需要使用逗号分隔多张表即可，如： select   字段列表  from  表1, 表2;
	实例:
	select * from  tb_emp , tb_dept;
	笛卡尔积：
	笛卡尔乘积是指在数学中，两个集合(A集合和B集合)的所有组合情况。
	在多表查询时，需要消除无效的笛卡尔积，只保留表关联部分的数据, 那么在SQL语句中，如何去除无效的笛卡尔积呢？只需要给多表查询加上连接查询的条件即可。
	分类:
		内连接：相当于查询A、B交集部分数据
		外连接:
			左外连接：查询左表所有数据(包括两张表交集部分数据)
			右外连接：查询右表所有数据(包括两张表交集部分数据)
		子查询: 

7.1.内连接查询: 查询两表或多表中交集部分数据
	隐式内连接语法：
	select  字段列表   from   表1 , 表2   where  条件 ... ;
	显式内连接语法：
	select  字段列表   from   表1  [ inner ]  join 表2  on  连接条件 ... ;
	案例:
	-- ============================= 内连接 ==========================
	-- A. 查询员工的姓名 , 及所属的部门名称 (隐式内连接实现)
	select tb_emp.name, tb_dept.name from tb_emp, tb_dept where tb_emp.dept_id=tb_dept.id;
	
	-- 起别名
	select e.name '姓名', d.name '所属部门' from tb_emp e, tb_dept d where e.dept_id=d.id;
	
	-- B. 查询员工的姓名 , 及所属的部门名称 (显式内连接实现)
	select tb_emp.name, tb_dept.name from tb_emp inner join tb_dept on tb_emp.dept_id=tb_dept.id;

7.2.外连接查询
	左外连接语法结构：左外连接相当于查询表1(左表)的所有数据，当然也包含表1和表2交集部分的数据
	select  字段列表   from   表1  left  [ outer ]  join 表2  on  连接条件 ... ;
	右外连接语法结构：右外连接相当于查询表2(右表)的所有数据，当然也包含表1和表2交集部分的数据
	select  字段列表   from   表1  right  [ outer ]  join 表2  on  连接条件 ... ;
	注意事项：
	左外连接和右外连接是可以相互替换的，只需要调整连接查询时SQL语句中表的先后顺序就可以了。而我们在日常开发使用时，更偏向于左外连接。
	案例:
	-- =============================== 外连接 ============================
	-- A. 查询员工表 所有 员工的姓名, 和对应的部门名称 (左外连接)
	select e.name, d.name from tb_emp e left join tb_dept d on e.dept_id = d.id;
	-- 转换成右外连接
	select e.name, d.name from tb_dept d right join tb_emp e on e.dept_id = d.id;
	
	-- B. 查询部门表 所有 部门的名称, 和对应的员工名称 (右外连接)
	select e.name,d.name from tb_emp e right join tb_dept d on e.dept_id=d.id;

7.3.子查询: SQL语句中嵌套select语句，称为嵌套查询，又称子查询
	语法:
	SELECT  *  FROM   t1   WHERE  column1 =  ( SELECT  column1  FROM  t2 ... );
	子查询外部的语句可以是insert / update / delete / select 的任何一个，最常见的是 select
	分类:
	- 标量子查询（子查询结果为单个值[一行一列]）
	- 列子查询（子查询结果为一列，但可以是多行）
	- 行子查询（子查询结果为一行，但可以是多列）	
	- 表子查询（子查询结果为多行多列[相当于子查询结果是一张表]）
	书写位置:
	- where之后
	- from之后
	- select之后

==> (标量子查询) 
	子查询返回的结果是单个值(数字、字符串、日期等)，最简单的形式，这种子查询称为标量子查询。
	常用的操作符： =   <>   >    >=    <   <=   
	案例:
	-- 标量子查询
	-- A. 查询 "教研部" 的所有员工信息
	-- a.查询"教研部"的部门ID
	select id from tb_dept where name='教研部';
	-- b.查询该部门ID对应的所有员工信息
	select * from tb_emp where dept_id=(select id from tb_dept where name='教研部');
	
	-- B. 查询在 "方东白" 入职之后的员工信息
	-- a.查询"方东白"的入职日期
	select entrydate from tb_emp where name='方东白';
	-- b.查询"方东白"入职后的所有员工信息
	select * from tb_emp where entrydate>(select entrydate from tb_emp where name='方东白');
==> (列子查询)
	子查询返回的结果是一列(可以是多行)，这种子查询称为列子查询。
	常用的操作符：IN  在指定的集合范围之内，多选一	NOT IN  不在指定的集合范围之内
	案例:
	-- 列子查询
	-- A. 查询 "教研部" 和 "咨询部" 的所有员工信息
	select id from tb_dept where name='教研部' or name = '咨询部';
	select * from tb_emp where dept_id in (select id from tb_dept where name='教研部' or name = '咨询部');
==> (行子查询)
	子查询返回的结果是一行(可以是多列)，这种子查询称为行子查询。
	常用的操作符：= 、<> 、IN 、NOT IN
	案例:
	-- 行子查询
	-- A. 查询与 "韦一笑" 的入职日期 及 职位都相同的员工信息 ;
	select entrydate, job from tb_emp where name = '韦一笑';
	select * from tb_emp where entrydate='2007-01-01' and job=2;
	select * from tb_emp where entrydate=(select entrydate from tb_emp where name = '韦一笑') and job=(select job from tb_emp where name = '韦一笑');
	select * from tb_emp where (entrydate,job)=('2007-01-01',2);
	select * from tb_emp where (entrydate,job)=(select entrydate, job from tb_emp where name = '韦一笑');

==> (表子查询)
	子查询返回的结果是多行多列，常作为临时表，这种子查询称为表子查询。
	常用的操作符：IN
	案例:
	-- 表子查询
	-- A. 查询入职日期是 "2006-01-01" 之后的员工信息 , 及其部门信息
	select * from tb_emp where entrydate>'2006-01-01';
	select e.*,d.name from (select * from tb_emp where entrydate>'2006-01-01') e, tb_dept d where e.dept_id=d.id;
	select e.*,d.name from (select * from tb_emp where entrydate>'2006-01-01') e left join tb_dept d on e.dept_id=d.id;

7.4.案例:
	-- 1. 查询价格低于 10元 的菜品的名称 、价格 及其 菜品的分类名称 .
	-- 表: category, dish
	-- SQL:
	select d.name, d.price, c.name
	from dish d,
	     category c
	where d.category_id = c.id
	  and d.price < 10;
	
	-- 2. 查询所有价格在 10元(含)到50元(含)之间 且 状态为'起售'的菜品名称、价格 及其 菜品的分类名称 (即使菜品没有分类 , 也需要将菜品查询出来).
	-- 表: category, dish
	-- SQL:
	select d.name, d.price, c.name
	from dish d
	         left join category c on d.category_id = c.id
	where d.price between 10 and 50
	  and c.status = 1;
	
	-- 3. 查询每个分类下最贵的菜品, 展示出分类的名称、最贵的菜品的价格 .
	-- 表: category, dish
	-- SQL:
	select c.name, max(d.price)
	from dish d,
	     category c
	where d.category_id = c.id
	group by c.name;
	
	-- 4. 查询各个分类下 状态为 '起售' , 并且 该分类下菜品总数量大于等于3 的 分类名称 .
	-- 表: category, dish
	-- SQL:
	select c.name, count(*)
	from dish d,
	     category c
	where d.category_id = c.id
	  and d.status = 1
	group by c.name
	having count(*) >= 3;
	
	-- 5. 查询出 "商务套餐A" 中包含了哪些菜品 （展示出套餐名称、价格, 包含的菜品名称、价格、份数）.
	-- 表: setmeal, setmeal_dish, dish
	-- SQL:
	select s.name, s.price, d.name, d.price, sd.copies
	from setmeal s,
	     setmeal_dish sd,
	     dish d
	where sd.setmeal_id = s.id
	  and sd.dish_id = d.id
	  and s.name = '商务套餐A';
	
	-- 6. 查询出低于菜品平均价格的菜品信息 (展示出菜品名称、菜品价格).
	-- 表: dish
	-- SQL:
	select avg(price) from dish;
	select name,price from dish where price<(37.736842);
	select name, price
	from dish
	where price < (select avg(price) from dish);

8.事务
	定义: 事务是一组操作的集合，它是一个不可分割的工作单位。事务会把所有的操作作为一个整体一起向系统提交或撤销操作请求，即这些操作要么同时成功，要么同时失败
	作用：保证在一个事务中多次操作数据库表中数据时，要么全都成功,要么全都失败。
	操作：
	- 自动提交事务：即执行一条sql语句提交一次事务。（默认MySQL的事务是自动提交）
	- 手动提交事务：先开启，再提交 
	SQL语句：
	start transaction;  /  begin ;  开启手动控制事务
	commit;                         提交事务        
	rollback;                       回滚事务        
	手动提交事务使用步骤：
	- 第1种情况：开启事务  =>  执行SQL语句   =>  成功  =>  提交事务
	- 第2种情况：开启事务  =>  执行SQL语句   =>  失败  =>  回滚事务
	四大特性: (ACID)
	- 原子性（Atomicity）：事务是不可分割的最小单元，要么全部成功，要么全部失败。
	- 一致性（Consistency）：事务完成时，必须使所有的数据都保持一致状态。
	- 隔离性（Isolation）：数据库系统提供的隔离机制，保证事务在不受外部并发操作影响的独立环境下运行。
	- 持久性（Durability）：事务一旦提交或回滚，它对数据库中的数据的改变就是永久的。
	详细解析:
	- 原子性（Atomicity）：原子性是指事务包装的一组sql是一个不可分割的工作单元，事务中的操作要么全部成功，要么全部失败。
	- 一致性（Consistency）：一个事务完成之后数据都必须处于一致性状态。
​				如果事务成功的完成，那么数据库的所有变化将生效。
​				如果事务执行出现错误，那么数据库的所有变化将会被回滚(撤销)，返回到原始状态。
	- 隔离性（Isolation）：多个用户并发的访问数据库时，一个用户的事务不能被其他用户的事务干扰，多个并发的事务之间要相互隔离。
​				一个事务的成功或者失败对于其他的事务是没有影响。
	- 持久性（Durability）：一个事务一旦被提交或回滚，它对数据库的改变将是永久性的，哪怕数据库发生异常，重启之后数据亦然存在。
	案例:
	-- 开启事务
	start transaction ;
	-- 删除学工部
	delete from tb_dept where id = 1;
	-- 删除学工部的员工
	delete from tb_emp where dept_id = 1;
	-- 提交事务 (成功时执行)
	commit ;
	-- 回滚事务 (出错时执行)
	rollback ;


9.索引
	定义: 是帮助数据库高效获取数据的数据结构, 使用索引可以提高查询的效率
	优点：
	- 提高数据查询的效率，降低数据库的IO成本。
	- 通过索引列对数据进行排序，降低数据排序的成本，降低CPU消耗。
	缺点：
	- 索引会占用存储空间。
	- 索引大大提高了查询效率，同时却也降低了insert、update、delete的效率。
	结构:
		Hash索引、B+Tree索引、Full-Text索引等, 我们平常所说的索引，如果没有特别指明，都是指默认的 B+Tree 结构组织的索引
	思考：采用二叉搜索树或者是红黑树来作为索引的结构有什么问题？
	答案: 
		最大的问题就是在数据量大的情况下，树的层级比较深，会影响检索速度。因为不管是二叉搜索数还是红黑数，一个节点下面只能有两个子节点。
		此时在数据量大的情况下，就会造成数的高度比较高，树的高度一旦高了，检索速度就会降低
	说明：
		如果数据结构是红黑树，那么查询1000万条数据，根据计算树的高度大概是23左右，这样确实比之前的方式快了很多，但是如果高并发访问，那么一个用户有可能需要23次磁盘IO，那么100万用户，那么会造成效率极其低下。
		所以为了减少红黑树的高度，那么就得增加树的宽度，就是不再像红黑树一样每个节点只能保存一个数据，可以引入另外一种数据结构，一个节点可以保存多个数据，这样宽度就会增加从而降低树的高度。这种数据结构例如BTree就满足。
	B+Tree(多路平衡搜索树)结构:
		- 每一个节点，可以存储多个key（有n个key，就有n个指针）
		- 节点分为：叶子节点、非叶子节点
		  - 叶子节点，就是最后一层子节点，所有的数据都存储在叶子节点上
		  - 非叶子节点，不是树结构最下面的节点，用于索引数据，存储的的是：key+指针
		- 为了提高范围查询效率，叶子节点形成了一个双向链表，便于数据的排序及区间范围查询
	拓展:
		非叶子节点都是由key+指针域组成的，一个key占8字节，一个指针占6字节，而一个节点总共容量是16KB，那么可以计算出一个节点可以存储的元素个数：16*1024字节 / (8+6)=1170个元素。
			- 查看mysql索引节点大小：show global status like 'innodb_page_size';    -- 节点大小：16384

		当根节点中可以存储1170个元素，那么根据每个元素的地址值又会找到下面的子节点，每个子节点也会存储1170个元素，那么第二层即第二次IO的时候就会找到数据大概是：1170*1170=135W。
		也就是说B+Tree数据结构中只需要经历两次磁盘IO就可以找到135W条数据。

		对于第二层每个元素有指针，那么会找到第三层，第三层由key+数据组成，假设key+数据总大小是1KB，而每个节点一共能存储16KB，所以一个第三层一个节点大概可以存储16个元素(即16条记录)。
		那么结合第二层每个元素通过指针域找到第三层的节点，第二层一共是135W个元素，那么第三层总元素大小就是：135W*16结果就是2000W+的元素个数。
		
		结合上述分析B+Tree有如下优点：
		- 千万条数据，B+Tree可以控制在小于等于3的高度
		- 所有的数据都存储在叶子节点上，并且底层已经实现了按照索引进行排序，还可以支持范围查询，叶子节点是一个双向链表，支持从小到大或者从大到小查找
	语法:
		创建索引: create  [ unique ]  index 索引名 on  表名 (字段名,... ) ;
		查看索引: show  index  from  表名;
		删除索引: show  index  from  表名;
	注意事项:
		- 主键字段，在建表时，会自动创建主键索引
		- 添加唯一约束时，数据库实际上会添加唯一索引
	案例:
	-- ================================ 索引 =============================
	select * from tb_sku where sn = '100000003145008'; -- 14s
	select count(*) from tb_sku;
	create index idx_sku_sn on tb_sku(sn);
	
	-- 创建 : 为tb_emp表的name字段建立一个索引 .
	create index idx_emp_name on tb_emp(name);
	
	-- 查询 : 查询 tb_emp 表的索引信息 .
	show index from tb_emp;
	
	-- 删除: 删除 tb_emp 表中name字段的索引 .
	drop index idx_emp_name on tb_emp;