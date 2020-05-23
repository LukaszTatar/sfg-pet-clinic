package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService service;

    Pet returnPet;

    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPetSet = new HashSet<>();
        returnPetSet.add(Pet.builder().id(1L).build());
        returnPetSet.add(Pet.builder().id(2L).build());
        when(petRepository.findAll()).thenReturn(returnPetSet);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));
        Pet pet = service.findById(1L);
        assertNotNull(pet);
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());
        Pet pet = service.findById(1L);
        assertNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().id(1L).build();
        when(petRepository.save(any())).thenReturn(returnPet);

        Pet pet = service.save(petToSave);

        assertNotNull(pet);
    }

    @Test
    void delete() {
        service.delete(returnPet);
        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(petRepository, times(1)).deleteById(anyLong());
    }
}