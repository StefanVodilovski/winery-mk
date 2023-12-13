package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.repository.AddWinesRepository;
import dians.homework3.wines02.service.AddWinesService;
import org.springframework.stereotype.Service;

@Service
public class AddWinesServiceImpl implements AddWinesService {
    AddWinesRepository addWinesRepository;

    public AddWinesServiceImpl(AddWinesRepository addWinesRepository) {
        this.addWinesRepository = addWinesRepository;
    }
}
