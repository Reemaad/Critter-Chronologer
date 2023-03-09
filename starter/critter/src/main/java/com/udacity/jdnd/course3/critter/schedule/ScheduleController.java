package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.UserService;
import com.udacity.jdnd.course3.critter.utils.Converter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.createSchedule(Converter.convertFromScheduleDTOToSchedule(scheduleDTO), scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds());
        return Converter.convertFromScheduleToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for (Schedule schedule: schedules) {
            scheduleDTOS.add(Converter.convertFromScheduleToScheduleDTO(schedule));
        }
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule: schedules) {
            scheduleDTOS.add(Converter.convertFromScheduleToScheduleDTO(schedule));
        }
        return scheduleDTOS;

    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule: schedules) {
            scheduleDTOS.add(Converter.convertFromScheduleToScheduleDTO(schedule));
        }
        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule: schedules) {
            scheduleDTOS.add(Converter.convertFromScheduleToScheduleDTO(schedule));
        }
        return scheduleDTOS;
    }
}
