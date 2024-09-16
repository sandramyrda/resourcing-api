package io.nology.jobs.temp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("temps")
public class TempController {

    @Autowired
    private TempService tempService;

    @GetMapping
    public ResponseEntity<List<Temp>> getTemps(@RequestParam(required = false) Long jobId) {
        if (jobId != null) {
            List<Temp> availableTemps = tempService.findAvailableTempsForJob(jobId);
            return ResponseEntity.ok(availableTemps);
        } else {
            List<Temp> temps = tempService.findAll();
            return new ResponseEntity<List<Temp>>(temps, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Temp> createTemp(@Valid @RequestBody CreateTempDTO data) {
        Temp createdTemp = tempService.createTemp(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTemp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Temp> getTempById(@PathVariable Long id) {
        Temp temp = tempService.getTempById(id);
        return ResponseEntity.ok(temp);
    }
}
