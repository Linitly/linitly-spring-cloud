package com.linitly.eureka.server.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * @author linxiunan
	 * @date 2019/8/13 15:42
	 * @return void
	 * @description 在实际开发中eureka需要进行登陆验证，在spring boot1.5.9的版本中直接引入依赖后就可以，
	 * 但在spring boot 2.0.x以后eureka添加验证后出现了服务无法注册的问题，在在网上发现是因为eureka本身的安全校验问题。
	 * 需要关闭csrf spring2.x版本的security默认启用了csrf检验，要在eurekaServer端配置security的csrf检验为false，代码如下
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		super.configure(http);
	}

}