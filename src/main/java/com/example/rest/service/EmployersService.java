package com.example.rest.service;

import com.example.rest.exception.EmployersNotFoundException;
import com.example.rest.model.EmployersModel;
import com.example.rest.repository.EmployersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmployersService implements BaseServiceInterface<EmployersModel> {

    private final EmployersRepository employersRepository;

    @Override
    public EmployersModel add(EmployersModel employersModel) {
        return employersRepository.saveAndFlush(employersModel);
    }

    @Override
    public EmployersModel replace(Long id, EmployersModel entity) throws EmployersNotFoundException {

        EmployersModel model = findById(id);

        updateFieldIfNotNull(model::setAge, entity.getAge());
        updateFieldIfNotNull(model::setFirstName, entity.getFirstName());
        updateFieldIfNotNull(model::setLastName, entity.getLastName());
        updateFieldIfNotNull(model::setSecondName, entity.getSecondName());
        updateFieldIfNotNull(model::setPosition, entity.getPosition());
        updateFieldIfNotNull(model::setSalary, entity.getSalary());

        return employersRepository.save(model);
    }

    private <T> void updateFieldIfNotNull(Consumer<T> setter, T value) {
        if(value != null) {
            if (value instanceof Integer) {
                setter.accept(value);
            }
            else if (value instanceof String) {
                if (!value.equals("")) {
                    setter.accept(value);
                }
            }
        }
    }

    @Override
    public void delete(Long id) throws EmployersNotFoundException {
        employersRepository.delete(findById(id));
    }

    @Override
    public EmployersModel findById(Long id) throws EmployersNotFoundException {

        return employersRepository.findById(id)
                .orElseThrow(() -> new EmployersNotFoundException(id));
    }

    @Override
    public List<EmployersModel> getAll() throws EmployersNotFoundException {

        List<EmployersModel> modelList = employersRepository.findAll();

        if (modelList.isEmpty()) {
            throw new EmployersNotFoundException();
        }

        return modelList;
    }
}
