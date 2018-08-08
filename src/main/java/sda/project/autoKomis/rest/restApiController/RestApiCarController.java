package sda.project.autoKomis.rest.restApiController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.project.autoKomis.model.car.Car;
import sda.project.autoKomis.rest.exeptions.CarErrorResponse;
import sda.project.autoKomis.rest.exeptions.CarNotFoundException;
import sda.project.autoKomis.service.CarDataService;

import java.util.List;

@RestController
@RequestMapping("/auto-komis/api/")
public class RestApiCarController {


    @Autowired
    private final CarDataService carDataService;

    public RestApiCarController(CarDataService carDataService) {
        this.carDataService = carDataService;
    }


    @GetMapping(value = "/all-cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> getAllCars() {
        return carDataService.getAllCars();
    }

    @GetMapping(value = "/all-cars/{carId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car get(@PathVariable("carId") Integer carId) {
        if (carDataService.getById(carId) != null) {
            return carDataService.getById(carId);
        } else {
            throw new CarNotFoundException("Nie ma takiego samochodu o ID: " + carId);
        }
    }

    @ExceptionHandler
    public ResponseEntity<CarErrorResponse> handlerException(CarNotFoundException exc) {
        CarErrorResponse error = new CarErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
