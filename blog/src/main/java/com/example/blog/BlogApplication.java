package com.example.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Blog REST APIs",
                description = "SpringBoot 3.1.1 Blog",
                version = "v1.0",
                contact = @Contact(
                        name = "Feng",
                        email = "devhtp2k@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "none"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "GitHub",
                url = "https://github.com/HTFong/phong-workspace"

        )
)
public class BlogApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
//        System.out.println(new BCryptPasswordEncoder().encode("admin"));
//        System.out.println(new BCryptPasswordEncoder().encode("user"));
    }

}
