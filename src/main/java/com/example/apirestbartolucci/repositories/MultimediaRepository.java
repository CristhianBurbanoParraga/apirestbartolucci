/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

import com.example.apirestbartolucci.models.Multimedia;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author criss
 */
@Repository
public interface MultimediaRepository extends CrudRepository<Multimedia, Integer> {

    public Optional<Multimedia> findByNombre(String nombre);

    public Optional<Multimedia> findByPublicid(String publicid);

    public Optional<Multimedia> findByUrl(String url);
}
