package com.github.lumunix.fursona.service;


import com.github.lumunix.fursona.entity.ConfirmationToken;
import com.github.lumunix.fursona.entity.User;
import com.github.lumunix.fursona.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Autowired
    EmailSenderService emailSenderService;

    void sendConfirmationMail(String userMail, String token) {

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Mail Confirmation Link!");
        mailMessage.setFrom("<MAIL>");
        mailMessage.setText(
                "Thank you for registering. Please click on the below link to activate your account." + " http://localhost:8080/sign-up/confirm?token="
                        + token);

        emailSenderService.sendEmail(mailMessage);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email)));
    }

    public void signUpUser(User user) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        final ConfirmationToken confirmationToken = new ConfirmationToken(userRepository.save(user));
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
    }

    public void confirmUser(ConfirmationToken confirmationToken) {
        final User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
    }
}
