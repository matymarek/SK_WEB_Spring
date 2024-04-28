package cz.uhk.sk_web.service;

import cz.uhk.sk_web.model.Post;
import cz.uhk.sk_web.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements PostRepository {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post findById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public <S extends Post> S save(S entity) {
        return postRepository.save(entity);
    }

    @Override
    public <S extends Post> Iterable<S> saveAll(Iterable<S> entities) {
        return postRepository.saveAll(entities);
    }

    @Override
    public Optional<Post> findById(Integer integer) {
        return postRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return postRepository.existsById(integer);
    }

    public List<Post> findAll() {
        return (List<Post>) postRepository.findAll();
    }

    public List<Post> findAllReversed() {
        List<Post> list = findAll();
        List<Post> reversed = new ArrayList<>();
        for(int i = list.size()-1; i >= 0; i--){
            reversed.add(list.get(i));
        }
        return reversed;
    }

    @Override
    public Iterable<Post> findAllById(Iterable<Integer> integers) {
        return postRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return postRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        postRepository.deleteById(integer);
    }

    @Override
    public void delete(Post entity) {
        postRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        postRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Post> entities) {
        postRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        postRepository.deleteAll();
    }

    public List<Post> getLastNPosts(int n){
        List<Post> jokes = (List<Post>) postRepository.findAll();
        int count = jokes.size();
        n = Math.min(n, count);
        return jokes.subList(count-n, count);
    }
}
