package io.kang.rest_controller;

import io.kang.dto.mysql.CategoryDTO;
import io.kang.service.domain_service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ContextAPI/category/")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<CategoryDTO>> categoryList(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("find/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("create")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }

    @PutMapping("update")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.update(categoryDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok("카테고리가 삭제되었습니다. 초기화는 불가능합니다.");
    }
}