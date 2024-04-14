package TodolistProject.Services;

import TodolistProject.data.model.User;
import TodolistProject.data.repository.UserRepository;
import TodolistProject.dtos_requests.LogOutRequest;
import TodolistProject.dtos_requests.LoginRequest;
import TodolistProject.dtos_requests.RegisterRequest;
import TodolistProject.exceptions.*;
import TodolistProject.services.UserServicesImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServicesImplementationTest {
@Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }
    @Test
    void testThatUserCanCreateAccount(){
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("passworded");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        userServicesImplementation.registerUser(newUserRegistrationRequest);
        assertEquals(1, userRepository.count());

    }

    @Test
    void userWithEmptyUserNameCannotBeRegistered_numberOfUsersZero() {
        userRepository.deleteAll();
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("passworded");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        assertThrows(EmptyUserNameRegistrationException.class, ()->userServicesImplementation.registerUser(newUserRegistrationRequest));
        assertEquals(0, userRepository.count());
    }
    @Test
    void userWithEmptyLastNameCannotBeRegistered_numberOfUsersZero() {
        userRepository.deleteAll();
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("passworded");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        assertThrows(EmptyLastNameRegistrationException.class, ()->userServicesImplementation.registerUser(newUserRegistrationRequest));
        assertEquals(0, userRepository.count());
    }

    @Test
    void userWithEmptyFirstNameCannotBeRegistered_numberOfUsersZero() {
        userRepository.deleteAll();
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("passworded");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        assertThrows(EmptyFirstNameRegistrationException.class, ()->userServicesImplementation.registerUser(newUserRegistrationRequest));
        assertEquals(0, userRepository.count());
    }

    @Test
    void userWhiteSpaceCannotBeRegistered_numberOfUsersZero() {
        userRepository.deleteAll();
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName(" ");
        newUserRegistrationRequest.setLastName(" ");
        newUserRegistrationRequest.setUserName(" ");
        newUserRegistrationRequest.setEmail(" ");
        newUserRegistrationRequest.setPassword(" ");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        assertThrows(WhiteSpaceException.class, ()->userServicesImplementation.registerUser(newUserRegistrationRequest));
        assertEquals(0, userRepository.count());
    }



    @Test
    void twoUserCanBeRegistered_numberOfUsersTwo() {
        userRepository.deleteAll();

        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("passworded");

        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        userServicesImplementation.registerUser(newUserRegistrationRequest);

        RegisterRequest secondUserRegistrationRequest = new RegisterRequest();
        secondUserRegistrationRequest.setFirstName("McJohnny");
        secondUserRegistrationRequest.setLastName("Joey");
        secondUserRegistrationRequest.setUserName("twitter-man");
        secondUserRegistrationRequest.setEmail("twitter-man@gmail.com");
        secondUserRegistrationRequest.setPassword("not-passworded");

        userServicesImplementation.registerUser(secondUserRegistrationRequest);
        assertEquals(2, userRepository.count());
    }

    @Test
    void sameUserCannotBeRegisteredTwice() {
        userRepository.deleteAll();
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("passworded");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        userServicesImplementation.registerUser(newUserRegistrationRequest);
        assertThrows(DoubleUserRegistrationException.class, () -> userServicesImplementation.registerUser(newUserRegistrationRequest));
        assertEquals(1, userRepository.count());
    }

    @Test
    void testUserCannotLogin_createAccountToLogIn() {
        userRepository.deleteAll();
        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setUsername("google-man");
        newLoginRequest.setPassword("PASSWORD");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        Optional<User> findUser = userRepository.findByUserName("google-man");
        assertFalse(findUser.isPresent());
        assertThrows(NoSuchElementException.class, () -> userServicesImplementation.loginUser(newLoginRequest));
    }
    @Test
    void testUserCanBeLoggedIn(){
        userRepository.deleteAll();
        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setUsername("google-man");
        newLoginRequest.setPassword("PASSWORD");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        Optional<User> findUser = userRepository.findByUserName("google-man");
        assertFalse(findUser.isPresent());
        assertThrows(NoSuchElementException.class, ()->userServicesImplementation.loginUser(newLoginRequest));
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("PASSWORD");

        userServicesImplementation.registerUser(newUserRegistrationRequest);
        assertEquals(1, userRepository.count());
        userServicesImplementation.loginUser(newLoginRequest);
        assertTrue(userRepository.findByUserName("google-man").get().getIsLoggedIn());
    }
    @Test
    void testUserCannotBeLoggedInWithWrongUserName(){
        userRepository.deleteAll();
        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setUsername("google-man");
        newLoginRequest.setPassword("PASSWORD");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("PASSWORD");
        Optional<User> findUser = userRepository.findByUserName("geogle-man");
        assertFalse(findUser.isPresent());
        assertThrows(NoSuchElementException.class, ()->userServicesImplementation.loginUser(newLoginRequest));
    }
    @Test
    void testUserCannotBeLoggedInWithWrongPassword(){
        userRepository.deleteAll();
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("PASSWORD");
        userServicesImplementation.registerUser(newUserRegistrationRequest);

        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setUsername("google-man");
        newLoginRequest.setPassword("PASSWORDED");
        Optional<User> findUser = userRepository.findByUserName("google-man");
        assertTrue(findUser.isPresent());
        assertThrows(WrongPasswordException.class, ()->userServicesImplementation.loginUser(newLoginRequest));
    }

    @Test
    void testUserCannotBeLoggedInWithEmptyUserName(){
        userRepository.deleteAll();
        var userServicesImplementation = new UserServicesImplementation(userRepository);
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("PASSWORD");
        userServicesImplementation.registerUser(newUserRegistrationRequest);

        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setUsername("");
        newLoginRequest.setPassword("PASSWORD");
        Optional<User> findUser = userRepository.findByUserName("google-man");
        assertTrue(findUser.isPresent());
        assertThrows(EmptyUserNameLoginException.class, ()->userServicesImplementation.loginUser(newLoginRequest));
    }

    @Test
    void testUserCannotBeLoggedInWithWhiteSpaceUserName(){
        userRepository.deleteAll();
        var userServicesImplementation = new UserServicesImplementation(userRepository);
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("PASSWORD");
        userServicesImplementation.registerUser(newUserRegistrationRequest);

        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setUsername(" ");
        newLoginRequest.setPassword("PASSWORD");
        Optional<User> findUser = userRepository.findByUserName("google-man");
        assertTrue(findUser.isPresent());
        assertThrows(WhiteSpaceException.class, ()->userServicesImplementation.loginUser(newLoginRequest));
    }

    @Test
    void testUserCanBeLoggedInWithEmptyPassword(){
        userRepository.deleteAll();
        var userServicesImplementation = new UserServicesImplementation(userRepository);
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("PASSWORD");
        userServicesImplementation.registerUser(newUserRegistrationRequest);

        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setUsername("google-man");
        newLoginRequest.setPassword("");
        Optional<User> findUser = userRepository.findByUserName("google-man");
        assertTrue(findUser.isPresent());
        assertThrows(EmptyPasswordLoginException.class, ()->userServicesImplementation.loginUser(newLoginRequest));
    }

    @Test
    void testUserCannotBeLoggedInWithWhiteSpacePassword(){
        userRepository.deleteAll();
        var userServicesImplementation = new UserServicesImplementation(userRepository);
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("PASSWORD");
        userServicesImplementation.registerUser(newUserRegistrationRequest);

        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setUsername("google-man");
        newLoginRequest.setPassword(" ");
        Optional<User> findUser = userRepository.findByUserName("google-man");
        assertTrue(findUser.isPresent());
        assertThrows(WhiteSpaceException.class, ()->userServicesImplementation.loginUser(newLoginRequest));
    }

    @Test
    void testUserIsLoggedInAfterRegistration_UserCanLogOut(){
        userRepository.deleteAll();
        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setUsername("google-man");
        newLoginRequest.setPassword("PASSWORD");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        Optional<User> findUser = userRepository.findByUserName("google-man");
        assertFalse(findUser.isPresent());
        assertThrows(NoSuchElementException.class, ()->userServicesImplementation.loginUser(newLoginRequest));
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("PASSWORD");

        userServicesImplementation.registerUser(newUserRegistrationRequest);
        assertEquals(1, userRepository.count());
        userServicesImplementation.loginUser(newLoginRequest);
        assertTrue(userRepository.findByUserName("google-man").get().getIsLoggedIn());
        LogOutRequest newLogOutRequest = new LogOutRequest();
        newLogOutRequest.setUsername("google-man");
        newLogOutRequest.setPassword("PASSWORD");
        userServicesImplementation.logoutUser(newLogOutRequest);
        assertFalse(userRepository.findByUserName("google-man").get().getIsLoggedIn());
    }

    @Test
    void testUserCanCreateTask(){
        RegisterRequest newUserRegistrationRequest = new RegisterRequest();
        newUserRegistrationRequest.setFirstName("Johnny");
        newUserRegistrationRequest.setLastName("Joe");
        newUserRegistrationRequest.setUserName("google-man");
        newUserRegistrationRequest.setEmail("google-man@gmail.com");
        newUserRegistrationRequest.setPassword("passworded");
        UserServicesImplementation userServicesImplementation = new UserServicesImplementation(userRepository);
        userServicesImplementation.registerUser(newUserRegistrationRequest);
        assertEquals(1, userRepository.count());
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();


    }

    @Test
    void test_UserCannotCreateTaskWithoutLogin(){


    }

    @Test
    void testUserCanDeleteTask(){}

    @Test
    void test_UserCannotDeleteTaskWithoutLogin(){

    }

    @Test
    void testUserCanUpdateTask(){

    }

    @Test
    void test_UserCannotUpdateTaskWithoutLogin(){

    }

    @Test
    void testUserCanGetTask(){

    }

    @Test
    void test_UserCannotGetTaskWithoutLogin(){

    }

    @Test
    void testUserCanGetTaskList(){

    }

    @Test
    void test_UserCannotGetTaskListWithoutLogin(){

    }

    @Test
    void testUserCanGetTaskListByUserId(){

    }

    @Test
    void test_UserCannotGetTaskListByUserIdWithoutLogin(){

    }

    @Test
    void testUserCanGetTaskListByTaskId(){

    }

    @Test
    void test_UserCannotGetTaskListByTaskIdWithoutLogin(){

    }

    @Test
    void testUserCanGetTaskListByUserIdAndTaskId(){

    }

    @Test
    void test_UserCannotGetTaskListByUserIdAndTaskIdWithoutLogin(){

    }

    @Test
    void testUserCanGetTaskListByTaskIdAndUserId(){

    }



}

