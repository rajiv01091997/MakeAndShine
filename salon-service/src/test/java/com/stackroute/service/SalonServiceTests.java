package com.stackroute.service;


import com.stackroute.constants.Gender;
import com.stackroute.constants.ServiceCategory;
import com.stackroute.dto.SalonServiceDto;
import com.stackroute.entity.Salon;
import com.stackroute.repository.SalonRepository;
import com.stackroute.service.impl.SalonServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {SalonServiceTests.class})
public class SalonServiceTests {
    @Mock
    SalonRepository salonRepository;

    @InjectMocks
    SalonServiceImpl salonService;



    Salon salon1 = new Salon(1L, ServiceCategory.Hair, "Hair", "HairCutting", 2000, Gender.FEMALE, null,null);
    Salon salon2 = new Salon(2L, ServiceCategory.Skin, "Hair", "HairCutting", 3000, Gender.MALE, null,null);




    List<Salon> salons = new ArrayList<Salon>();

    @Test
    @Order(1)
    public void test_getAllSalons() {

        this.salons.add(salon1);
        this.salons.add(salon2);

        when(salonRepository.findAll()).thenReturn(salons);
        assertEquals(2, salonService.getAllSalons().size());

    }

    @Test
    @Order(3)
    public void test_getSalonByID() {
        this.salons.add(salon1);
        long salonID = 1;

        when(salonRepository.findById(salonID)).thenReturn(Optional.of(salon1));
        SalonServiceDto salonServiceDto = salonService.getSalonById(salonID);
        assertEquals(salonServiceDto.getServiceid(), salon1.getServiceid());


    }

    @Test
    @Order(2)
    public void test_addSalon() {
        this.salons.add(salon1);
        when(salonRepository.save(salon1)).thenReturn(salon1);
        SalonServiceDto salonServiceDto = salonService.convertToDto(salon1);
        SalonServiceDto salonServiceDto1 = salonService.addSalon(salonServiceDto);
        assertEquals(salonServiceDto1.getServiceid(), salon1.getServiceid());

    }

   /* @Test@Order(4)
    public void test_updateSalonById(){
*/




   @Test
   @Order(4)
   public void deleteSalonByIdTest() {

       Optional<Salon> salon = Optional.of(new Salon(1l, ServiceCategory.Hair, "Hair", "HairCutting", 2000, Gender.FEMALE, null,null));
       when(salonRepository.findById(1l)).thenReturn(salon);
       salonService.deleteSalonById(salon.get().getServiceid());

   }

}
