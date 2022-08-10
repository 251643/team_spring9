package com.sparta.hh99_w3.config;

import com.sparta.hh99_w3.models.Users;
import com.sparta.hh99_w3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    //userPk // 토큰에서의 username
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        Users user = userRepository.findById(Long.parseLong(userPk))
                .orElseThrow(() -> new UsernameNotFoundException(userPk + "은 존재하지 않는 아이디입니다."));
        return new UserDetailsImpl(user);//
    }
}
