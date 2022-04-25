package com.azlir.restaurant.services.implementation;

import com.azlir.restaurant.entities.database.Item;
import com.azlir.restaurant.repositories.ItemCategoryRepository;
import com.azlir.restaurant.repositories.ItemRepository;
import com.azlir.restaurant.repositories.StoreRepository;
import com.azlir.restaurant.services.framework.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {
  private final ItemRepository studentRepository;
  private final StoreRepository storeRepository;
  private final ItemCategoryRepository itemCategoryRepository;

  @Autowired
  public ItemServiceImpl(
      ItemRepository studentRepository,
      StoreRepository storeRepository,
      ItemCategoryRepository itemCategoryRepository) {
    this.studentRepository = studentRepository;
    this.storeRepository = storeRepository;
    this.itemCategoryRepository = itemCategoryRepository;
  }

  @Override
  public List<Item> getAllItems() {
    return studentRepository.findAll();
  }

  @Override
  public Optional<Item> findById(UUID id) {
    return studentRepository.findById(id);
  }

  @Override
  public Item save(Item item, HttpServletRequest request) {
    final var store =
        storeRepository
            .findById(UUID.fromString((String) request.getSession().getAttribute("STORE_ID")))
            .orElse(null);
    item.setStore(store);
    return studentRepository.save(item);
  }

  @Override
  public void deleteById(UUID id) {
    studentRepository.deleteById(id);
  }
}
