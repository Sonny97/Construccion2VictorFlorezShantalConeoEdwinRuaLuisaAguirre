package app.application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.User;
import app.domain.model.emuns.Role;
import app.domain.services.CreateUsers;
import app.domain.services.DeleteUsers;
import app.domain.services.ListUsersService;
import java.util.List;


public class UserUseCase {
    
    @Autowired
    private CreateUsers createService;

    @Autowired
    private DeleteUsers deleteService;
    
    @Autowired
    private ListUsersService listService; 

    public UserUseCase(CreateUsers createService) {
        this.createService = createService;
    }
    
    public UserUseCase(DeleteUsers deleteService) {
        this.deleteService = deleteService;
    }

    public void createHRUser(User user) throws Exception {
        user.setRole(Role.HUMAN_RESOURCES);
        createService.create(user);
    }
    
    public void createAdministrativeUser(User user) throws Exception {
        user.setRole(Role.ADMINISTRATIVE);
        createService.create(user);
    }

    public void createNurseUser(User user) throws Exception {
        user.setRole(Role.NURSE);
        createService.create(user);
    }

    public void createDoctorUser(User user) throws Exception {
        user.setRole(Role.MEDIC);
        createService.create(user);
    }

    public void deleteUser(String username) throws Exception {
        User user = new User();
        user.setUserName(username);
        deleteService.delete(user);
    }
    public List<User> listAllUsers() throws Exception {
        return listService.listAllUsers();
    }
}