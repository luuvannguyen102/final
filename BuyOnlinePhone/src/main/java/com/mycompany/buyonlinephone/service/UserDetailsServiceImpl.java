/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;



import com.mycompany.buyonlinephone.entities.AccountEntity;
import com.mycompany.buyonlinephone.entities.AccountRoleEntity;
import com.mycompany.buyonlinephone.enums.CommonStatus;
import com.mycompany.buyonlinephone.repository.UserRepository;
import com.mycompany.buyonlinephone.repository.UserRoleRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity user = userRepository.findByEmailLikeAndStatusLike(email, CommonStatus.ACTIVE);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        Set<AccountRoleEntity> roleNames = userRoleRepository.findByAccounts_Email(email);
        Set<GrantedAuthority> grantList = new HashSet<>();
        if (!CollectionUtils.isEmpty(roleNames)) {
            for (AccountRoleEntity role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole().toString());
                grantList.add(authority);
            }
        }

        return (UserDetails) new User(user.getEmail(), user.getPassword(), grantList);
    }

}
