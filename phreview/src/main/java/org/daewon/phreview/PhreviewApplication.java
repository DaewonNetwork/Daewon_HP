package org.daewon.phreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListner 활성화, .class를 사용하기 위한 어노테이션
public class PhreviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhreviewApplication.class, args);
	}

}
