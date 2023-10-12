package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.constants.Gender;
import com.stackroute.constants.ServiceCategory;
import com.stackroute.dto.SalonServiceDto;
import com.stackroute.entity.SequenceGeneratorService;
import com.stackroute.repository.SalonRepository;
import com.stackroute.service.SalonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SalonController.class)
@ActiveProfiles("test")
public class SalonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SalonService salonService;
    @MockBean
    private SalonRepository salonRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    SequenceGeneratorService sequenceGeneratorService;

    private List<SalonServiceDto> salonServiceDtoList;

    @BeforeEach
    void contextLoads() {


        this.salonServiceDtoList = new ArrayList<>();
        SalonServiceDto salonDto = new SalonServiceDto(1l, ServiceCategory.Hair, "Hair", "HairCutting", 2000, Gender.FEMALE, null,null);
        SalonServiceDto salonDto1 = new SalonServiceDto(2l, ServiceCategory.Hair, "Hair", "HairCutting", 4000, Gender.FEMALE, null,null);
        this.salonServiceDtoList.add(salonDto);
        this.salonServiceDtoList.add(salonDto1);

    }

    @Test
    void getAllUser() throws Exception {
        when(salonService.getAllSalons()).thenReturn(salonServiceDtoList);

        this.mockMvc.perform(get("/api/v2/salon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        is(salonServiceDtoList.size())));
    }
    @Test
    void addNewUSerTest() throws Exception{
        SalonServiceDto salonDto = new SalonServiceDto(1l, ServiceCategory.Hair, "Hair", "HairCutting", 2000, Gender.FEMALE, null,null);
        when(salonService.addSalon(any(SalonServiceDto.class))).thenAnswer((invocation) -> invocation.getArgument(0));
        SalonServiceDto check = salonService.addSalon(salonDto);
        assertEquals(salonDto.getServiceid(),check.getServiceid());

    }
    @Test
    void  getUserByIDTest() throws Exception{
       final Long serviceid = 1L;
       final SalonServiceDto salonDto = new SalonServiceDto(1L, ServiceCategory.Hair, "Hair", "HairCutting", 2000, Gender.FEMALE, null,null);
        given(salonService.getSalonById(serviceid)).willReturn(salonDto);




//        when(salonService.getSalonById(serviceid)).thenReturn(salonDto);
       this.mockMvc.perform(get("/api/v2/{serviceid}", serviceid))
                .andExpect(status().isOk()).andExpect(jsonPath("$.serviceid", is(salonDto.getServiceid())))
                .andExpect(jsonPath("$.serviceName", is(salonDto.getServiceName())));


    }

//    @Test
//    void updateUserById() throws Exception{
//        long
//        SalonServiceDto salonDto = new SalonServiceDto(1l, ServiceCategory.Hair, "Hair", "HairCutting", 2000, Gender.FEMALE, null);
//
//    }
}
