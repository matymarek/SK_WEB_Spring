package cz.uhk.sk_web.service;

import cz.uhk.sk_web.model.User;
import cz.uhk.sk_web.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserRepository {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void deleteAndArchiveInTransaction(int id) {
        userRepository.deleteAndArchiveInTransaction(id);
    }

    @Override
    public <S extends User> S save(S entity) {
        return userRepository.save(entity);
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return userRepository.saveAll(entities);
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return userRepository.existsById(integer);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<User> findAllById(Iterable<Integer> integers) {
        return userRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        userRepository.deleteById(integer);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        userRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        userRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    public List<User> findAllExcept(User user) {
        return ((List<User>)userRepository.findAll()).stream().filter((x) -> !Objects.equals(user.getId(), x.getId())).toList();
    }
}
