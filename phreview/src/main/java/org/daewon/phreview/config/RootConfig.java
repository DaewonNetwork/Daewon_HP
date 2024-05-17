package org.daewon.phreview.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {

    @Bean
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
<<<<<<< HEAD
                .setMatchingStrategy(MatchingStrategies.LOOSE);    // STRICT면 테이블의 변수와 이름이 같아야함
=======
                .setMatchingStrategy(MatchingStrategies.STRICT);
>>>>>>> main
        return modelMapper;
    }
}
