package ra.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ra.edu.dto.ResponseData;
import ra.edu.entity.Account;
import ra.edu.exception.NotFoundException;
import ra.edu.repository.IAccountRepository;

@Service
public class UserService {
    @Autowired
    private IAccountRepository accountRepository;
    public ResponseData getById(Long id) throws NotFoundException{
        return ResponseData.builder()
                .success(true)
                .message("Successfully")
                .data(accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account id "+id+" not exist")))
                .build();
    }
}
