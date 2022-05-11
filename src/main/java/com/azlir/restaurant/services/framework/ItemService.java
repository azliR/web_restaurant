package com.azlir.restaurant.services.framework;

import com.azlir.restaurant.entities.database.Item;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemService {
  List<Item> getItemsByStoreId(UUID storeId);

  Optional<Item> findById(UUID id);

  Item save(Item std, HttpServletRequest request);

  void deleteById(UUID id);
}
