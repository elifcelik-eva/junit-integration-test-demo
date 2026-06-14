package com.elifcelik.testlab.unitTestCases.midDemo;


public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User cannot found"));
    }

    public void deletedUser(Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found");
        }
        userRepository.deletedById(id);
    }
}
