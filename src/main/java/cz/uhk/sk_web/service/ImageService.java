package cz.uhk.sk_web.service;

import cz.uhk.sk_web.model.Image;
import cz.uhk.sk_web.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService implements ImageRepository {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image findById(int id) {
        return imageRepository.findById(id);
    }

    @Override
    public <S extends Image> S save(S entity) {
        return imageRepository.save(entity);
    }

    @Override
    public <S extends Image> Iterable<S> saveAll(Iterable<S> entities) {
        return imageRepository.saveAll(entities);
    }

    @Override
    public Optional<Image> findById(Integer integer) {
        return imageRepository.findById(integer);
    }
    @Override
    public boolean existsById(Integer integer) {
        return imageRepository.existsById(integer);
    }

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Iterable<Image> findAllById(Iterable<Integer> integers) {
        return imageRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return imageRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        imageRepository.deleteById(integer);
    }

    @Override
    public void delete(Image entity) {
        imageRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        imageRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Image> entities) {
        imageRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        imageRepository.deleteAll();
    }
}
