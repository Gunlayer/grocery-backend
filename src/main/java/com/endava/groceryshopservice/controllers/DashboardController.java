package com.endava.groceryshopservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.endava.groceryshopservice.entities.dto.VisitorRequestDTO;
import com.endava.groceryshopservice.services.VisitorService;
import com.endava.groceryshopservice.entities.Dashboard;
import com.endava.groceryshopservice.services.DashboardService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@Api(value = "Admin controller adds functionality to get statistic dates ")
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;
    private final VisitorService visitorService;

    @ApiOperation(value = "fetches statistic dates for admin")
    @GetMapping
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<Dashboard> getDashboard(){
        return ResponseEntity.status(HttpStatus.OK).body(dashboardService.getWeeklyDashboard());
    }

    @ApiOperation(value = "introduces new visitor")
    @PostMapping("/visitor")
    public ResponseEntity<HttpStatus> addVisitor(@RequestBody VisitorRequestDTO request) {
         visitorService.addVisitor(request);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
}