/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Subnivel;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends CrudRepository<Actividad, Integer> {

    public ArrayList<Actividad> findBySubnivel(Subnivel subnivel);

    public ArrayList<Actividad> findByTipo(String tipo);

    public ArrayList<Actividad> findByValor(int valor);
}
