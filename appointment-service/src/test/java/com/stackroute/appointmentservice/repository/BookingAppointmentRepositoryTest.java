package com.stackroute.appointmentservice.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


import com.stackroute.appointmentservice.entity.BookAppointment;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
public class BookingAppointmentRepositoryTest {
     
	
	@Mock
	BookAppointmentRepository bookRepository;


	@Test
	
	 void testGetAllBookingDetails() {

		
		BookAppointment fakeObj1 = new BookAppointment(1,2,"jyoti.sonani@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com");
		BookAppointment fakeObj2 = new BookAppointment(2,3,"jyoti1.sonani@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com");
		BookAppointment fakeObj3 = new BookAppointment(3,4,"jyoti2.sonani@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com");
		
		List<BookAppointment> bookingAppointmentDtos = new ArrayList<>();
		bookingAppointmentDtos.add(fakeObj1);
		bookingAppointmentDtos.add(fakeObj2);
		bookingAppointmentDtos.add(fakeObj3);
        when(bookRepository.findAll()).thenReturn(bookingAppointmentDtos);	
        List<BookAppointment> list = bookRepository.findAll();
        assertEquals(list.size(), 3);
	
	}
		
	@Test
	void createNewBookingAppointmentTest() {
		
		BookAppointment fakeObj1 = new BookAppointment(1,2,"jyoti.sonani@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com");
		when(bookRepository.save(fakeObj1)).thenReturn(fakeObj1);
	   BookAppointment bookObjAppointment = bookRepository.save(fakeObj1);
	   assertEquals(bookObjAppointment.getAppointmentId(),fakeObj1.getAppointmentId());
		assertEquals(bookObjAppointment.getCustomerEmailId(), fakeObj1.getCustomerEmailId());
		
	}
	
	@Test
	void creatNewBookAppointmentTest() {
		BookAppointment bookAppointment = new BookAppointment();
		when(bookRepository.save(bookAppointment)).thenReturn(null);
		BookAppointment bookAppointment2 = bookRepository.save(bookAppointment);
		assertNull(bookAppointment2);
		
	}
	
	@Test
	void getBookingByAppointmentIdTest() {
		
		BookAppointment  fakeObj1 = new BookAppointment(1,2,"jyoti.sonani@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com"); 
		when(bookRepository.findById(fakeObj1.getAppointmentId())).thenReturn(Optional.of(fakeObj1));
		BookAppointment bookAppointment = bookRepository.findById(fakeObj1.getAppointmentId()).get();
		assertEquals(bookAppointment.getAppointmentId(), fakeObj1.getAppointmentId());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
