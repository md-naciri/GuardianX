package com.guardianx._db.seeder.seeds;


import com.guardianx.domain.entity.Permission;
import com.guardianx.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PermissionSeeder {
    private final PermissionRepository permissionRepository;

    private final Set<String> subjects = Set.of(
            "user",
            "role",
            "permission",
            "task"
    ); // add your subjects here
    private final Set<String> actions = Set.of(
            "create",
            "read",
            "update",
            "delete"
    ); // add your actions here

    public void seed() {
        if(permissionRepository.count() == 0){
            subjects.forEach(subject ->
                actions.forEach(action ->
                    permissionRepository.save(
                        Permission.builder()
                                .subject(subject)
                                .action(action)
                                .build()
                    )
                )
            );
        }
    }
}
