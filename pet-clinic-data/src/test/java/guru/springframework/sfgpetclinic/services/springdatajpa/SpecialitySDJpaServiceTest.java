package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    Speciality returnSpeciality;

    @BeforeEach
    void setUp() {
        returnSpeciality = Speciality.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Speciality> returnSpecialitySet = new HashSet<>();
        returnSpecialitySet.add(Speciality.builder().id(1L).build());
        returnSpecialitySet.add(Speciality.builder().id(2L).build());
        when(specialtyRepository.findAll()).thenReturn(returnSpecialitySet);
        Set<Speciality> specialities = service.findAll();
        assertNotNull(specialities);
        assertEquals(2, specialities.size());
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(returnSpeciality));
        Speciality speciality = service.findById(1L);
        assertNotNull(speciality);
    }

    @Test
    void findByIdNotFound() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.empty());
        Speciality speciality = service.findById(1L);
        assertNull(speciality);
    }

    @Test
    void save() {
        Speciality specialityToSave = Speciality.builder().id(1L).build();
        when(specialtyRepository.save(any())).thenReturn(returnSpeciality);
        Speciality speciality = service.save(specialityToSave);
        assertNotNull(speciality);
    }

    @Test
    void delete() {
        service.delete(returnSpeciality);
        verify(specialtyRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(specialtyRepository).deleteById(anyLong());
    }
}