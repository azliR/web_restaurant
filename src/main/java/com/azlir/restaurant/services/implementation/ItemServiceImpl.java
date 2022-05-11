package com.azlir.restaurant.services.implementation;

import com.azlir.restaurant.entities.database.Item;
import com.azlir.restaurant.repositories.ItemRepository;
import com.azlir.restaurant.repositories.StoreAdminRepository;
import com.azlir.restaurant.services.framework.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {
  private final ItemRepository itemRepository;
  private final StoreAdminRepository storeAdminRepository;

  @Autowired
  public ItemServiceImpl(
      ItemRepository studentRepository, StoreAdminRepository storeAdminRepository) {
    this.itemRepository = studentRepository;
    this.storeAdminRepository = storeAdminRepository;
  }

  @Override
  public List<Item> getItemsByStoreId(UUID storeId) {
    return itemRepository.findByStoreId(storeId);
  }

  @Override
  public Optional<Item> findById(UUID id) {
    return itemRepository.findById(id);
  }

  @Override
  public Item save(Item item, HttpServletRequest request) {
    final var store =
        storeAdminRepository
            .findByEmail(request.getSession().getAttribute("username").toString())
            .orElseThrow()
            .getStore();
    item.setStore(store);
    return itemRepository.save(item);
  }

  @Override
  public void deleteById(UUID id) {
    itemRepository.deleteById(id);
  }
}
