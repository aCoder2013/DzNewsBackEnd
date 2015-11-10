# XX-NewsSystem
一个采用SpringBoot,SpringDataJPA,Material Design Lite
编写的Simple新闻客户端,
##Framework or Third Party Component
1.  Spring Boot,SpringMVC,Spring DataJPA,spring-hateoas
2.  Vaadin
3.  Hibernate Validator
4.  EhCache
5.  Druid  
6.  Material Design Lite
7.  jsoup

##API
1.  /api/news ：获取单页新闻,支持size,page,sort
2.  /api/news/{id} ：获取指定ID的新闻项
3.  /api/news/detail/{id} :获取指定ID的新闻详情

##访问
0.  项目部署在阿里云，
1.  前台：http://115.28.26.248:80
2.  后台：http://115.28.26.248:8080/admin

## 构建
1.  命令行中输入： mvn package ,可以在pom.xml中修改为WAR文件
2.  java -jar project.jar
