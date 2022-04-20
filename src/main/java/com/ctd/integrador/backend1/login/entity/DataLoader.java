package com.ctd.integrador.backend1.login.entity;

import com.ctd.integrador.backend1.login.service.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("admin");
        String password2 = encoder.encode("user");

        userRepository.save(new AppUser("Bob", "admin", "admin@mail.com", password, AppUserRoles.ROLE_ADMIN));
        userRepository.save(new AppUser("Peter", "user", "user@mail.com", password2, AppUserRoles.ROLE_USER));

    }

}
