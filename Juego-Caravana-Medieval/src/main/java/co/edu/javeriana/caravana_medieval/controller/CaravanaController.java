package co.edu.javeriana.caravana_medieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval.mapper.CaravanaMapper;
import co.edu.javeriana.caravana_medieval.model.Caravana;
import co.edu.javeriana.caravana_medieval.service.CaravanaService;


@RestController
@RequestMapping("/caravana")
public class CaravanaController {
    @Autowired
    private CaravanaService caravanaService;

    @GetMapping("/{id}")
    public CaravanaDTO getCaravanaById(@PathVariable Long id) {
        Caravana caravana = caravanaService.getCaravanaById(id);
        CaravanaMapper caravanaMapper = new CaravanaMapper();
        CaravanaDTO caravanaDTO = caravanaMapper.toDTO(caravana);
        return caravanaDTO;
    }
    
    

}
