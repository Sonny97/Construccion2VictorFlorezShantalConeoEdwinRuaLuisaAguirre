package app.domain.application.useCase;

import app.domain.model.User;
import app.domain.model.emuns.Role;
import app.domain.services.CreateUsers;
import app.domain.services.DeleteUsers;


public class RhUseCase {

    private CreateUsers createService;
    private DeleteUsers deleteService;

    public RhUseCase(CreateUsers createService) {
        this.createService = createService;
    }
    
    public RhUseCase(DeleteUsers deleteService) {
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
}