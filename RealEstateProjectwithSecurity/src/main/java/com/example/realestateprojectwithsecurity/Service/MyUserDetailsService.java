package com.example.realestateprojectwithsecurity.Service;



import com.example.realestateprojectwithsecurity.ApiResponse.ApiException;
import com.example.realestateprojectwithsecurity.Model.User;
import com.example.realestateprojectwithsecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user==null){
            throw new ApiException("Wrong username or password");
        }
        return user;

    }
}
