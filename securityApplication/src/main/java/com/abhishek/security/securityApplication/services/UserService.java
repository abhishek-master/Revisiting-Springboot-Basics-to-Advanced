package com.abhishek.security.securityApplication.services;

import com.abhishek.security.securityApplication.dto.LoginDTO;
import com.abhishek.security.securityApplication.dto.SignUpDTO;
import com.abhishek.security.securityApplication.dto.UserDTO;
import com.abhishek.security.securityApplication.entities.User;
import com.abhishek.security.securityApplication.exceptions.ResourceNotFoundException;
import com.abhishek.security.securityApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository ;
    private final ModelMapper modelMapper ;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new BadCredentialsException("User with Email " + username + " not found.")) ;
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {
        signUpDTO.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        User user = modelMapper.map(signUpDTO, User.class);
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with userID " + userId + " not found."));
    }
}
