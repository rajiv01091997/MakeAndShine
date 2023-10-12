package com.stackroute.appointmentservice.service;


 import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.appointmentservice.dto.BookAppointmentDto;
import com.stackroute.appointmentservice.entity.BookAppointment;
import com.stackroute.appointmentservice.exception.ResourceNotFoundException;
import com.stackroute.appointmentservice.interfaces.BookAppointmentService;
import com.stackroute.appointmentservice.repository.BookAppointmentRepository;

@SpringBootTest
 class BookingAppointmentServiceTest {

	 @MockBean
	 private BookAppointmentRepository bookRepository;
	 
	 @Autowired
	 private BookAppointmentService bookService;
      
	 BookAppointment fakeObj1 = new BookAppointment(1,2,"jyoti.sonani@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com");
     BookAppointment fakeObj2 = new BookAppointment(2,3,"sonam@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com");
		
	 List<BookAppointment> bookAppointments= new ArrayList<>();
         
     @Test
     void getBookingAppointmentByCustomerEmailId()
     {
    	   this.bookAppointments.add(fakeObj1);
    	   this.bookAppointments.add(fakeObj2);

    	   String customerEmailId1="jyoti.sonani@gmail.com";
    	   
    	   when(bookRepository.findByCustomerEmailId(customerEmailId1)).thenReturn(bookAppointments);

    	   List<BookAppointmentDto> bookAppointmentDtos= bookService.getBookingDetailsByCustomerEmailId(customerEmailId1);
           assertEquals(2, bookAppointmentDtos.size());
      }

    @Test
    void getBookingDetailsByAppointmentID() {
    	
   	 BookAppointment fakeObj1 = new BookAppointment(1,2,"jyoti.sonani@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com");
    	
    	long appointmentId=1;
    	when(bookRepository.findById(appointmentId)).thenReturn(Optional.of(fakeObj1));
    	BookAppointmentDto bookAppointmentDto = bookService.getBookingDetailsByAppointmentID(appointmentId);
    	assertEquals(bookAppointmentDto.getAppointmentId(), fakeObj1.getAppointmentId());
    	assertEquals(bookAppointmentDto.getCustomerEmailId(), fakeObj1.getCustomerEmailId());
    }

    @Test
    void getBookingDetailsByAppointmentIDNegative() {
    	
    	
    	long appointmentId=101;
    	when(bookRepository.findById(appointmentId)).thenReturn(Optional.empty());
    	assertThrows(ResourceNotFoundException.class, ()->{
    		bookService.getBookingDetailsByAppointmentID(appointmentId);
    	}); 
     }
    
    @Test
    void creatNewBookingAppointment() {
   
    	BookAppointment fakeObj1 = new BookAppointment(1,2,"jyoti.sonani@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com");
          when(bookRepository.save(fakeObj1)).thenReturn(fakeObj1);
          BookAppointmentDto bookAppointmentDto =bookService.convertToDto(fakeObj1);
          BookAppointmentDto bookAppointmentDto2 =bookService.addBookingAppointment(bookAppointmentDto);
          assertEquals(bookAppointmentDto2.getAppointmentId(), fakeObj1.getAppointmentId());
    }
    
    @Test
    void updateBookingDetail() {
    	
    	BookAppointment fakeObj1 = new BookAppointment(1,2,"jyoti.sonani@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com");
        long serviceId =2;
    	when(bookRepository.findById(serviceId)).thenReturn(Optional.of(fakeObj1));
    	BookAppointmentDto bookDto = bookService.convertToDto(fakeObj1);
    	bookDto.setServiceId(3);
		when(bookService.updateBookingDetail(serviceId,bookDto)).thenReturn(bookDto);
		BookAppointmentDto bookDto1 = bookService.updateBookingDetail(serviceId,bookDto);
		assertEquals(bookDto1.getServiceId(),2);
    }
    
    @Test
    void  cancelBookingDetails()
    {
    	BookAppointment fakeObj1 = new BookAppointment(1,2,"jyoti.sonani@gmail.com",null,null,null,"best salon must visit",true,"sonam.kapase@gmail.com");

    	long appointmentId =1;
    	when(bookRepository.findById(appointmentId).get()).thenReturn(fakeObj1);
    	BookAppointmentDto bookAppointmentDto = bookService.convertToDto(fakeObj1);;
    	BookAppointmentDto bookDto2= bookService.cancelBookingDetails(appointmentId);
    	assertEquals(bookDto2.getAppointmentId(), fakeObj1.getAppointmentId());
    	
    }

}
