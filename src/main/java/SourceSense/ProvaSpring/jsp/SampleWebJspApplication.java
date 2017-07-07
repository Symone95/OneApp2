/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package SourceSense.ProvaSpring.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import db.DAO;
import db.Database;
import entities.Arma;
import entities.Armatura;
import entities.Eroe;
import entities.Mostro;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SampleWebJspApplication extends SpringBootServletInitializer {
	
	@Bean
	public DAO dao(){
		return new DAO(new Database());
	}
	
	@Bean
	@Scope("prototype")
	public Eroe eroe(){
		return new Eroe("Eroe", "Yeah", 100, 100, 100, 100, new Arma("Spada", 50), new Armatura("scudo", 90));
	}

	@Bean
	@Scope("prototype")
	public Mostro mostro(){
		return new Mostro("Mostro", "Yeah", 100, 100, 100, 100, new Arma("Lancia", 50), new Armatura("Elmo", 90));
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleWebJspApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleWebJspApplication.class, args);
	}

}
