package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import ma.emsi.patientsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(PatientsMvcApplication.class, args);

    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new
                    Patient(null,"Hassan",new Date(),false,12));
            patientRepository.save(new
                    Patient(null,"Omar",new Date(),false,321));
            patientRepository.save(new
                    Patient(null,"Yasmine",new Date(),true,65));
            patientRepository.save(new
                    Patient(null,"Hanae",new Date(),true,32));
            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });

        };
     }
     //@Bean
    CommandLineRunner saveUser(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("Mohamed","1234","1234");
            securityService.saveNewUser("Yasmine","1234","1234");
            securityService.saveNewUser("Hassan","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("Mohamed","USER");
            securityService.addRoleToUser("Mohamed","ADMIN");
            securityService.addRoleToUser("Yasmine","USER");
            securityService.addRoleToUser("Hassan","USER");


        };
     }

}
