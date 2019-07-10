## 设计初衷
这个项目主要是为了快速搭建SSM架构构造的模板，主要封装了SSM系统基本的配置文件，常用技术和一些工具类。
## 项目包括
1. SSM项目配置文件架构
2. 用户类的查询和插入流程
3. 公用类**ServerResponse**和返回代码**ResponseCode**的封装
4. 工具类的封装:
    ZIPUtil
5. 常用工具的使用demo:  
    定时任务Scheduled的使用  
    日志程序logback的使用  
    lombok的使用
## 使用步骤
1. git clone https://github.com/zhao22/spring-mybatis-template.git
2. 使用IDE导入项目
3. 修改数据库配置文件jdbc.properties
4. 导入src/resources/sql/test.sql
5. 启动文件，按页面指示操作