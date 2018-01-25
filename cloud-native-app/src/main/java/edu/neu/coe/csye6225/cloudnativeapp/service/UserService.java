package edu.neu.coe.csye6225.cloudnativeapp.service;


import edu.neu.coe.csye6225.cloudnativeapp.domain.UserAccount;
import edu.neu.coe.csye6225.cloudnativeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserAccount save(UserAccount userAccount) {

        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
        return userRepository.save(userAccount);

    }


    private String hashPassword(String password) {

        String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
        return pw_hash;


    }

    public UserAccount findByEmail(String email) {

        return userRepository.findByEmailAddress(email);
    }


    public UserAccount findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetailsImpl) {
            UserDetailsImpl userDetailsImpl = (UserDetailsImpl)userDetails;
            return userDetailsImpl.getUser();

        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserAccount userAccount = findByEmail(userName);
        if(userAccount == null){

            throw new UsernameNotFoundException(userName);
        }
        return new UserDetailsImpl(userAccount);
    }
}
