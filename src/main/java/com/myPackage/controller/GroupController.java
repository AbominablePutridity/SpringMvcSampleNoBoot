/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPackage.controller;

import com.myPackage.entity.Group;
import com.myPackage.repository.GroupRepository;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author maxim
 */
@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    
    @GetMapping
    public List<Group> getAllProducts() {
        return groupRepository.findAll();
    }

    @GetMapping("/{id}")
    public Group getProductById(@PathVariable Long id) {
        return groupRepository.findById(id);
    }

    @PostMapping
    public Group createProduct(@RequestBody Group group) {
        return groupRepository.save(group);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        groupRepository.delete(groupRepository.findById(id));
    }
    
    @PutMapping("/{id}")
    public Group fullUpdateGroup(
        @PathVariable Long id,
        @RequestBody Group updatedGroup
    ) {
        // Устанавливаем ID из пути, чтобы убедиться, что обновляем нужную сущность
        updatedGroup.setId(id); 

        // saveOrUpdate: если ID существует - обновит, если нет - создаст новый
        return groupRepository.saveOrUpdate(updatedGroup);
    }
    
    @PatchMapping("/{id}")
    public Group partialUpdateGroup(
        @PathVariable Long id,
        @RequestBody Map<String, Object> updates
    ) {
        // 1. Достаём существующую группу
        Group existingGroup = groupRepository.findById(id);

        // 2. Применяем изменения из Map к объекту
        if (updates.containsKey("name")) {
            existingGroup.setGroupName((String) updates.get("groupName"));
        }
        if (updates.containsKey("description")) {
            existingGroup.setSpecialization((String) updates.get("specialization"));
        }
        // ... другие поля при необходимости

        // 3. Сохраняем частично обновлённый объект
        return groupRepository.update(existingGroup);
    }
}

