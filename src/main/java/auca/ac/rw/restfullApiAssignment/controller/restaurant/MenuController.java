package auca.ac.rw.restfullApiAssignment.controller.restaurant;

import auca.ac.rw.restfullApiAssignment.model.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menu = new ArrayList<>();

    public MenuController() {
        menu.add(new MenuItem(1L, "Spring Rolls", "Crispy vegetable rolls", 5.99, "Appetizer", true));
        menu.add(new MenuItem(2L, "Caesar Salad", "Fresh lettuce with dressing", 7.49, "Appetizer", true));
        menu.add(new MenuItem(3L, "Grilled Chicken", "Served with rice", 12.99, "Main Course", true));
        menu.add(new MenuItem(4L, "Beef Steak", "Served with mashed potatoes", 18.99, "Main Course", false));
        menu.add(new MenuItem(5L, "Cheesecake", "Creamy cheesecake slice", 6.49, "Dessert", true));
        menu.add(new MenuItem(6L, "Chocolate Cake", "Rich chocolate flavor", 6.99, "Dessert", true));
        menu.add(new MenuItem(7L, "Iced Tea", "Cold refreshing tea", 2.99, "Beverage", true));
        menu.add(new MenuItem(8L, "Coffee", "Hot brewed coffee", 2.49, "Beverage", false));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllItems() {
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getItemById(@PathVariable Long id) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getByCategory(@PathVariable String category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailable(@RequestParam boolean available) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.isAvailable() == available) {
                result.add(item);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchByName(@RequestParam String name) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuItem> addItem(@RequestBody MenuItem item) {
        menu.add(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        MenuItem toRemove = null;
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                toRemove = item;
                break;
            }
        }
        if (toRemove != null) {
            menu.remove(toRemove);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

