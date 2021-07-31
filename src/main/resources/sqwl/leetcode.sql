177. 第N高的薪水
create function  getNthHighestSalary(N int) RETURNS INT
Begin
Set N = N -1;
Return (select ifnull(select distinct  Salary from employee  order by Salary desc limit(N,1) , null) as getNthHighestSalary)
End
// limit(N ,1) 表示取第N 中间取一个

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
      select distinct Salary from
      (select dense_rank() over(order by Salary desc) num,Salary from Employee) t
      where t.num=N
  );
END