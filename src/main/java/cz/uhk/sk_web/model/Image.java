package cz.uhk.sk_web.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "imgs")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "img_name")
    private String name;

    @Column(name = "alt", nullable = false, length = 128)
    private String alt;


    public Image() {
    }

    public Image(String name, String alt) {
        this.name = name;
        this.alt = alt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alt='" + alt + '\'' +
                '}';
    }
}
