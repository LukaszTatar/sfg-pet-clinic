package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
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
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService service;

    PetType returnPetType;

    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<PetType> returnPetTypeSet = new HashSet<>();
        returnPetTypeSet.add(PetType.builder().id(1L).build());
        returnPetTypeSet.add(PetType.builder().id(2L).build());
        when(petTypeRepository.findAll()).thenReturn(returnPetTypeSet);
        Set<PetType> petTypes = service.findAll();
        assertNotNull(petTypes);
        assertEquals(2, petTypes.size());
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));
        PetType petType = service.findById(1L);
        assertNotNull(petType);
    }

    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());
        PetType petType = service.findById(1L);
        assertNull(petType);
    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.builder().id(1L).build();
        when(petTypeRepository.save(any())).thenReturn(returnPetType);
        PetType petType = service.save(petTypeToSave);
        assertNotNull(petType);
    }

    @Test
    void delete() {
        service.delete(returnPetType);
        verify(petTypeRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(petTypeRepository).deleteById(anyLong());
    }
}