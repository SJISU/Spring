<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>
	Hello world!  
</h1>

<p> 홍길동의 스프링 홈</p>
<img src="img/kakao3.png"/>
<!-- 
	1/ WEB-INF/lib 폴더에 ojdbc8.jar복사
	
	2. pom.xml dependency추가
	
	ojdbc8 === 
		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc8</artifactId> 
			<version>18.0.0</version>
			<scope>system</scope>
			<systemPath>${basedir}/src/main/webapp/WEB-INF/lib/ojdbc8.jar</systemPath>
		</dependency>	
	https://mvnrepository.com/artifact/org.springframework/spring-jdbc
		<dependency>
    		<groupId>org.springframework</groupId>
    		<artifactId>spring-jdbc</artifactId>
    		<version>5.2.10.RELEASE</version>
		</dependency>		 
	
	3. servlet-context.xml에 db정보 입력	
	 오라클 드라이브, 서버위치, 계정, 비밀번호
		<beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource"> 
			<beans:property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"></beans:property>
			<beans:property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"></beans:property>
			<beans:property name="username" value="c##scott"></beans:property>
			<beans:property name="password" value="tiger"></beans:property>
		</beans:bean>    

 -->