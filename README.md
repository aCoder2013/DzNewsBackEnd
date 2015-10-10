# XX-NewsSystem
一个采用SpringBoot,SpringDataJPA,Bootstrap编写的Simple新闻客户端,
##Feature
1.  图片支持上传到新浪云
2.  新闻数据支持由爬虫抓取或人工添加。
3.  后端采用Vaadin框架，不用写一行前端代码！
4.  采用SpringBoot，省去了大量配置文件
5.  采用Spring DataJPA，只需定义接口即可

##访问
0.  项目部署在阿里云，
1.  前台：http://115.28.26.248:8080
2.  后台：http://115.28.26.248:8080/admin

## 构建
1.  命令行中输入： mvn package ,可以在pom.xml中修改为WAR文件
2.  java -jar project.jar
