-- 员工管理(带约束)
create table tb_emp
(
    id          int unsigned primary key auto_increment comment 'ID',
    username    varchar(20)      not null unique comment '用户名',
    password    varchar(32) default '123456' comment '密码',
    name        varchar(10)      not null comment '姓名',
    gender      tinyint unsigned not null comment '性别, 说明: 1 男, 2 女',
    image       varchar(300) comment '图像',
    job         tinyint unsigned comment '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管',
    entrydate   date comment '入职时间',
    create_time datetime         not null comment '创建时间',
    update_time datetime         not null comment '修改时间'
) comment '员工表';

-- 准备测试数据
INSERT INTO tb_emp (id, username, password, name, gender, image, job, entrydate, create_time, update_time)
VALUES (1, 'jinyong', '123456', '金庸', 1, '1.jpg', 4, '2000-01-01', '2022-10-27 16:35:33', '2022-10-27 16:35:35'),
       (2, 'zhangwuji', '123456', '张无忌', 1, '2.jpg', 2, '2015-01-01', '2022-10-27 16:35:33', '2022-10-27 16:35:37'),
       (3, 'yangxiao', '123456', '杨逍', 1, '3.jpg', 2, '2008-05-01', '2022-10-27 16:35:33', '2022-10-27 16:35:39'),
       (4, 'weiyixiao', '123456', '韦一笑', 1, '4.jpg', 2, '2007-01-01', '2022-10-27 16:35:33', '2022-10-27 16:35:41'),
       (5, 'changyuchun', '123456', '常遇春', 1, '5.jpg', 2, '2012-12-05', '2022-10-27 16:35:33',
        '2022-10-27 16:35:43'),
       (6, 'xiaozhao', '123456', '小昭', 2, '6.jpg', 3, '2013-09-05', '2022-10-27 16:35:33', '2022-10-27 16:35:45'),
       (7, 'jixiaofu', '123456', '纪晓芙', 2, '7.jpg', 1, '2005-08-01', '2022-10-27 16:35:33', '2022-10-27 16:35:47'),
       (8, 'zhouzhiruo', '123456', '周芷若', 2, '8.jpg', 1, '2014-11-09', '2022-10-27 16:35:33', '2022-10-27 16:35:49'),
       (9, 'dingminjun', '123456', '丁敏君', 2, '9.jpg', 1, '2011-03-11', '2022-10-27 16:35:33', '2022-10-27 16:35:51'),
       (10, 'zhaomin', '123456', '赵敏', 2, '10.jpg', 1, '2013-09-05', '2022-10-27 16:35:33', '2022-10-27 16:35:53'),
       (11, 'luzhangke', '123456', '鹿杖客', 1, '11.jpg', 2, '2007-02-01', '2022-10-27 16:35:33',
        '2022-10-27 16:35:55'),
       (12, 'hebiweng', '123456', '鹤笔翁', 1, '12.jpg', 2, '2008-08-18', '2022-10-27 16:35:33', '2022-10-27 16:35:57'),
       (13, 'fangdongbai', '123456', '方东白', 1, '13.jpg', 1, '2012-11-01', '2022-10-27 16:35:33',
        '2022-10-27 16:35:59'),
       (14, 'zhangsanfeng', '123456', '张三丰', 1, '14.jpg', 2, '2002-08-01', '2022-10-27 16:35:33',
        '2022-10-27 16:36:01'),
       (15, 'yulianzhou', '123456', '俞莲舟', 1, '15.jpg', 2, '2011-05-01', '2022-10-27 16:35:33',
        '2022-10-27 16:36:03'),
       (16, 'songyuanqiao', '123456', '宋远桥', 1, '16.jpg', 2, '2010-01-01', '2022-10-27 16:35:33',
        '2022-10-27 16:36:05'),
       (17, 'chenyouliang', '12345678', '陈友谅', 1, '17.jpg', null, '2015-03-21', '2022-10-27 16:35:33',
        '2022-10-27 16:36:07'),
       (18, 'zhang1', '123456', '张一', 1, '2.jpg', 2, '2015-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:09'),
       (19, 'zhang2', '123456', '张二', 1, '2.jpg', 2, '2012-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:11'),
       (20, 'zhang3', '123456', '张三', 1, '2.jpg', 2, '2018-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:13'),
       (21, 'zhang4', '123456', '张四', 1, '2.jpg', 2, '2015-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:15'),
       (22, 'zhang5', '123456', '张五', 1, '2.jpg', 2, '2016-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:17'),
       (23, 'zhang6', '123456', '张六', 1, '2.jpg', 2, '2012-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:19'),
       (24, 'zhang7', '123456', '张七', 1, '2.jpg', 2, '2006-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:21'),
       (25, 'zhang8', '123456', '张八', 1, '2.jpg', 2, '2002-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:23'),
       (26, 'zhang9', '123456', '张九', 1, '2.jpg', 2, '2011-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:25'),
       (27, 'zhang10', '123456', '张十', 1, '2.jpg', 2, '2004-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:27'),
       (28, 'zhang11', '123456', '张十一', 1, '2.jpg', 2, '2007-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:29'),
       (29, 'zhang12', '123456', '张十二', 1, '2.jpg', 2, '2020-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:31');


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

--  =================== 排序查询 ======================
-- 1. 根据入职时间, 对员工进行升序排序
select * from tb_emp order by entrydate asc;
select * from tb_emp order by entrydate;

-- 2. 根据入职时间, 对员工进行降序排序
select * from tb_emp order by entrydate desc ;

-- 3. 根据 入职时间 对公司的员工进行 升序排序 ， 入职时间相同 , 再按照 更新时间 进行降序排序
select * from tb_emp order by entrydate, update_time desc;

--  =================== 分页查询 ======================
-- 1. 从起始索引0开始查询员工数据, 每页展示5条记录
select * from tb_emp limit 0,5;

-- 2. 查询 第1页 员工数据, 每页展示5条记录
select * from tb_emp limit 5;

-- 3. 查询 第2页 员工数据, 每页展示5条记录
select * from tb_emp limit 5,5;

-- 4. 查询 第3页 员工数据, 每页展示5条记录
select * from tb_emp limit 10,5;

--  =================== 案例练习 ======================
-- 练习1 : 员工管理列表查询 , 根据最后操作时间, 进行倒序排序
-- 根据输入条件查询第1页数据（每页展示10条记录）
-- 输入条件：
-- 姓名：张 （模糊查询）
-- 性别：男
-- 入职时间：2000-01-01 ~ 2015-12-31
-- 分页： 0 , 10
-- 排序： 修改时间  DESC
select *
from tb_emp
where name like '%张%'
  and gender = 1
  and entrydate between '2000-01-01' and '2015-12-31'
order by update_time desc
limit 0,10;

-- 练习2 :男性与女性员工的人数统计 (1 : 男性员工 , 2 : 女性员工)
-- 函数: if(条件表达式 , t , f)
select
    if(gender=1, '男性员工', '女性员工') '性别',
    count(*) '人数'
from tb_emp
group by gender;

-- 练习3 : 员工职位信息统计 -- count
-- 函数: case ... when ... then ... when ... then ... else ... end
-- 函数: case 表达式 when 值1 then 结果1 when 值2 then 结果2 ... else ... end
select (case job
            when 1 then '班主任'
            when 2 then '讲师'
            when 3 then '学工主管'
            when 4 then '教研主管'
            else '未分配职位' end
           )    as '职位',
       count(*) as '人数'
from tb_emp
group by job;