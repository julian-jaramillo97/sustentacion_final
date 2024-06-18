package com.domain.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.app.model.Auto;
import com.domain.app.repository.AutosRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AutosController {
    @Autowired
    private AutosRepository autosRepository;

    @GetMapping("/autos")
    public ResponseEntity<List<Auto>> getAllAutos(@RequestParam(required = false) String modelo) {
        try {
            List<Auto> autos = new ArrayList<>();

            if (modelo == null)
                autos.addAll(autosRepository.findAll()); // Obtener todos los autos
            else
                autos.addAll(autosRepository.findByModeloContaining(modelo)); // Filtrar por modelo

            if (autos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(autos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Auto> getAutoById(@PathVariable("id") long id) {
        Optional<Auto> autoData = autosRepository.findById(id);

        if (autoData.isPresent()) {
            return new ResponseEntity<>(autoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Auto> createAuto(@RequestBody Auto auto) {
        try {
            Auto _auto = autosRepository.save(auto);
            return new ResponseEntity<>(_auto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auto> updateAuto(@PathVariable("id") long id,
                                           @RequestBody Auto auto) {
        Optional<Auto> autoData = autosRepository.findById(id);

        if (autoData.isPresent()) {
            Auto _auto = autoData.get();
            _auto.setMarca(auto.getMarca());
            _auto.setModelo(auto.getModelo());
            _auto.setAño(auto.getAño());
            _auto.setColor(auto.getColor());
            _auto.setPrecio(auto.getPrecio());
            _auto.setTipo(auto.getTipo());
            _auto.setConcesionaria(auto.getConcesionaria());

            return new ResponseEntity<>(autosRepository.save(_auto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuto(@PathVariable("id") long id) {
        try {
            autosRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllAutos() {
        try {
            autosRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Auto>> findByModeloContaining(@RequestParam String modelo) {
        try {
            List<Auto> autos = autosRepository.findByModeloContaining(modelo);

            if (autos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(autos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
