package lk.ijse.green_shadow_pvt_ltdbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GreenShadowPvtLtdBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenShadowPvtLtdBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}