package com.example.IPM.Coures.Project.admin;

import com.example.IPM.Coures.Project.general.BasePagingAndSortingService;
import com.example.IPM.Coures.Project.general.MyError;
import com.example.IPM.Coures.Project.user.UserDTO;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Service
public class AdminService extends BasePagingAndSortingService<AdminEntity,AdminDTO,Integer> {

    private final AdminRepository repository;
    private final AdminMapper mapper;

    public AdminService(AdminRepository repository, AdminMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public static String generateKey(){
        String key = "";
        Random random = new Random();
        char ch;
        for(int i =0; i < 13; i++){
            ch = (char) (random.nextInt(94) + 33);
            key += ch;
        }
        return key;
    }

    public AdminDTO check(AdminDTO dto) throws MyError {
        AdminEntity admin = repository.findByAccessKey(dto.getAccessKey());
        if(admin == null)
            throw new MyError("NO-NO-NO-NO you are not admin");
        return mapper.fromEntityToDTO(admin);
    }

    public boolean create(AdminDTO dto) throws MyError, NoSuchAlgorithmException {
        if(repository.findByLogin(dto.getLogin()) != null) {
            throw new MyError("уже существует");
        }
        byte[] salt = generateSalt();
        AdminEntity admin = mapper.fromDTOToEntity(dto);
        admin.setSold(salt);
        admin.setHashedPassword(hashPassword(admin.getPassword(), salt));
        admin.setAccessKey(generateKey());
        repository.save(admin);
        return true;
    }

    public AdminDTO authoriz(AdminDTO dto) throws MyError, NoSuchAlgorithmException {
        AdminEntity admin = repository.findByLogin(dto.getLogin());
        if (admin != null && verifyPassword(dto.getPassword(), admin.getHashedPassword(), admin.getSold())) {
            return mapper.fromEntityToDTO(admin);
        } else {
            throw new MyError("не получилось авторизироваться");
        }
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

    public boolean deleteByLogin(AdminDTO dto) throws MyError {
        try {
            repository.deleteByLogin(dto.getLogin());
            return true;
        } catch (Exception e) {
            throw new MyError("Не получилось удалить по этажу");
        }
    }

}
