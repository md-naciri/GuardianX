package com.guardianx._db.seeder.seeds;
import com.guardianx.domain.entity.Role;
import com.guardianx.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;

    private final Set<String> roles = Set.of(
            "SUPER_ADMIN",
            "ADMIN",
            "MANAGER",
            "USER"
    ); // add your roles here
    public void seed() {
        if(roleRepository.count() == 0){
            roles.forEach(
                role -> roleRepository.save(
                    Role.builder()
                        .name(role.toUpperCase())
                        .build()
                )
            );
        }
    }
}
