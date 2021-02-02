package lee.andyfxq.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.andyfxq.model.User;
import lee.andyfxq.repository.UserRepository;
import lee.andyfxq.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public User _save(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public void removeById(String id) {
		userRepository.deleteById(id);
	}

}
