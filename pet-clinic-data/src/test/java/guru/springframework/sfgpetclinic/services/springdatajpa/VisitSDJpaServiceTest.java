package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
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
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    Visit returnVisit;

    @BeforeEach
    void setUp() {
        returnVisit = Visit.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Visit> returnVisitSet = new HashSet<>();
        returnVisitSet.add(Visit.builder().id(1L).build());
        returnVisitSet.add(Visit.builder().id(2L).build());
        when(visitRepository.findAll()).thenReturn(returnVisitSet);
        Set<Visit> visits = service.findAll();
        assertNotNull(visits);
        assertEquals(2, visits.size());
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnVisit));
        Visit visit = service.findById(1L);
        assertNotNull(visit);
    }

    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());
        Visit visit = service.findById(1L);
        assertNull(visit);
    }

    @Test
    void save() {
        Visit visitToSave = Visit.builder().id(1L).build();
        when(visitRepository.save(any())).thenReturn(returnVisit);
        Visit visit = service.save(visitToSave);
        assertNotNull(visit);
    }

    @Test
    void delete() {
        service.delete(returnVisit);
        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(visitRepository).deleteById(anyLong());
    }
}