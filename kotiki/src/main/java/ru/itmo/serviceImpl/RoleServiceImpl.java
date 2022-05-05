package ru.itmo.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.models.Role;
import ru.itmo.repository.OwnerRepository;
import ru.itmo.repository.RoleRepository;
import ru.itmo.service.RoleService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleById(int roleId) {
        return roleRepository.findById(roleId);
    }
}
