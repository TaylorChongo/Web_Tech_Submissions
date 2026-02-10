package auca.ac.rw.restfullApiAssignment.controller.bonusQuestion;

import auca.ac.rw.restfullApiAssignment.model.ApiResponse;
import auca.ac.rw.restfullApiAssignment.model.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();

    public UserProfileController() {
        users.add(new UserProfile(1L, "john_doe", "john@example.com", "John Doe", 25, "USA", "Software engineer", true));
        users.add(new UserProfile(2L, "sarah_b", "sarah@example.com", "Sarah Brown", 30, "UK", "Designer", true));
        users.add(new UserProfile(3L, "kevin_smith", "kevin@example.com", "Kevin Smith", 22, "Canada", "Student", false));
        users.add(new UserProfile(4L, "ana_maria", "ana@example.com", "Ana Maria", 28, "Brazil", "Photographer", true));
        users.add(new UserProfile(5L, "lee_chan", "lee@example.com", "Lee Chan", 35, "China", "Entrepreneur", false));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAll() {
        return new ResponseEntity<>(new ApiResponse<>(true, "All users retrieved", users), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> getById(@PathVariable Long id) {
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                return new ResponseEntity<>(new ApiResponse<>(true, "User found", u), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "User not found", null), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/username")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByUsername(@RequestParam String username) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile u : users) {
            if (u.getUsername().toLowerCase().contains(username.toLowerCase())) {
                result.add(u);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(true, "Search results", result), HttpStatus.OK);
    }

    @GetMapping("/search/country")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@RequestParam String country) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile u : users) {
            if (u.getCountry().equalsIgnoreCase(country)) {
                result.add(u);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(true, "Search results", result), HttpStatus.OK);
    }

    @GetMapping("/search/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAgeRange(@RequestParam int min, @RequestParam int max) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile u : users) {
            if (u.getAge() >= min && u.getAge() <= max) {
                result.add(u);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(true, "Search results", result), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> create(@RequestBody UserProfile user) {
        users.add(user);
        return new ResponseEntity<>(new ApiResponse<>(true, "User profile created successfully", user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> update(@PathVariable Long id, @RequestBody UserProfile data) {
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                u.setUsername(data.getUsername());
                u.setEmail(data.getEmail());
                u.setFullName(data.getFullName());
                u.setAge(data.getAge());
                u.setCountry(data.getCountry());
                u.setBio(data.getBio());
                u.setActive(data.isActive());
                return new ResponseEntity<>(new ApiResponse<>(true, "User updated", u), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "User not found", null), HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activate(@PathVariable Long id) {
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                u.setActive(true);
                return new ResponseEntity<>(new ApiResponse<>(true, "User activated", u), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "User not found", null), HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivate(@PathVariable Long id) {
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                u.setActive(false);
                return new ResponseEntity<>(new ApiResponse<>(true, "User deactivated", u), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "User not found", null), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        UserProfile target = null;
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                target = u;
                break;
            }
        }
        if (target != null) {
            users.remove(target);
            return new ResponseEntity<>(new ApiResponse<>(true, "User deleted", null), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "User not found", null), HttpStatus.NOT_FOUND);
    }
}
