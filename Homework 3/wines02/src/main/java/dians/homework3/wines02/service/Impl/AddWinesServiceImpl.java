package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.AddWinesDto;
import dians.homework3.wines02.mapper.AddWinesMapper;
import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.repository.AddWinesRepository;
import dians.homework3.wines02.service.AddWinesService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddWinesServiceImpl implements AddWinesService {
    private final AddWinesRepository addWinesRepository;

    public AddWinesServiceImpl(AddWinesRepository addWinesRepository) {
        this.addWinesRepository = addWinesRepository;
    }

    @Override
    public AddWinesDto findById(Long id) {
        Optional<AddWines> wines = addWinesRepository.findById(id);
        if(wines.isPresent()) {
            return AddWinesMapper.mapToAddWinesDto(wines.get());
        }
        return null;
    }
}
