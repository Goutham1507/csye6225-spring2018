package edu.neu.coe.csye6225.cloudnativeapp.service;


import edu.neu.coe.csye6225.cloudnativeapp.domain.UserAccount;
import edu.neu.coe.csye6225.cloudnativeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public void save(UserAccount userAccount) {


        userAccount.setPassword(hashPassword(userAccount.getPassword()));
        userRepository.save(userAccount);

    }


    private String hashPassword(String password) {

        String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
        return pw_hash;


    }
}
