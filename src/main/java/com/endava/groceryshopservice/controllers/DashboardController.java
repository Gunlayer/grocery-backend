package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.Visitor;
import com.endava.groceryshopservice.entities.dto.MostSoldProductDTO;
import com.endava.groceryshopservice.entities.dto.VisitorRequestDTO;
import com.endava.groceryshopservice.repositories.VisitorRepository;
import com.endava.groceryshopservice.services.UserService;
import com.endava.groceryshopservice.services.VisitorService;
import io.swagger.annotations.Api;

import com.endava.groceryshopservice.entities.Dashboard;
import com.endava.groceryshopservice.services.DashboardService;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Api(value = "Admin controller adds functionality to get statistic dates ")
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;
    private final VisitorService visitorService;
    private final VisitorRepository visitorRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<Dashboard> getDashboard(){
        return ResponseEntity.status(HttpStatus.OK).body(dashboardService.getWeeklyDashboard());
    }

    @PostMapping("/visitor")
//    @PreAuthorize("hasAuthority('users:write')")
    @ApiOperation(value = "introduces new visitor")
    public ResponseEntity<HttpStatus> addVisitor(@RequestBody VisitorRequestDTO request) {
         visitorService.addVisitor(request);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/v")
    public List<Visitor> getAll() {
        return visitorRepository.findAll();
    }
}
