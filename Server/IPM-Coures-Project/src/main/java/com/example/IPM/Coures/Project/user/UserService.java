package com.example.IPM.Coures.Project.user;

import com.example.IPM.Coures.Project.block.BlockDTO;
import com.example.IPM.Coures.Project.general.BasePagingAndSortingService;
import com.example.IPM.Coures.Project.general.MyError;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService extends BasePagingAndSortingService<UserEntity,UserDTO,Integer> {

    private final UserMapper mapper;
    private final UserRepository repository;


    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
        this.mapper = mapper;
        this.repository = repository;
    }


    public UserDTO authoriz(UserDTO dto) throws MyError, NoSuchAlgorithmException {
        UserEntity user = repository.findByLogin(dto.getLogin());
        if (user != null && verifyPassword(dto.getPassword(), user.getHashedPassword(), user.getSold())) {
            return mapper.fromEntityToDTO(user);
        } else {
            throw new MyError("не получилось авторизироваться");
        }
    }

    public boolean create(UserDTO dto) throws MyError, NoSuchAlgorithmException {
        if(repository.findByLogin(dto.getLogin()) != null) {
            throw new MyError("Уже сущесвтвует");
        }
        byte[] salt = generateSalt();
        UserEntity user = mapper.fromDTOToEntity(dto);
        user.setSold(salt);
        user.setHashedPassword(hashPassword(user.getPassword(), salt));
        repository.save(user);
        return true;
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        byte[] hashedBytes = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    public static boolean verifyPassword(String password, String hashedPassword, byte[] salt) throws NoSuchAlgorithmException {
        String newHashedPassword = hashPassword(password, salt);
        return newHashedPassword.equals(hashedPassword);
    }

    public boolean deleteByLogin(UserDTO dto) throws MyError {
        try {
            repository.deleteByLogin(dto.getLogin());
            return true;
        } catch (Exception e) {
            throw new MyError("Не получилось удалить по этажу");
        }
    }

}
