package com.watson.annababy.service.aa;

import com.watson.annababy.model.MenuEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository  extends CrudRepository<MenuEntity, Long> {

    List<MenuEntity> findByText(String text);
}
