package cz.uhk.sk_web.service;

import cz.uhk.sk_web.model.Comment;
import cz.uhk.sk_web.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements CommentRepository {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public <S extends Comment> S save(S entity) {
        return commentRepository.save(entity);
    }

    @Override
    public <S extends Comment> Iterable<S> saveAll(Iterable<S> entities) {
        return commentRepository.saveAll(entities);
    }

    @Override
    public Optional<Comment> findById(Integer integer) {
        return commentRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return commentRepository.existsById(integer);
    }

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Iterable<Comment> findAllById(Iterable<Integer> integers) {
        return commentRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return commentRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        commentRepository.deleteById(integer);
    }

    @Override
    public void delete(Comment entity) {
        commentRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        commentRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Comment> entities) {
        commentRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
    }
}
