package com.elifcelik.testlab.unitTestCases.midDemo;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    boolean existsById(Long id);
    void deletedById(Long id);

}
