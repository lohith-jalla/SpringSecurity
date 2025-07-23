package com.lohith.Security.service;


import com.lohith.Security.model.Student;
import com.lohith.Security.model.UserPrincipal;
import com.lohith.Security.repo.StudentRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final StudentRepo studentRepo;

    public MyUserDetailsService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student=studentRepo.findByName(username);

        if(student==null){
            System.out.println("user not found"+username);
            throw new UsernameNotFoundException("user not found"+username   );
        }

        return new UserPrincipal(student);
    }
}
