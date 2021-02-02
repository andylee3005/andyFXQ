package lee.andyfxq.service;

import java.util.List;
import java.util.Optional;

import lee.andyfxq.model.User;

public interface UserService {
	List<User> getAll();
	Optional<User> getById(String id);
	User _save(User user);
	boolean checkEmail(String email);
	void removeById(String id);
}
