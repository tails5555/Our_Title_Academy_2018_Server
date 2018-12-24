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
@RequestMapping("/ContextAPI")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("categories")
    public ResponseEntity<List<CategoryDTO>> categoryList(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        CategoryDTO categoryDTO = categoryService.findById(id);
        if(categoryDTO != null)
            return ResponseEntity.ok(categoryDTO);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("categories")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.update(categoryDTO));
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok("카테고리가 삭제되었습니다. 초기화는 불가능합니다.");
    }
}
